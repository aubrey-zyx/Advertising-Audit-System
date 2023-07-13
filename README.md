# Advertising-Audit-System
## About the Project
The goal of this project is to design and develope an advertising audit system, based on the relevant business I encountered during my internship at ByteDance. This system is a simple version of the _Audit Platform_ of ByteDance, which is mainly used for auditing ads delivered on TikTok and querying related information.  
## System Design
### Functional Module Design
![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/d5470dbe-0b61-42f5-acc8-364ba715f7d1)
1. System Management (Auditor Management, Risk Label Management, and Queue Management)
2. Ad Audit (Approving Ad, Rejecting Ad, Risk Labeling, and Punishing Advertiser)
3. Information Query (Advertiser Query, Ad Query, and Audit Log Query)
4. Statistics Query (Auditor KPI Statistics, and Audit Result Statistics)
### Database Design
![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/af1caa7b-b370-4bf3-9a62-6db04ea0c986)
## System Developement
### Tools
* Java, Java Swing
* MySQL 5.0.27 (import [mysql-connector-java-5.1.37-bin](https://jar-download.com/artifacts/mysql/mysql-connector-java/5.1.37/source-code))
* MySQL Workbench 6.3
### Code Structure
* 'adaudit.view': Codes for implementing UIs and main functional modules
* 'adaudit.dao': Codes for establishing connections with the database through JDBC API
### Functional Module Implementation
* System Management
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/cd112fbd-ce11-4726-97d8-1bed06e65e75)
* Ad Audit
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/a6a56cdc-5d82-4b99-a7ed-10d54060b935)
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/6cf0a122-afad-43e0-bcb5-406547cb51eb)
* Information Query
  ![image](https://github.com/aubrey-zyx/Advertising-Audit-System/assets/108720789/e42ac712-923e-4bce-8d3a-f3d9fa5733af)
