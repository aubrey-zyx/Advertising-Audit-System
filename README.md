# Advertising-Audit-System
## About the Project
The goal of this project is to design and develope an advertising audit system, based on the relevant business I encountered during my internship at ByteDance. This system is a simple version of the _Audit Platform_ of ByteDance, which is mainly used for auditing ads delivered on TikTok and querying related information.  
## System Design
### Functional Module Design
![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/modules.jpg)
1. System Management (Auditor Management, Risk Label Management, and Queue Management)
2. Ad Audit (Approving Ad, Rejecting Ad, Risk Labeling, and Punishing Advertiser)
3. Information Query (Advertiser Query, Ad Query, and Audit Log Query)
4. Statistics Query (Auditor KPI Statistics, and Audit Result Statistics)
### Database Design
![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/database.png)
## System Developement
### Tools
* Java, Java Swing
* MySQL 5.0.27 (import [mysql-connector-java-5.1.37-bin](https://jar-download.com/artifacts/mysql/mysql-connector-java/5.1.37/source-code))
* MySQL Workbench 6.3
### Code Structure
* `adaudit.view`: Codes for implementing UIs and main functional modules
* `adaudit.dao`: Codes for establishing connections with the database through JDBC API
### Functional Module Implementation
* System Management
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/system_management.png)
* Ad Audit
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/ad_audit_1.png)
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/ad_audit_2.png)
* Information Query
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/blob/main/pics/info_query.png)
