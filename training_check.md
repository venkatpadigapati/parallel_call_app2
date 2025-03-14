# **Copilot License Request – Process Enhancement & Training Validation**

## **1. Overview**

### **Objective**

The current Copilot onboarding process provides licenses based on user attestation without verifying if the required training has been completed. The goal of this enhancement is to introduce a training validation step while keeping the onboarding process as intact as possible.

### **Scope**

- Validate whether users have completed the **"Using GitHub Copilot Responsibly"** training.
- Ensure training validation is tracked and monitored as part of the onboarding process.
- Keep minimal impact on existing statuses and workflows.
- Introduce automation through a scheduled process to validate training completion.

---

## **2. Current Process (Before Changes)**

1. Developer visits `/copilotOnboarding` page and sees the **"How to Request Access"** section, which lists training as a prerequisite.
2. User clicks **"Request License"** and attests that training is completed.
3. User data (**UID, Email, LOB, etc.**) and attestation are stored in **MongoDB** (`github-copilot-onboarding` collection), and the user status is set to **Initiated - Pending Validation**.
4. The Onboarding Team reviews the requests and processes them, updating the status to **In Progress**.
5. Once processed, the status updates to **Approved**, and an email notification is sent.

### **License Revocation Flow**

1. Managers request to revoke licenses for inactive users.
2. Status transitions through **Revoke Initiated → Revoke In Progress → Revoked**.
3. Revoked users can re-request licenses by following the same process above.

---

## **3. Proposed Approaches**

### **Approach 1: Modify Primary Statuses (Single Status Approach)**

- Introduce new statuses into the **primary workflow**:
  - **Initiated - Pending Validation** (User attested but training verification pending)
  - **In Progress - Training Not Verified** (Training verification failed, but onboarding proceeds)
  - **Approved - Training Not Verified** (License issued, but training pending verification)

#### **Pros:**

- Training verification is explicitly tracked within the onboarding process.
- The onboarding team is aware of pending training cases.

#### **Cons:**

- Modifying primary statuses increases complexity.
- Difficult to distinguish whether onboarding is blocked due to training.

### **Approach 2: Dual Status Tracking (Recommended Approach)**

- Keep **existing primary statuses** (**Initiated, In Progress, Approved, Revoked**).
- Introduce a **secondary "Training Status"**:
  - **Pending Validation** (Training check not yet completed)
  - **Not Verified** (Training validation failed)
  - **Verified** (Training validation successful)

#### **Pros:**

- Minimal impact on the **existing onboarding flow**.
- Provides **clear separation** between license request status and training status.
- Easier to filter users who need training verification without altering primary workflows.

#### **Cons:**

- Requires UI changes to display **both statuses**.
- Slight increase in database complexity.

---

## **4. Technical Implementation**

### **Training Validation via DISHR API**

- **Endpoint:** `/hr/employees/data/Learning/v1/transcript`
- **Request Body:**
  ```json
  {
    "TranscriptsList": [
      {"ELID": "U12345", "COURSE_ID": "ON-0004747"},
      {"ELID": "U45678", "COURSE_ID": "ON-0004747"}
    ]
  }
  ```
- **Response:**
  ```json
  [
    {"ELID": "U12345", "COURSE_ID": "ON-0004747", "COMPLETION_DATE": null, "USER_PROGRESS_STATUS": null},
    {"ELID": "U45678", "COURSE_ID": "ON-0004747", "COMPLETION_DATE": "2023-12-11T06:00:00Z", "USER_PROGRESS_STATUS": "Completed"}
  ]
  ```
- Training statuses based on API response:
  - `null` → **Not Verified**
  - `Registered` / `In Progress` → **Pending Validation**
  - `Completed` → **Verified**

### **Spring Boot Scheduler for Training Validation**

- **Class Name:** `CopilotTrainingValidationScheduler`
- **Method Name:** `validateTrainingStatus()`
- Runs **every Sunday at 12:00 AM (midnight)** to check training completion.
- Updates **MongoDB** fields accordingly.
- Triggers email notifications based on user status (configurable).

#### **Implementation Example:**
```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CopilotTrainingValidationScheduler {

    private static final Logger LOGGER = Logger.getLogger(CopilotTrainingValidationScheduler.class.getName());

    @Autowired
    private CopilotOnboardingService copilotOnboardingService;

    @Scheduled(cron = "0 0 0 * * SUN") // Runs every Sunday at 12:00 AM
    public void validateTrainingStatus() {
        LOGGER.info("Starting Copilot training validation process...");

        List<String> pendingUsers = copilotOnboardingService.getUsersPendingTrainingValidation();
        if (pendingUsers.isEmpty()) {
            LOGGER.info("No users pending training validation.");
            return;
        }

        copilotOnboardingService.updateTrainingStatusForUsers(pendingUsers);

        LOGGER.info("Completed Copilot training validation process.");
    }
}
```

---

### **MongoDB Schema (Existing Collection: github-copilot-onboarding)**

*No changes required.* Existing fields will suffice.

#### **Relevant Fields:**

- `status`: **Tracks onboarding state** (`Initiated`, `In Progress`, `Approved`, `Revoked`).
- `trainingStatus`: **New secondary status** (`Pending Validation`, `Verified`, `Not Verified`).
- `updatedDate`: **Tracks last training check update.**

### **Email Notifications (Configurable)**

- **If training is Not Verified:** Notify user to complete training.
- **If training is Pending Validation:** No immediate action, but continue tracking.
- **If training is Verified:** No notification needed.

### **UI Changes**

- Update **Attestation Screen**: Display training verification step.
- Update **Onboarding Dashboard**: Show both **primary & secondary statuses**.
- Update **User Status Page**: Show **training status separately**.

---

## **5. Conclusion**

- Approach 2 (Dual Status Tracking) is recommended due to its minimal impact on the existing process.
- Implementation requires changes to **API validation, MongoDB schema, UI, and email notifications**.
- Scheduled validation will ensure training compliance is monitored effectively.

---

**Next Steps:**

- Finalize the preferred approach after team discussion.
- Implement UI and API updates.
- Configure scheduled training validation.
- Roll out in test environment before production deployment.

