# GitHub Copilot Agent Best Practices

This document outlines best practices for creating and using agents with GitHub Copilot to ensure effective outcomes and adherence to standard guidelines.

---

## 1. Define Clear Agent Instructions

- **Be explicit and specific**: Provide clear, unambiguous instructions that define the agent's role, responsibilities, and scope.
- **Use structured prompts**: Organize instructions with headers, bullet points, and numbered lists to improve readability and comprehension.
- **Limit scope**: Each agent should have a single, well-defined responsibility to avoid confusion and overlapping concerns.
- **Include context**: Provide relevant project context (language, framework, coding standards) so the agent can generate contextually appropriate responses.

## 2. Follow Standard Naming Conventions

- Name agents using descriptive, lowercase hyphenated identifiers (e.g., `code-reviewer`, `test-generator`, `requirement-assessment`).
- Use the `.agent.md` file extension for agent configuration files stored under `.github/agents/`.
- Keep file names consistent with the agent's purpose and role.

## 3. Structure Agent Files Consistently

- Store all agent configuration files under `.github/agents/` for discoverability.
- Use a consistent Markdown structure:
  - **Title**: Agent name and brief description.
  - **Role**: The agent's designated role and primary goal.
  - **Instructions**: Detailed behavioral guidelines.
  - **Constraints**: Explicit limitations and out-of-scope activities.
  - **Examples** (optional): Sample inputs and expected outputs.

## 4. Write Precise and Actionable Instructions

- Use imperative language (e.g., "Generate", "Analyze", "Review") to direct agent behavior.
- Avoid ambiguous terms; prefer concrete, verifiable actions.
- Break complex tasks into smaller, sequential steps to guide the agent through multi-step workflows.
- Specify output formats (e.g., JSON, Markdown, plain text) to ensure consistent, predictable results.

## 5. Incorporate Safety and Quality Guards

- Instruct agents to **avoid generating harmful, insecure, or non-compliant code**.
- Require agents to follow the project's established coding standards and security guidelines.
- Include instructions to validate inputs and handle edge cases gracefully.
- Mandate that agents flag uncertainty rather than generating potentially incorrect output.

## 6. Leverage Context Files

- Use `.github/copilot-instructions.md` to provide project-wide context that applies to all agents.
- Reference relevant project documentation, architecture decisions, and domain terminology within agent instructions.
- Keep context files updated as the project evolves to ensure agents remain aligned with current standards.

## 7. Design for Iterative Refinement

- Treat agent instructions as living documents; review and update them regularly based on observed outcomes.
- Use version control to track changes to agent files, enabling rollback if behavior regresses.
- Gather feedback from team members and incorporate improvements continuously.

## 8. Test Agent Behavior

- Validate agent instructions with representative test cases before deploying to production workflows.
- Include both common cases and edge cases to ensure robust behavior.
- Document known limitations and expected failure modes to set accurate expectations.

## 9. Maintain Security and Privacy

- **Never include sensitive data** (credentials, API keys, PII) in agent instructions or context files.
- Restrict agent access to only the information and tools necessary for its task (principle of least privilege).
- Review agent-generated content before committing it to the repository to prevent accidental exposure of sensitive information.

## 10. Ensure Traceability and Documentation

- Document the purpose and expected behavior of each agent in its configuration file.
- Log significant agent interactions and outcomes for audit and debugging purposes.
- Link agent files to related issues, pull requests, or documentation where applicable.

## 11. Optimize for Team Collaboration

- Share agent configurations across the team via the repository to ensure consistent usage.
- Use pull requests to propose and review changes to agent instructions, applying the same rigor as code reviews.
- Provide onboarding documentation for new team members explaining how agents are used in the project.

## 12. Align with GitHub Copilot Guidelines

- Follow [GitHub Copilot's official documentation](https://docs.github.com/en/copilot) for the latest features and best practices.
- Stay informed about updates to Copilot capabilities and incorporate new features where beneficial.
- Ensure usage complies with your organization's GitHub Copilot policies and licensing agreements.

---

## Quick Reference Checklist

| Best Practice | Description |
|---|---|
| Clear instructions | Explicit, unambiguous agent role and scope |
| Consistent naming | Lowercase hyphenated names, `.agent.md` extension |
| Structured files | Stored in `.github/agents/`, consistent Markdown format |
| Actionable language | Imperative verbs, defined output formats |
| Safety guards | Avoid harmful/insecure output, validate inputs |
| Context files | Use `copilot-instructions.md` for project-wide context |
| Iterative refinement | Version-controlled, regularly reviewed instructions |
| Testing | Validate with representative test cases |
| Security & privacy | No sensitive data, principle of least privilege |
| Traceability | Documented purpose, logged interactions |
| Team collaboration | Shared via repository, reviewed via pull requests |
| Copilot alignment | Follow official GitHub Copilot guidelines |

---

*For project-wide Copilot instructions, see [`.github/copilot-instructions.md`](.github/copilot-instructions.md).*
