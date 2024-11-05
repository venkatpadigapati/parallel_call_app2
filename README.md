                      +----------------------------------------+
                      |     DLP Team Sends Weekly Report      |
                      +----------------------------------------+
                                       |
                                       v
              +-------------------------------+       +------------------------------+
              |      Classify Incident        |------>| Action Required              |
              +-------------------------------+       | - User Notification          |
                          |                           | - Data Exposure Assessment   |
                          |                           | - Educate User on Secure Use |
                          |                           +------------------------------+
                          |
                          v
           +-------------------------------+       +------------------------------+
           | No Further Action             |------>| Monitor for Patterns         |
           | - Log Incident                |       | - Update Regex               |
           | - Assess for Repetition       |       | - Preventive Adjustments     |
           +-------------------------------+       +------------------------------+
                          |
                          |
                          v
         +-------------------------------+       +------------------------------+
         | Business-Not Reviewed         |------>| Log and Close                |
         | - No Further Action Needed    |       | - Archive Incident           |
         +-------------------------------+       +------------------------------+
                          |
                          |
                          v
         +-------------------------------+       +------------------------------+
         | False Positive                |------>| Log and Close                |
         | - Confirmed Safe              |       | - Archive for Record         |
         +-------------------------------+       +------------------------------+






OR 


                               +-------------------------------------------+
                               |        Weekly DLP Report Received         |
                               +-------------------------------------------+
                                                |
                                                v
                      +------------------------------------------+
                      |         Initial Incident Triage          |
                      +------------------------------------------+
                                                |
                    +--------------------------+---------------------------+
                    |                          |                           |
                    v                          v                           v
       +---------------------+      +---------------------+     +----------------------+
       |   Action Required   |      |  No Further Action |     | Business-Not Reviewed|
       +---------------------+      +---------------------+     +----------------------+
                    |                          |                           |
                    |                          |                           |
        Follow-up with User                    v                           v
             |                      +---------------------+       +----------------------+
             |                      |     Log Incident   |       |   Close Incident     |
             v                      |    Add to Regex    |       |   No Action Needed   |
   Educate User & Preventive        +---------------------+       +----------------------+
        Steps Taken
