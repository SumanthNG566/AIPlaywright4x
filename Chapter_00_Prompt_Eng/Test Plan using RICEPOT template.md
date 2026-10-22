# VWO Test Plan

**Test Plan ID:** TP-VWO-001
**Title:** VWO – Digital Experience Optimization Platform — Test Plan
**Framework:** RICE POT — **R**ole · **I**nstructions · **C**ontext · **E**xample · **P**arameters · **O**utput · **T**one
**Template:** RICE POT Generic QA Template (`04_RICE_POT_Generic_QA_Template.md`)
**Prompt used:** `Test Plan Prompt using RICEPOT template.md`
**Product:** VWO – Digital Experience Optimization Platform
**Product URL (from PRD):** https://app.vwo.com/
**PRD:** `LLM_Basics/Product Requirements Document (PRD) VWO.com.pdf`
**PRD text source of truth:** `LLM_Basics/VWO_Test_Plan_Extracted_PRD.txt`
**Governing rules:** `03_Anti_Hallucinations.md`
**Date:** 2026-10-02
**Status:** **Planned — not executed.** No test in this document has been run; no results are reported.

> This test plan follows the RICE POT template's 12-section test-plan structure. Confirmed facts, proposed assumptions and unresolved questions are kept separate throughout. Anything the PRD does not state is marked **Not provided** or **Insufficient information to determine** — never invented.

---

## 1. Test Plan ID and Title

| Field | Value |
| --- | --- |
| **Test Plan ID** | TP-VWO-001 |
| **Title** | VWO – Digital Experience Optimization Platform — Test Plan |
| **Version** | 1.0 (planned) |
| **Date** | 2026-10-02 |
| **Prepared by** | QA (role to be assigned — see §8) |
| **Source** | PRD VWO.com (product URL https://app.vwo.com/) |

## 2. Objective and References

**Objective.** Verify that the VWO platform delivers the capabilities and non-functional requirements stated in the PRD — experimentation (FR-1), the SmartStats engine (FR-2), visual & code editors (FR-3), behavioural insights (FR-4), audience targeting (FR-5), real-time reporting (FR-6), personalization (FR-7), integrations (FR-8), collaboration/workflow (FR-9), and the five non-functional categories (§7) — with full requirement traceability and no invented detail.

**References.**

| Ref | Document |
| --- | --- |
| R-1 | `LLM_Basics/Product Requirements Document (PRD) VWO.com.pdf` |
| R-2 | `LLM_Basics/VWO_Test_Plan_Extracted_PRD.txt` (verbatim text of R-1) |
| R-3 | `Chapter_00_Prompt_Eng/03_Anti_Hallucinations.md` (governing rules) |
| R-4 | `Chapter_00_Prompt_Eng/04_RICE_POT_Generic_QA_Template.md` (RICE POT framework) |
| R-5 | `Chapter_00_Prompt_Eng/Test Plan Prompt using RICEPOT template.md` (prompt used) |

## 3. In Scope and Out of Scope

**In scope** (all traceable to the PRD):

- Experimentation: A/B, Split URL and Multivariate testing; multiple variations (FR-1, §4.1)
- SmartStats Bayesian engine and statistically validated reports (FR-2, §4.1)
- Visual (WYSIWYG) and code editors; version previews; cross-device/cross-browser QA; scheduling (FR-3, §4.1)
- Heatmaps (click/scroll/focus), session recordings, on-page surveys & feedback, funnel analytics (FR-4, §4.2)
- Audience targeting / behavioural segmentation (FR-5, §4.1)
- Real-time reporting & dashboards (FR-6)
- Personalization engine — segments by geography/behaviour/demographics; real-time delivery (FR-7, §4.3)
- Integration connectors — analytics, commerce/CRM, data platforms, CMS (FR-8, §4.1, §4.5)
- Collaboration & workflow management (FR-9, §4.4)
- Custom goals & metric configuration (§4.1)
- User flows §5.1 (set up an A/B test) and §5.2 (analyse behavioural data)
- Non-functional: performance, security, scalability, data privacy, reliability (§7)

**Out of scope:**

- Business KPIs (§8) — post-release business outcomes, not verifiable as product behaviour here
- Pricing & licensing (§9) — commercial, not a product feature
- Future enhancements (§11) — AI suggestion engine, native mobile SDK enhancements, predictive analytics/ROI forecasting
- Detailed negative/validation coverage — the PRD defines no validation rules or error behaviour (see §10)

## 4. Requirements and Planned Coverage

Planned coverage rows use local IDs **PC-01…PC-35**. "Basis in PRD" cites the source; no expected result beyond the PRD is stated. **Status of every row: Not executed.**

| Coverage ID | Requirement | Planned coverage | Type | Priority | Basis in PRD |
| --- | --- | --- | --- | --- | --- |
| PC-01 | FR-1 | Configure and run an A/B test with 2+ variations | Functional | Must | §4.1, §5.1 |
| PC-02 | FR-1 | Configure and run a Split URL test | Functional | Must | §4.1 |
| PC-03 | FR-1 | Configure and run a Multivariate test | Functional | Must | §4.1 |
| PC-04 | FR-1 | Define an experiment with more than two variations | Functional | Must | §4.1 |
| PC-05 | FR-2 | Review SmartStats Bayesian results for an experiment | Functional | Must | §4.1 |
| PC-06 | FR-2 | Review a statistically validated, actionable report | Functional | Must | §4.1 |
| PC-07 | FR-3 | Edit a variation in the visual (WYSIWYG) editor | Functional | Must | §6 FR-3 |
| PC-08 | FR-3 | Edit a variation in the code editor | Functional | Must | §6 FR-3 |
| PC-09 | FR-3 | Preview a variation before launch | Functional | High | §4.1 |
| PC-10 | FR-3 | Run cross-device / cross-browser QA on a variation | Functional | High | §4.1 |
| PC-11 | FR-3 | Schedule an experiment start/stop | Functional | High | §4.1 |
| PC-12 | FR-4 | Generate click, scroll and focus heatmaps | Functional | Must | §4.2 |
| PC-13 | FR-4 | Record and review a session | Functional | Must | §4.2 |
| PC-14 | FR-4 | Publish an on-page survey / feedback widget | Functional | Medium | §4.2 |
| PC-15 | FR-4 | Define a funnel and inspect drop-off points | Functional | Medium | §4.2 |
| PC-16 | FR-5 | Target an audience segment by behaviour/attributes | Functional | High | §4.1 |
| PC-17 | FR-6 | View real-time analytics on a dashboard during an active experiment | Functional | Must | §6 FR-6 |
| PC-18 | FR-7 | Segment users by geography, behaviour and demographics | Functional | High | §4.3 |
| PC-19 | FR-7 | Deliver customised content to a segment in real time | Functional | High | §4.3 |
| PC-20 | FR-8 | Connect and sync an analytics tool (Google Analytics / Mixpanel) | Integration | High | §4.1 |
| PC-21 | FR-8 | Connect and sync a commerce/CRM platform (Shopify / Salesforce) | Integration | High | §4.5 |
| PC-22 | FR-8 | Connect and sync a data platform (Segment / Snowflake) | Integration | High | §4.5 |
| PC-23 | FR-8 | Connect and sync a CMS (WordPress / Drupal) | Integration | Medium | §4.5 |
| PC-24 | FR-9 | Use collaboration tools and move an experiment through the Kanban backlog | Functional | Medium | §4.4 |
| PC-25 | FR-9 | Use the central planning interface for initiatives | Functional | Medium | §4.4 |
| PC-26 | CAP-4 (custom goals) | Configure a custom goal/metric aligned to a KPI | Functional | High | §4.1 |
| PC-27 | NFR-P | Measure an editing workflow against the 2-second target | Performance | Must | §7 |
| PC-28 | NFR-S | Enable and verify two-factor authentication | Security | Must | §7 |
| PC-29 | NFR-S | Verify role-based access control restricts by role | Security | Must | §7 |
| PC-30 | NFR-S | Verify activity logs record actions | Security | Must | §7 |
| PC-31 | NFR-SC | Observe behaviour under high visitor volume | Performance | High | §7 |
| PC-32 | NFR-DP | Review GDPR / CCPA and regional-policy handling | Compliance | High | §7 |
| PC-33 | NFR-R | Review uptime against the 99.9 % SLA | Reliability | High | §7 |
| PC-34 | UF-1 | Execute §5.1 A/B set-up flow end to end (5 steps) | E2E | Must | §5.1 |
| PC-35 | UF-2 | Execute §5.2 behavioural-analysis flow end to end (4 steps) | E2E | High | §5.2 |

**Coverage summary:** all 9 functional requirements, the 5 non-functional categories, both user flows and the named capabilities are mapped to ≥ 1 planned coverage row. No requirement is unmapped. Coverage count: 35.

**Negative / edge coverage:** **Insufficient information to determine** — the PRD defines no validation rules, limits or error behaviour (§10), so no negative/edge coverage can be planned from it.

## 5. Test Approach, Levels, and Types

| Level / type | Applicable? | Notes (supported by scope) |
| --- | --- | --- |
| Functional (UI/behaviour) | Yes | Each FR-1…FR-9 capability exercised (PC-01…PC-26) |
| Integration | Yes | FR-8 connectors and analytics integrations (PC-20…PC-23) |
| End-to-end | Yes | Documented flows §5.1 and §5.2 (PC-34, PC-35) |
| Regression | Yes (planned) | Re-run of Must-priority coverage after changes; scope/threshold to be agreed |
| Performance | Partial | Editing ≤ 2 s (§7) and scalability observation; no quantified volume target (§10) |
| Security | Partial | 2FA, RBAC, activity logs only (§7); no password/session policy in the PRD |
| Compliance | Partial | GDPR/CCPA/regional-policy review only (§7); no mechanism detail in the PRD |
| Reliability | Partial | 99.9 % uptime review (§7) |
| Accessibility / Localization | No | Not in the PRD (Localization gap §10) |

**Approach.** Coverage is derived only from PRD-stated capabilities. Each planned coverage item restates the PRD behaviour as its expected outcome. Because the PRD is capability-level, execution-level steps (fields, error text, exact thresholds) require supplementary specs before execution.

## 6. Environment, Tools, Access, and Test Data

**Confirmed requirements (from PRD):** a VWO account/workspace, at least one testable web property, defined audience segments and target metrics (§4.1, §5.1).

| Item | Status |
| --- | --- |
| Environment / staging URL | **Not provided** — only the product URL https://app.vwo.com/ is stated |
| Browser / OS / device matrix | **Not provided** → *Proposed (Inference — low confidence):* current Chrome, Firefox, Edge, Safari on desktop; one iOS and one Android device |
| Tooling (test management / automation) | **Not provided** — plan is tool-agnostic |
| Test accounts & data | **Not provided** — account provisioning, seeding and tenant setup undefined |
| Access / roles | Roles exist (2FA/RBAC, §7) but naming/permissions **Not provided** |
| Secrets handling | Credentials must stay out of artefacts; supply via environment/secret mechanism (**Prohibited practice**) |

## 7. Entry and Exit Criteria

The PRD defines no acceptance thresholds; only §7's "≤ 2 s editing" and "99.9 % uptime" are quantified. The rest are **proposals requiring agreement**.

| Criterion | Statement | Basis |
| --- | --- | --- |
| **Entry** | VWO account/workspace, ≥ 1 testable web property, defined segments and target metrics are available | Confirmed from §4.1, §5.1 (specifics Not provided) |
| **Exit** | All Must-priority planned coverage executed; no open Critical/High defects | *Proposed (Inference — low confidence)* — PRD defines none |
| **Performance exit** | Editing workflow responds within 2 seconds | Confirmed §7 |
| **Reliability exit** | Review against 99.9 % uptime SLA | Confirmed §7 |
| **Other thresholds** | Not provided | PRD silent |

## 8. Roles, Responsibilities, Estimates, and Schedule

**Confirmed stakeholder groups (from PRD §2–§3):** Digital Product Managers, UX/UI Designers, Growth & Marketing, Data Analysts/CRO Specialists, Engineering/DevOps (primary/secondary users listed in §3).

**Not provided / proposed:** specific test owners, RACI, estimates, timelines and schedule are **Not provided**. *Proposed (Inference — low confidence):* a QA Lead owns the plan; feature owners per FR area; a sprint-aligned schedule agreed with the product owner.

## 9. Defect Management and Reporting

The PRD defines no defect model. The following are **proposals requiring agreement**:

- **Severity:** Critical (capability unusable) / High (Must requirement broken) / Medium (High requirement affected) / Low (cosmetic).
- **Priority:** aligned to FR priority (Must / High / Medium) and business impact.
- **Reporting cadence:** defect log updated per test cycle; summary at cycle close.
- **Tracking:** defect ID, requirement ID, environment, steps, expected (PRD basis), actual, severity, priority, status. Actual results are recorded only when a test has been executed.

## 10. Risks, Dependencies, Assumptions, and Open Questions

**Risks (as stated in PRD §10):**

| Risk | PRD mitigation | Test implication |
| --- | --- | --- |
| Technical Complexity | Robust SDKs/docs, pre-built templates | Verify documented setup paths (PC-01, PC-07, PC-08) |
| Data Accuracy Challenges | SmartStats + cross-tool validation | Verify Bayesian results and integration consistency (PC-05, PC-06, PC-20) |
| User Adoption | Guided tours, in-app support, analyst assistance | Usability checks via E2E flows (PC-34, PC-35) |

**Confirmed gaps — "Insufficient information to determine":**

1. No acceptance criteria for any requirement (capability-level only).
2. No UI specification — no screens, field labels, button text, component behaviour.
3. No error codes, messages or validation rules — negative-path expectations cannot be derived.
4. No API specification — no endpoints, payloads, auth flows or rate limits.
5. No supported browser/OS/device list (cross-device/cross-browser QA stated but not enumerated).
6. No quantified performance target beyond "≤ 2 s editing"; "high visitor volumes" unquantified.
7. No security detail beyond 2FA, RBAC and activity logs.
8. No data-retention, consent or privacy mechanism detail behind GDPR/CCPA.
9. No environment/hosting details — no staging URL, account setup or data seeding.
10. No experiment limits — max variations, traffic-split precision, statistical thresholds.
11. No localization/language requirements.
12. Pricing (§9) is external and not a testable product feature.

**Proposed assumptions (Inference — low confidence):**

- A staging/test environment exists and mirrors production behaviour.
- At least one web property can be instrumented for experiments.
- Regression scope follows changes to FR-1…FR-9.

**Open questions (require answers before execution):**

- Which environment/URL and provisioned test accounts will be used?
- Which browsers, OSes and devices are in the target matrix?
- What volume defines "high visitor volumes" (PC-31)?
- What regression scope and exit thresholds are agreed?
- Who owns each FR area, and what is the schedule?

## 11. Suspension and Resumption Criteria

**Proposed (Inference — low confidence)** — the PRD defines none:

- **Suspend** if the environment is unavailable, a blocking defect prevents progress, or a requirement conflict is unresolved.
- **Resume** when the blocking condition is cleared and the affected scope is re-confirmed.

## 12. Test Deliverables and Approval

**Deliverables (planned):**

- This test plan (TP-VWO-001)
- The prompt used: `Test Plan Prompt using RICEPOT template.md`
- Requirements → planned coverage matrix (§4)
- Defect log (per §9), once execution begins
- Test summary report at cycle close (proposed)

**Approval:**

| Role | Name | Decision | Date |
| --- | --- | --- | --- |
| QA Lead | *(to be assigned)* | ☐ Approve ☐ Revise | |
| Product Owner | *(to be assigned)* | ☐ Approve ☐ Revise | |

*Owners are Not provided in the PRD and must be assigned before execution.*

---

# Appendix — Template Compliance (review checklist)

From `04_RICE_POT_Generic_QA_Template.md` §6:

| # | Checklist item | Status |
| --- | --- | --- |
| 1 | All placeholders filled or explicitly marked Not provided | **pass** — every `{{PLACEHOLDER}}` resolved; unavailable inputs marked |
| 2 | Selected task profile matches the requested deliverable | **pass** — Test plan profile |
| 3 | Plan names the exact sections/files to be created | **pass** — §1–§12; file `Test Plan using RICEPOT template.md` |
| 4 | Required clarification and plan approval complete | **pass** — clarifications answered; plan approved before generation |
| 5 | Scope, counts, constraints and output instructions agree | **pass** — 35 planned coverage items; scope = §4–§7 |
| 6 | Requirements traceable; proposed assumptions visible | **pass** — §4 matrix; §10 separates assumptions/questions |
| 7 | Expected results observable and based on supplied requirements | **pass** — each row cites the PRD |
| 8 | Credentials and secrets absent from the artefact | **pass** — none present |
| 9 | Generated work distinguished from executed work | **pass** — status "Planned — not executed" |
| 10 | Applicable checks reported accurately, gaps identified | **pass** — gaps listed in §10 |
| 11 | Final artefact uses requested fields, filenames, format and tone | **pass** — test-plan structure, Markdown |

**Overall:** the plan follows the RICE POT template's test-plan profile, traces 35 planned coverage items to the PRD, and declares the PRD's gaps rather than inventing detail. No tests have been executed.