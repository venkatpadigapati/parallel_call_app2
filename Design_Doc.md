## **Design Document: Custom VS Code Plugin for Compatibility Testing**

### **Overview**

Our organization uses a VS Code Private Marketplace to manage extensions, as the public marketplace is blocked for all users. This Private Marketplace allows users to search and download extensions seamlessly. However, with each VS Code version update, manually verifying the compatibility of extensions hosted in the Private Marketplace is time-consuming and labor-intensive.

To streamline this process, we propose developing a custom VS Code plugin. This plugin will automate the compatibility testing of extensions by installing, validating, and uninstalling them programmatically. It will also save the results in a MongoDB database for analysis and generate reports for further actions.

---

### **Problem Statement**

Each time VS Code updates to a new version, we must manually verify the compatibility of all extensions in the Private Marketplace. This process involves installing and uninstalling each extension to check for compatibility, which is inefficient and prone to errors.

---

### **Solution**

We aim to develop a custom VS Code plugin that automates compatibility testing. The plugin will:

- Fetch extension metadata from the Private Marketplace.
- Install and validate each extension.
- Uninstall extensions after validation.
- Save compatibility results to a MongoDB collection for analysis.
- Provide APIs to fetch and download test results.

---

### **Workflow Overview**

The plugin workflow can be divided into the following phases:

1. **Activation Phase:**

   - The extension is activated and loads its configuration.
   - The compatibility check process is initiated.

2. **Compatibility Testing Phase:**

   - The plugin fetches metadata for all extensions from the Private Marketplace.
   - For each extension, the following steps are executed:
     - **Install Extension:** Programmatically install the extension.
     - **Verify Installation:** Check if the extension was successfully installed.
     - **Check Compatibility:** Validate the extension against the current VS Code version.
     - **Ensure Activation:** Confirm that the extension is active.
     - **Uninstall Extension:** Remove the extension after the test.
   - Results are saved in a MongoDB collection.

3. **Result Reporting Phase:**

   - Generate a detailed report summarizing successful and failed compatibility tests.
   - Provide APIs for report retrieval and analysis.

---

### **Workflow Diagram**

```
  +------------------+
  |  VS Code IDE     | <--- (Actor)
  +------------------+
            |
            v
  +------------------+
  | Custom Plugin    | <--- (Process Start)
  | Activated        |
  +------------------+
            |
            v
  +------------------+
  | Fetch Metadata   | <--- (Process)
  | from API         |
  +------------------+
            |
            v
  +------------------+
  | Iterate Over     | <--- (Decision)
  | Each Extension?  |
  +------------------+
            | Yes                   No
            v                      +------------------+
  +------------------+             | End Process      |
  | Install Extension|             +------------------+
  +------------------+
            |
            v
  +------------------+
  | Verify Installed?| <--- (Decision)
  +------------------+
            | Yes                   No
            v                      +------------------+
  +------------------+             | Log Failure      |
  | Check Compatibility|           +------------------+
  +------------------+
            |
            v
  +------------------+
  | Ensure Active    | <--- (Process)
  +------------------+
            |
            v
  +------------------+
  | Uninstall        | <--- (Process)
  +------------------+
            |
            v
            |
  +------------------+
  | Next Extension?  | <--- (Decision)
  +------------------+
            | Yes                   No
            v                      +------------------+
  +------------------+             | Send Results     |
  | Repeat Process   |             | to API Server    |
  +------------------+             +------------------+
                                    |
                                    v
                            +------------------+
                            | Store Results    |
                            | in MongoDB       |
                            +------------------+
```

---

### **APIs**

1. **Fetch Extensions**

   - **Endpoint**: `{{baseUrl}}/vscode/extension`
   - **Method**: GET
   - **Description**: Fetches metadata of all extensions from the `VsCodeExtensions` collection.

   **Sample Response:**

   ```json
   {
     "extensions": [
       {
         "_id": "1232f",
         "name": "sample-extension",
         "version": "1.0.0",
         "publisher": "samplePublisher",
         "engines": {
           "vscode": "^1.78.0"
         }
       }
     ]
   }
   ```

2. **Save Test Results**

   - **Endpoint**: `{{baseUrl}}/vscode/extension/compatibilityTestResult`
   - **Method**: POST
   - **Body**:
     ```json
     {
       "ideType": "VSCode",
       "ideVersion": "1.95",
       "result": "Success",
       "outputMessage": "",
       "createdDate": "timestamp"
     }
     ```
   - **Description**: Saves compatibility test results to the MongoDB collection.

3. **Download Test Results**

   - **Endpoint**: `{{baseUrl}}/vscode/extension/downloadCompatibilityTestResult`
   - **Method**: GET
   - **Query Params**:
     - `ideType` (optional)
     - `ideVersion` (optional)
     - `result` (optional)
     - `date` (optional)
   - **Description**: Downloads test results as a CSV file.

---

### **MongoDB Schema**

**Collection**: `plugin_compatibility_test_results`

**Sample Document:**

```json
{
  "_id": "1234",
  "ideType": "VSCode",
  "ideVersion": "1.95",
  "extensionId": "samplePublisher.sample-extension",
  "result": "Success",
  "outputMessage": "",
  "createdDate": "2024-12-10T00:00:00Z"
}
```

---

### **Implementation Details**

#### **VS Code Engine Overview**

The VS Code engine provides APIs to programmatically manage extensions. Commands such as `workbench.extensions.installExtension` and `workbench.extensions.uninstallExtension` are used to install and uninstall extensions. The engine checks compatibility by validating the `engines.vscode` field in the extension's `package.json` against the current VS Code version.

#### **Sample Code Snippet: Install Extension**

*Installs a VS Code extension programmatically.*

```javascript
async function installExtension(extensionId) {
  return await vscode.commands.executeCommand('workbench.extensions.installExtension', extensionId);
}
```

#### **Sample Code Snippet: Check if Extension is Installed**

*Validates if the extension is currently installed in VS Code.*

```javascript
function isExtensionInstalled(extensionId) {
  return !!vscode.extensions.getExtension(extensionId);
}
```

#### **Sample Code Snippet: Check Compatibility**

*Verifies if an extension is compatible with the current VS Code version by reading its metadata and comparing the `engines.vscode` version against the current VS Code version.*

```javascript
async function checkCompatibility(extensionId) {
  const metadataPath = `${process.env.HOME}/.vscode/extensions/${extensionId}/package.json`;
  if (fs.existsSync(metadataPath)) {
    const packageJson = JSON.parse(fs.readFileSync(metadataPath, 'utf8'));
    const requiredVersion = packageJson.engines?.vscode;
    return semver.satisfies(vscode.version, requiredVersion);
  }
  return false;
}
```

#### **Sample Code Snippet: Uninstall Extension**

*Uninstalls a VS Code extension programmatically.*

```javascript
async function uninstallExtension(extensionId) {
  await vscode.commands.executeCommand('workbench.extensions.uninstallExtension', extensionId);
}
```

---

### **Future Enhancements**

1. Support for multiple IDEs beyond VS Code.
2. Detailed logging and error handling for each step.
3. Improved reporting with graphical dashboards for compatibility results.
4. Notifications for incompatible extensions to relevant teams.

---

### **Conclusion**

By automating the compatibility testing of extensions, this custom plugin will save time, reduce manual effort, and ensure seamless user experiences with the VS Code Private Marketplace. The integration of MongoDB for data storage and APIs for result retrieval will provide robust support for analysis and decision-making.

