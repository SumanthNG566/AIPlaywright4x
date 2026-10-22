## 1. Test Plan ID and Title

| Field | Value |
| --- | --- |
| Test Plan ID | TP-SF-LOGIN-001 |
| Title | Salesforce Login Page — Functional, UI, Cross-Browser, Basic Security, and Automation Regression Test Plan |
| Version | 1.0 (Draft) |
| Date | 2026-10-07 |
| Application | Salesforce CRM — Login page |
| URL | `https://login.salesforce.com/?locale=in` |
| Status | Awaiting approval |

---

## 2. Objective and References

### Objective

Confirm that a user can sign in to Salesforce through the login page with valid credentials. Confirm that invalid, empty, or malformed input is rejected with a clear error message and no session is created. Also confirm that the page renders and behaves the same way across supported browsers. Critical paths are kept under automated regression through the existing Selenium framework.


## 3. In Scope and Out of Scope

### In scope

| Area | Details |
| --- | --- |
| Functional — positive | Valid login with a provisioned account; two-step (identifier-first) flow; Remember Me selected on login |
| Functional — negative | Wrong password, non-existent user, malformed username, empty username, empty password, both fields empty |
| UI / Usability | Element presence and labels, page title, logo, password masking, Remember Me toggle, Forgot Password link presence, `locale=in` rendering, keyboard navigation (Tab order, Enter to submit) |
| Cross-browser | Chrome, Edge, Firefox (latest stable) on Windows 11 |
| Basic security | HTTPS enforced, credentials not exposed in the URL, password masked, SQL-like and script-like input denied without bypass or script execution, generic error text that does not reveal whether the username exists |
| Automation regression | Selenium + Java + TestNG suite in `Selenium_Framework`, run on every test cycle |

### Out of scope

| Area | Reason |
| --- | --- |
| MFA / identity verification codes | Excluded by agreement. The valid-login check only confirms that the browser left the login page. |
| SSO and "Use Custom Domain" / My Domain | Excluded by agreement |
| Forgot Password journey | Only the link's presence is verified. The reset flow is not tested. |
| Performance / load testing | Excluded by agreement |
| Penetration testing | Excluded by agreement. Testing Salesforce production also needs written authorization. |
| Account registration, post-login features | Not part of the login feature |

---

## 4. Requirements and Planned Coverage

All requirement IDs below are **local IDs**. Expected messages are the **observed** text.

| Req ID | Requirement | Test Scenarios | Type | Priority (Proposed) | Coverage |
| --- | --- | --- | --- | --- | --- |
| REQ-LGN-01 | The login page loads with the username field, Log In button, Remember Me checkbox, Forgot Password link, and logo; the title contains "Salesforce" | Page load and element visibility; title check; Log In button label is "Log In" | UI | P1 | Automated (`ValidLoginTest`) |
| REQ-LGN-02 | Submitting a valid-format username shows the password step (identifier-first flow) | Username → Log In → password field displayed; username field hidden | Functional + | P1 | Automated (via `submitUsername`) |
| REQ-LGN-03 | Valid credentials authenticate the user and leave the login page | Valid login; valid login with Remember Me; landing page is Home or identity verification | Functional + | P1 | Automated (needs credentials) |
| REQ-LGN-04 | A wrong password or non-existent user is rejected with: "Please check your username and password. If you still can't log in, contact your Salesforce administrator." | Wrong password; non-existent user; wrong password with Remember Me | Functional − | P1 | Automated (`InvalidLoginTest`) |
| REQ-LGN-05 | An empty username is rejected with "Please enter your username." | Both fields empty; username only empty | Functional − | P1 | Automated |
| REQ-LGN-06 | An empty password at the password step is rejected with "Please enter your password." | Valid-format username, blank password | Functional − | P1 | Automated |
| REQ-LGN-07 | A non-email username is rejected and no session is created | `invalid-user`. Observed message: "Please enter your password." (see Open Question Q1) | Functional − | P2 | Automated |
| REQ-LGN-08 | The password field masks input | Field `type="password"` after entry | UI / Security | P1 | Automated |
| REQ-LGN-09 | Remember Me can be selected and deselected, and remembers the username on return | Toggle on/off (automated); revisit after logout shows the remembered username (manual) | UI / Functional | P2 | Partly automated |
| REQ-LGN-10 | Basic security controls | HTTPS redirect from HTTP; no credentials in the URL or query string after submit; SQL-like input (`' OR '1'='1`) and script input (`<script>alert(1)</script>`) denied with no bypass and no script execution; the error does not reveal whether the username exists | Security (basic) | P1 | Manual |
| REQ-LGN-11 | Behaviour is consistent across Chrome, Edge, and Firefox | Run the full automation suite plus a manual smoke test on each browser | Cross-browser | P2 | Automated (`-Dbrowser=`) + Manual |
| REQ-LGN-12 | The page is usable with the keyboard and with `locale=in` | Logical Tab order; Enter submits; labels associated with inputs; layout correct at the 1366×768 and 1920×1080 viewports | Usability | P3 | Manual |

### Coverage summary

| Coverage | Requirements |
| --- | --- |
| Fully automated | REQ-LGN-01, 02, 04, 05, 06, 07, 08 |
| Automated, needs provisioned credentials | REQ-LGN-03 |
| Partly automated | REQ-LGN-09, 11 |
| Manual only | REQ-LGN-10, 12 |

---

## 5. Test Approach, Levels, and Types

| Level / Type | Approach |
| --- | --- |
| System testing — functional | Scripted manual cases derived from Section 4, then automation of the stable P1/P2 paths |
| UI / usability | Checklist-based manual review plus automated element and attribute assertions |
| Exploratory testing | One 60–90 minute time-boxed session per browser, focused on the two-step flow, Back/Forward navigation, and refresh mid-flow |
| Cross-browser | Full automated suite run on each browser in the matrix (Section 6); manual smoke test on each |
| Basic security | Manual checks for REQ-LGN-10 using browser DevTools (Network tab, URL inspection). No scanning tools and no attack traffic. |
| Regression (automated) | `Selenium_Framework` suite: Page Object Model with PageFactory, XPath locators, explicit waits, TestNG `@DataProvider` for negative data |
| Test design techniques | Equivalence partitioning (valid / invalid / empty), error guessing (malformed, SQL-like, script input), state transition (username step → password step → error / success) |

### Automation suite overview

| Test Class | Tests | Requirements |
| --- | --- | --- |
| `ValidLoginTest` | UI elements, title, password masking, Remember Me toggle, valid login | REQ-LGN-01, 02, 03, 08, 09 |
| `InvalidLoginTest` | 6 data-driven negative cases + invalid login with Remember Me | REQ-LGN-04, 05, 06, 07 |

**Execution evidence to date:** one headless Chrome run on 2026-10-07 executed **12 tests: 11 passed, 0 failed, 1 skipped**. The skipped test was the valid login, because no credentials were supplied. This was a framework validation run, not a formal test cycle.

---

## 6. Environment, Tools, Access, and Test Data

### Environment

| Item | Value |
| --- | --- |
| Org | Salesforce Developer Edition org (free) |
| Login URL | `https://login.salesforce.com/?locale=in` |
| OS | Windows 11 Pro |
| Browsers | Chrome, Edge, Firefox — latest stable |
| Network | Standard internet access. No proxy that blocks `*.salesforce.com`. |

### Tools

| Tool | Version |
| --- | --- |
| Java | 17 |
| Maven | 3.9.x |
| Selenium | 4.27.0 (drivers managed by Selenium Manager) |
| TestNG | 7.10.2 |
| Defect tracker | Jira (proposed) |
| Test management | Jira / Xray or a shared spreadsheet (proposed) |

### Access and test data

| Data | Source | Notes |
| --- | --- | --- |
| Valid account | Provisioned Developer Edition user | Supplied only through `SF_USERNAME` / `SF_PASSWORD` or `-Dsf.username` / `-Dsf.password`. Never committed. |
| Wrong password | Synthetic (`WrongPass@123`) | Used against `qa.automation.invalid@example.com` |
| Non-existent user | Synthetic (`no.such.user.20261007@example.com`) | `example.com` is a reserved domain |
| Malformed username | `invalid-user` | |
| Security input | `' OR '1'='1`, `<script>alert(1)</script>` | Denial checks only |

**Lockout caution:** repeated failed logins against the **real** test account may lock it. Negative tests must use only the synthetic usernames listed above.

---

## 7. Entry and Exit Criteria (Proposed)

### Entry criteria

1. This test plan is approved.
2. The Developer Edition org and the valid test user are provisioned, and their credentials are stored as environment variables.
3. The login page is reachable from the test machines.
4. `Selenium_Framework` compiles successfully (`mvn test-compile`).
5. Test cases for REQ-LGN-01 to REQ-LGN-12 are written and reviewed.

### Exit criteria

1. 100% of planned P1 test cases have been executed.
2. At least 95% of all executed test cases have passed.
3. No Critical or High severity defects remain open.
4. The automation suite passes on Chrome with valid credentials supplied, with 0 failed and 0 skipped tests.
5. All Medium and Low defects are triaged and have an agreed target or a documented acceptance.
6. The Test Summary Report is published and signed off.

---

## 8. Roles, Responsibilities, Estimates, and Schedule (Proposed)

### Roles

| Role | Responsibilities |
| --- | --- |
| QA Lead | Owns the plan, triages defects, reports status, and gives exit sign-off |
| Manual QA | Designs and executes manual, UI, usability, security, and exploratory tests |
| Automation QA | Maintains the `Selenium_Framework` suite, runs the cross-browser automation, and publishes TestNG reports |
| Product Owner / Admin | Provisions the org and accounts, answers requirement questions, and approves the plan and the summary |

### Schedule (2 weeks)

| Phase | Dates (Proposed) | Owner | Output |
| --- | --- | --- | --- |
| Plan review and approval | 2026-10-12 – 2026-10-13 | QA Lead | Approved plan |
| Test case design and review | 2026-10-13 – 2026-10-15 | Manual QA | Reviewed test cases |
| Environment and data setup | 2026-10-12 – 2026-10-14 | Automation QA / Admin | Org, user, env vars |
| Automation update and dry run | 2026-10-14 – 2026-10-16 | Automation QA | Green suite on Chrome |
| Cycle 1 — functional, UI, security | 2026-10-16 – 2026-10-20 | Manual QA | Execution results, defects |
| Cross-browser run | 2026-10-19 – 2026-10-21 | Automation QA | Reports for Chrome, Edge, and Firefox |
| Defect fix verification and regression | 2026-10-21 – 2026-10-22 | QA team | Retest results |
| Test Summary Report and sign-off | 2026-10-23 | QA Lead | Signed summary |

---

## 9. Defect Management and Reporting

### Defect lifecycle

`New → Triaged → Assigned → Fixed → Ready for Retest → Closed`, with side states `Reopened`, `Deferred`, and `Rejected`.

### Severity definitions

| Severity | Definition | Example |
| --- | --- | --- |
| Critical | Login is impossible, or security is bypassed | Valid user cannot log in; SQL-like input authenticates |
| High | A core path fails or wrong behaviour has no workaround | Error message is missing for wrong password |
| Medium | Incorrect behaviour with a workaround | Remember Me does not retain the username |
| Low | Cosmetic or text issue | Label misalignment |

### Defect report fields

Bug ID | Title | Environment / Browser | Preconditions | Test Data | Steps to Reproduce | Expected Result | Actual Result | Evidence (screenshot, TestNG report) | Severity | Priority | Requirement ID

### Triage and reporting

| Activity | Cadence |
| --- | --- |
| Defect triage | Daily during execution, 15 minutes |
| Status report (executed / passed / failed / blocked, open defects by severity) | Daily |
| Automation report (`target/surefire-reports`) | Every run, attached to the status report |
| Test Summary Report | End of cycle |

---

## 10. Risks, Dependencies, Assumptions, and Open Questions

### Risks

| ID | Risk | Impact | Mitigation |
| --- | --- | --- | --- |
| RK-01 | Salesforce changes the login UI or flow (for example, the two-step flow changes) | Automation breaks; expected results become outdated | Keep locators in one Page Object; run a smoke test before each cycle |
| RK-02 | Identity verification appears on a new device or browser | The valid-login run stops at the verification page | The assertion accepts "left the login page"; verify the device once manually |
| RK-03 | Account lockout or rate limiting from repeated attempts | Blocks valid-login testing | Use synthetic users for negative tests; limit runs |
| RK-04 | The behaviour is a production third-party page outside our control | Defects cannot be fixed by our team | Report findings as observations; escalate through Salesforce support if needed |
| RK-05 | Headless and headed browsers differ | False passes or failures | Run at least one headed run per browser |

### Dependencies

- Developer Edition org and a valid user (Product Owner / Admin).
- Internet access to `login.salesforce.com`.
- Browsers installed on the test machines.

### Assumptions

- `?locale=in` returns English (India) text. The error messages listed in Section 4 are the observed English text.
- "Successful login" means the browser leaves the login page. Post-login content is out of scope.

### Open questions

| ID | Question | Owner |
| --- | --- | --- |
| Q1 | A non-email username (`invalid-user`) shows "Please enter your password." at the username step. Should this be accepted as expected behaviour, or raised as an observation? | Product Owner |
| Q2 | Should Remember Me persistence (REQ-LGN-09) be automated? It needs a successful login and logout cycle. | QA Lead |
| Q3 | Is a defect tracker other than Jira required? | QA Lead |

---

## 11. Suspension and Resumption Criteria

| Suspend testing when | Resume when |
| --- | --- |
| `login.salesforce.com` is unavailable or degraded for more than 1 hour | The page is reachable and the smoke test (REQ-LGN-01) passes |
| The valid test account is locked or expired | The account is unlocked or re-provisioned and credentials are updated |
| A Critical defect blocks more than 30% of planned P1 cases | A fix or workaround is confirmed and the blocked cases are re-planned |
| More than 50% of automation tests fail because of a UI change | `LoginPage` locators and flow have been updated and the dry run passes |

---

## 12. Test Deliverables and Approval

### Deliverables

| Deliverable | Format | Owner |
| --- | --- | --- |
| Test Plan (this document) | Markdown | QA Lead |
| Test Cases (REQ-LGN-01 to 12) | Jira / spreadsheet | Manual QA |
| Automation suite | `Selenium_Framework` Maven project | Automation QA |
| Automation execution reports | TestNG / Surefire reports per browser | Automation QA |
| Defect reports | Jira | QA team |
| Daily status reports | Email / chat | QA Lead |
| Test Summary Report | Markdown / PDF | QA Lead |

### Approval

| Role | Name | Decision | Date |
| --- | --- | --- | --- |
| QA Lead | | | |
| Product Owner | | | |
| Development / Admin Lead | | | |
