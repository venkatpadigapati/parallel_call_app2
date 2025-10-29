### **GitHub Copilot Coding Agent**

We are proposing to enable the **[GitHub Copilot Coding Agent](https://docs.github.com/en/copilot/about-github-copilot/understanding-github-copilot-agents)** feature as part of GitHub Copilot for Business to enhance developer productivity and automate common coding workflows. This feature extends the existing Copilot IDE capabilities by introducing a **task-based AI agent** that can assist with coding, testing, and refactoring within a secure enterprise setup.

The Copilot Coding Agent helps developers **automate repetitive coding tasks**, generate and validate code across multiple files, and apply consistent best practices. It acts as an intelligent assistant that can interpret user intent, perform reasoning on the codebase, and produce reliable outputs. By doing so, it improves development speed and overall efficiency while maintaining compliance with enterprise data policies.

The feature leverages the **same secure architecture** as the existing Copilot for Business implementation, with **authentication handled via GitHub Enterprise SSO**. All activities occur within the **isolated enterprise boundary**, ensuring **no data is transmitted outside** approved repositories or environments.
For more details on technical architecture, refer to [Indexing Repositories for Copilot Chat](https://docs.github.com/en/enterprise-cloud@latest/copilot/customizing-copilot/indexing-repositories-for-copilot-chat).

Enabling this feature will allow Wells Fargo developers to **deliver high-quality code faster**, improve test coverage, and reduce manual effort, aligning with our strategic goals around developer enablement and productivity.

---

### **GitHub Copilot Spaces**

We are proposing to enable the **[GitHub Copilot Spaces](https://github.blog/news-insights/product-news/introducing-copilot-spaces/)** feature, which provides developers with a collaborative, AI-powered workspace integrated into GitHub. Copilot Spaces allows teams to **create, edit, and experiment with code collaboratively in real time**—all within a secure GitHub environment—without needing to configure local environments.

This feature introduces an interactive workspace where Copilot can assist multiple developers simultaneously, helping with **prototyping, debugging, and sharing code logic**. It also provides context-aware AI suggestions within a shared development space, enabling teams to explore ideas faster and **accelerate project onboarding and reviews**.

From a compliance perspective, Copilot Spaces leverages **GitHub’s existing enterprise-grade security and identity management**. All sessions are authenticated through **GitHub Enterprise SSO**, and data remains confined within the enterprise repository boundaries. No source code or metadata leaves the Wells Fargo-controlled GitHub environment.
More information on Copilot Spaces is available in the [GitHub Blog Announcement](https://github.blog/news-insights/product-news/introducing-copilot-spaces/).

By enabling Copilot Spaces, development teams can **enhance collaboration, reduce setup friction**, and **speed up innovation cycles**, all while maintaining compliance with Wells Fargo’s security standards.
