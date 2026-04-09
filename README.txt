Newspaper Subscription Management System
MCA | DBMS Java Assessment | April 2026 | Set No. 12
UEM Kolkata | Department of Computer Applications

─────────────────────────────────────────
HOW TO SET UP
─────────────────────────────────────────
1. Install XAMPP and start MySQL
2. Open CMD and run:
   "C:\xampp\mysql\bin\mysql.exe" -u root -p
3. Run the schema file:
   source sql/schema.sql

─────────────────────────────────────────
HOW TO COMPILE
─────────────────────────────────────────
Open terminal inside the project folder and run:
   cd src
   javac -cp ".;..\lib\*" DBConnection.java Main.java

─────────────────────────────────────────
HOW TO RUN
─────────────────────────────────────────
   java -cp ".;..\lib\*" Main

─────────────────────────────────────────
DATABASE CREDENTIALS
─────────────────────────────────────────
Host     : localhost
Port     : 3306
Username : root
Password : (blank)
Database : newspaper_db

─────────────────────────────────────────
FEATURES IMPLEMENTED
─────────────────────────────────────────
1. Register Subscriber
2. Record Payment
3. Mark Delivery
4. View Unpaid Subscribers
5. Exit