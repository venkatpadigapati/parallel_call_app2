Here’s a more concise version of the documentation for connecting to Vault and fetching a MongoDB connection string:

### Step-by-Step: Encrypting Secrets Using Vault

##### IntelliJ IDEA
1. **Install Vault Plugin**:
   - Go to **File** > **Settings** > **Plugins**.
   - Search for and install **Vault**.

2. **Configure Vault**:
   - Navigate to **Tools** > **Vault** > **Settings**.
   - Enter the **Vault Address** (e.g., `http://127.0.0.1:8200`) and your **Token**.
   - Click **Test Connection** and then **OK**.

3. **Store Secrets**:
   - Go to **Tools** > **Vault** > **Secrets**.
   - Click **Create Secret**, specify the **Path** (e.g., `secret/mongodb`) and add key-value pairs (e.g., `connection_string: mongodb://user:password@host:port/db`).
   - Click **Save**.

4. **Retrieve Secrets**:
   - Use the following code snippet to fetch the MongoDB connection string:
     ```java
     VaultConfig config = new VaultConfig()
             .address("http://127.0.0.1:8200")
             .token("your-token")
             .build();

     Vault vault = new Vault(config);
     LogicalResponse response = vault.logical().read("secret/mongodb");
     String mongoConnectionString = response.getData().get("connection_string");
     ```

##### Visual Studio Code
1. **Install Vault Extension**:
   - Go to the **Extensions** Marketplace.
   - Search for **Vault** or **Azure Key Vault** and install it.

2. **Configure the Extension**:
   - Open the Command Palette (Ctrl+Shift+P) and type **Vault: Configure**.
   - Provide the **Vault URL** and authentication details.

3. **Store Secrets**:
   - Use the Command Palette and type **Vault: Create Secret**.
   - Set the **Path** (e.g., `secret/mongodb`) and key-value pairs for the connection string.

4. **Retrieve Secrets**:
   - Use the following code snippet to access the MongoDB connection string:
     ```javascript
     const Vault = require('node-vault');
     const vault = Vault({ endpoint: 'http://127.0.0.1:8200', token: 'your-token' });

     vault.read('secret/mongodb')
       .then((result) => {
         const mongoConnectionString = result.data.connection_string;
       })
       .catch((err) => console.error(err));
     ```

This streamlined documentation provides clear, actionable steps for developers to connect to Vault and retrieve the MongoDB connection string efficiently.
