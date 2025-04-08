

## 🧠 Model Selection by Use Case (with Examples)

| **Use Case**                  | **Recommended Model(s)**     | **Strengths**                                                   | **Example to Try**                                                                                                   |
|------------------------------|------------------------------|------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| **Legacy Code Migration**     | Claude 3.7, GPT-4o           | Claude for context/safety; GPT-4o for syntax-focused migration  | 🧾 Migrate Java 7 code using `for` loops to Java 17 streams. <br>🗨️ Prompt: *"Migrate this method to use modern Java syntax with streams."* |
| **Test Case Generation**      | GPT-4o, Claude 3.5           | GPT-4o for functional tests; Claude for secure/edge-case tests  | 🧾 Method that calculates tax based on salary. <br>🗨️ Prompt: *"Generate JUnit 5 test cases including edge cases."* |
| **Code Understanding**        | Claude 3.7, Gemini Flash     | Claude for safe explanations; Gemini for cross-domain logic     | 🧾 Complex recursive tree traversal function. <br>🗨️ Prompt: *"Explain how this function works, step by step."* |
| **Code Refactoring**          | o1, GPT-4o                   | o1 for performance; GPT-4o for simplification                   | 🧾 A class with nested `if-else` and repeated code. <br>🗨️ Prompt: *"Refactor this to improve readability and reduce duplication."* |
| **Code Review**               | Claude 3.7, 03-mini          | Claude for security/best practices; 03-mini for quick feedback  | 🧾 Pull request with file handling and logging logic. <br>🗨️ Prompt: *"Review this code for potential issues and improvements."* |
| **Multi-Language Development**| GPT-4o, Gemini Flash         | GPT-4o for translation; Gemini for integration                  | 🧾 Node.js code that consumes a REST API. <br>🗨️ Prompt: *"Convert this JavaScript code to Python with equivalent logic."* |
| **Documentation & Learning**  | GPT-4o, Claude 3.5           | GPT-4o for clarity; Claude for accuracy                         | 🧾 A method that parses CSV files and filters data. <br>🗨️ Prompt: *"Write documentation explaining how this method works."* |
| **Debugging**                 | o1, GPT-4o                   | o1 for performance issues; GPT-4o for logical tracing           | 🧾 Code with a `NullPointerException` or off-by-one bug. <br>🗨️ Prompt: *"Find and fix the bug in this code."* |
| **Bug Detection & Fixing**    | Claude 3.7, GPT-4o           | Claude for safe fixes; GPT-4o for quick detection               | 🧾 Form validation logic that skips certain inputs. <br>🗨️ Prompt: *"Fix any validation issues in this function."* |
| **API Integration**           | Gemini Flash, GPT-4o         | Gemini for orchestration; GPT-4o for boilerplate                | 🧾 Integrate a weather API and parse the response. <br>🗨️ Prompt: *"Add error handling and retry logic to this integration."* |
| **Infrastructure as Code (IaC)** | GPT-4o, Claude 3.7        | GPT-4o for Terraform/YAML; Claude for secure configs            | 🧾 Terraform file creating an EC2 instance. <br>🗨️ Prompt: *"Verify this Terraform script for best practices and compliance."* |
| **DevOps & CI/CD Automation** | Gemini Flash, o1             | Gemini for pipelines; o1 for tuning and scripting               | 🧾 A GitHub Actions YAML with build and deploy steps. <br>🗨️ Prompt: *"Optimize this workflow and add caching."* |
| **Code Generation**           | GPT-4o, Claude 3.5           | GPT-4o for rich code; Claude for chat-based generation          | 🧾 Prompt: *"Create a function to calculate loan EMI with principal, rate, and term as inputs."* <br>Try both models and compare for verbosity vs. clarity. |

---

### 💡 Tips for Comparing Models in VS Code

To effectively compare model responses:

1. **Open the same code file** or paste the same snippet into the Copilot Chat window.
2. **Select a different model** from the dropdown each time.
3. **Use the same prompt** with each model.
4. **Screenshot or copy-paste the output** for comparison.

📸 You can display these comparisons side-by-side in your documentation:
- 🧩 Claude 3.7’s explanation of the method
- 🧩 GPT-4o’s refactored version of the same code

---

Would you like the full document exported in a specific format (Word, PDF, or Markdown)?  
Also, I can bundle all the related **example `.java`, `.yaml`, `.js` files** into a zip if you haven't downloaded it yet.

Let me know!
