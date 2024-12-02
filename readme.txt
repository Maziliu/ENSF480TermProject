Prerequisites:
JDK17 (or higher), maven, Node.js, mySQL
ensure apache-maven-3.9.9\bin is added to PATH

---------------------------------------------------------------------------------------------------

configuring mySQL:
1. ensure mysql is properly installed and server/bin is added to PATH

2. ensure username: root, password: password
    - if not, go to: backend\src\main\resources\application.properties, and change user and password to match the one on your mySQL

---------------------------------------------------------------------------------------------------

running springboot:
1. if you have option to 'run Java', use that option. Else, in terminal:
    cd to backend directory
    mvn clean install
    mvn spring-boot:run

---------------------------------------------------------------------------------------------------

running frontend:
1. cd to frontend directory
    npm install
    npm start

---------------------------------------------------------------------------------------------------

errors:
    1. if mySQL server is not running: then in cmd prompt:
    - Input services.msc by pressing Win + R.
    - Search the list for a service with the name "MySQL" or "MySQL80" (or a similar name). To start the server, perform a right-click on the MySQL service and select "Start".
