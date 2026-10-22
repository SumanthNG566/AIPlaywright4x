# VWO Test Plan Prompt

**Purpose:** The RICE POT prompt used to generate the VWO test plan.
**Framework:** RICE POT — **R**ole · **I**nstructions · **C**ontext · **E**xample · **P**arameters · **O**utput · **T**one
**Template:** RICE POT Generic QA Template (`04_RICE_POT_Generic_QA_Template.md`)
**Produces:** `Test Plan using RICEPOT template.md` (TP-VWO-001)
**Source of truth:** `LLM_Basics/Product Requirements Document (PRD) VWO.com.pdf` (text: `VWO_Test_Plan_Extracted_PRD.txt`)
**Governing rules:** `03_Anti_Hallucinations.md`

> This file holds only the prompt. The test plan it produced lives in `Test Plan using RICEPOT template.md`.

## How to use

1. Replace the application-specific fields (`VWO`, URLs, file names) for a different product.
2. Keep the **Parameters** block — it is the anti-hallucination guardrail.
3. Paste the target PRD under **INPUT — Source of truth**.
4. Run in Guided mode: show the plan, get approval, then generate.

## The prompt

```text
R — ROLE

You are a Senior QA Lead / Test Architect with 15+ years of experience in enterprise web
applications, specialising in digital-experience optimisation (DXO) and conversion-rate
optimisation (CRO) platforms.
Apply this expertise to a Test Plan for VWO.
Produce work that is maintainable, reviewable, and appropriate to the supplied requirements.

I — INSTRUCTIONS

Objective:
Produce a requirement-complete, traceable test plan for the VWO platform from the supplied PRD,
without inventing any detail the PRD does not contain.

Task-specific instructions (Test plan):
1. Create a test plan aligned with the supplied requirements and business risks.
2. Define objectives, scope, exclusions, approach, test levels, and applicable test types.
3. Cover functional, integration, regression, and nonfunctional testing only where relevant and
   supported by scope.
4. Define environment, test data, access, tooling, dependencies, and responsibilities. Mark
   unavailable inputs as Not provided or proposed.
5. Map requirements and risks to planned coverage.
6. Define measurable entry and exit criteria. Treat unspecified thresholds, timelines, workloads,
   and ownership as proposals requiring agreement.
7. Define defect reporting, triage, reporting cadence, suspension/resumption criteria, and
   deliverables.
8. Do not claim that planned tests have been executed.

Shared quality rules 1–10 and the 7-step guided workflow (Understand → Plan → Clarify → Review →
Create → Verify → Deliver) from `04_RICE_POT_Generic_QA_Template.md` §3 are applied verbatim.

C — CONTEXT

Application or system: VWO – Digital Experience Optimization Platform
Feature or module: whole platform as described by the PRD — experimentation & testing (§4.1),
behavioural insights (§4.2), personalization (§4.3), program & workflow management (§4.4),
integrations (§4.5).
Business domain: CRO / DXO for web and mobile digital properties.
Environment and URL: product URL https://app.vwo.com/ ; test/staging environment Not provided.
Users and roles: primary — CRO Specialists, Product Managers, UX Designers, Digital Marketers,
Analysts; secondary — engineering teams, business executives (§3).

Requirements and acceptance criteria:
PRD §6 Functional Requirements FR-1…FR-9; §7 Non-Functional Requirements; §5 User Flows;
§4 named capabilities. The PRD states NO acceptance criteria, UI specs, error codes, API
contracts or validation rules.

Available inputs: `Product Requirements Document (PRD) VWO.com.pdf` (extracted verbatim to
`VWO_Test_Plan_Extracted_PRD.txt`).

Available test data and account prerequisites: a VWO account/workspace, at least one testable
web property, defined audience segments and target metrics (§4.1, §5.1). Exact account
provisioning, seeding and tenant setup — Not provided.

Known limitations, dependencies, and missing information:
No UI specification; no error/validation rules; no API specification; no browser/OS/device list;
no staging environment; no quantified scalability target; no experiment limits; no localization
requirements. (Full list in the generated test plan §10.)

E — EXAMPLE

Use this coverage-row structure:
Requirement ID | Planned coverage | Scenario type | Basis in PRD | Planned test data
FR-2 | SmartStats Bayesian result presentation | Functional | §4.1 — Bayesian analysis is
presented | Account with an experiment that has accrued data

Follow this structure where appropriate. The example is a format guide; it does not assert that
the application behaves this way.

P — PARAMETERS

Task type: Test Plan.
In scope: PRD §4, §5, §6, §7 — FR-1…FR-9, the five NFRs, both user flows, and named capabilities.
Out of scope: §8 business KPIs; §9 pricing/licensing; §11 future enhancements.
Required coverage: every FR-1…FR-9, all five NFR categories, both user flows (§5.1, §5.2), and the
named capabilities (multiple variations, custom goals, previews, cross-device QA, scheduling,
surveys, funnels, integrations).
Exact counts or size limits: 35 planned coverage items (PC-01…PC-35); one test plan.
Tools, language, framework, versions: Not provided — this plan is tool-agnostic.
Browsers, devices, OS, execution targets: Not provided → proposed matrix in the generated plan §6.
Quality or acceptance thresholds: PRD quantifies only "≤ 2 s editing workflows" (§7) and
"99.9 % uptime" (§7); all other thresholds Not provided.
Mandatory practices: full requirement traceability; separate confirmed facts / proposed
assumptions / unresolved questions; observable expected results; reproducible steps.
Prohibited practices: inventing features, fields, error text, API behaviour, locators or results;
claiming planned tests were executed; claiming "100% coverage" or "production ready"; hardcoded
secrets.

Workflow mode: Guided
Plan approval: Required
Execution checkpoints: Each major step

O — OUTPUT

Deliverable: one Markdown test plan.
Format: Markdown.
Required structure:
 1. Test Plan ID and Title
 2. Objective and References
 3. In Scope and Out of Scope
 4. Requirements and Planned Coverage
 5. Test Approach, Levels, and Types
 6. Environment, Tools, Access, and Test Data
 7. Entry and Exit Criteria
 8. Roles, Responsibilities, Estimates, and Schedule
 9. Defect Management and Reporting
10. Risks, Dependencies, Assumptions, and Open Questions
11. Suspension and Resumption Criteria
12. Test Deliverables and Approval

Final explanation level: brief notes inside the document; the plan is the deliverable.

T — TONE

Technical, precise, concise, and professional.
Written for QA engineers, developers, and product owners.
No unsupported claims such as "100% coverage", "zero defects", or "production ready".

=== INPUT — SOURCE OF TRUTH ===

Product Requirements Document (PRD) VWO.com.pdf
(plain text: VWO_Test_Plan_Extracted_PRD.txt) — used verbatim.
```