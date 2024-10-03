Here's the formatted text based on the given 5 steps:

# Guidelines to Manage Secrets in Local Environment
==================================================

## Overview
There are instances where we have encountered sensitive data not following proper guidelines. As most users have started using Copilot, such data can get exposed and used for analysis. The following document lists some guidelines we can follow while storing sensitive data.

## Types of Secrets
Many types of secrets exist. Among the more common types are:
- High availability secrets (Tokens that are difficult to rotate)
- Application configuration files
- Connection strings
- API keys
- Credentials
- Passwords
- 2FA keys
- Private keys (e.g., SSH keys)
- Session tokens
- Platform-specific secret types

## Guidelines to Manage Secrets

### 1. Secrets in Environment Variables
Putting secrets and configuration in environment variables helps mitigate the damage of more common breaches, eliminating classes of mistakes that can lead to security vulnerabilities, and making deployment simple and flexible. Secrets are put into environment variables so they never touch disk when deployed. This mitigates the damage if an attacker:

a. Gains read-access to your production server's filesystem  
b. Gains read-access to your code  
c. Can execute code, even your code, on your production servers  

#### Step-by-Step: Setting Environment Variables in IntelliJ IDEA
1. **Open the Project** in IntelliJ IDEA.
2. Go to **Run** > **Edit Configurations**.
3. In the **Run/Debug Configurations** dialog, select your desired configuration.
4. Scroll down to the **Environment** section and click on the **Environment Variables** field.
5. Click the **...** button to open the **Environment Variables** dialog.
6. **Add a new variable** by clicking on the **+** icon. Specify the **Name** (e.g., `SECRET_KEY`) and the **Value** (e.g., an encrypted or placeholder value).

   ![IntelliJ Set Environment Variable](attachment:intellij_environment_variable.png)

#### Step-by-Step: Setting Environment Variables in Visual Studio Code
1. **Open the Command Palette** (Ctrl+Shift+P or Cmd+Shift+P on macOS).
2. Type `Preferences: Open Settings (UI)` and open it.
3. **Search for Terminal Settings**:
   - Search for **Terminal > Integrated > Env**.
4. **Add Environment Variables**:
   ```json
   "terminal.integrated.env.windows": {
       "SECRET_KEY": "your_secret_here"
   },
   "terminal.integrated.env.linux": {
       "SECRET_KEY": "your_secret_here"
   },
   "terminal.integrated.env.osx": {
       "SECRET_KEY": "your_secret_here"
   }
   ```

   ![VS Code Set Environment Variable](attachment:vscode_environment_variable.png)

### 2. Secrets in `.gitignore` File
It's a common method to store sensitive data apart from the version control system. You can find numerous records about secrets in `.gitignore` files in GitHub. This method is typical because it's simple. The main drawback is that secrets are being kept in plaintext. If your host has been compromised, all these secrets can leak. Although it's risky to keep secrets in plaintext, adding them to `.gitignore` is still preferable to hardcoding secrets directly into the source code.

#### Step-by-Step: Adding Sensitive Files to `.gitignore`
- **Create/Edit the `.gitignore` File**:
  - Right-click on the **project root** and choose **New** > **File** to create `.gitignore` if it doesn't already exist.
  - Add the following entries to ignore sensitive files:
  ```
  *.env
  application.properties
  secrets.yml
  ```

### 3. Encrypt Secrets
There are built-in solutions for secrets in development platforms or IaC tools like **Hashicorp Vault**. They encrypt the sensitive values before sending them to the source control system. In source codes, it looks like cipher strings. It is a convenient and secure method, but key protection solely relies on encryption. Any mistake in the encryption algorithm can reveal all your keys at once.

#### Step-by-Step: Encrypting Secrets Using Plugins
##### IntelliJ IDEA
1. **Install the Vault Plugin**:
   - Go to **File** > **Settings** > **Plugins**.
   - Search for and install **Vault**.
2. **Configure Vault**:
   - Go to **Tools** > **Vault** and configure it to store and retrieve secrets securely.

##### Visual Studio Code
1. **Install the Vault or Azure Key Vault Extension**:
   - Go to the **Extensions** Marketplace.
   - Search for and install **Vault** or **Azure Key Vault**.
2. **Configure the Extension**:
   - Follow the provided documentation within the extension to store and manage secrets securely.

### 4. Using Test Data Instead of Real Data
To minimize the risks of exposing real data during testing, it is recommended to use **test data** rather than real PII or sensitive information.

#### Best Practices
- **Create a Separate Environment File for Testing**: Create a `test-config.properties` or `.env.test` file for non-sensitive values during testing.
- **Reference Test Data in IDEs**:

  
  #### Step-by-Step: Referencing Test Data in IntelliJ IDEA
1. **Open the Project** and go to **Run** > **Edit Configurations**.
2. **Add a New Configuration**:
   - Click the **+** icon to add a new configuration (e.g., **Application** or **JUnit**).
   - Name it **Test Environment**.
3. **Set Environment Variables or Program Arguments**:
   - In the **Environment Variables** field, click **...**, then click **+** to add a variable (e.g., `CONFIG_FILE` with the value `path/to/your/test-config.properties`).
   - Alternatively, enter the file path in **Program Arguments** (e.g., `--config-file=path/to/your/test-config.properties`).
4. **Apply and Run**:
   - Click **Apply** and **OK** to save.
   - Select **Test Environment** from the drop-down list in the toolbar and click **Run**.

  - In Visual Studio Code, update the `launch.json` or `settings.json` to reference the `.env.test` file:
  ```json
  "envFile": "${workspaceFolder}/.env.test"
  ```
- **Disable GitHub Copilot on Sensitive Files**:
  - In **VS Code**, add the following to your `settings.json` to prevent Copilot suggestions for sensitive files:
  ```json
  "github.copilot.excludeLanguages": {
      "plaintext": true,
      "json": true
  }
  ```

By following these guidelines and setting up your environment according to the steps above, you can minimize the risk of accidental exposure of sensitive information while benefiting from tools like GitHub Copilot.
