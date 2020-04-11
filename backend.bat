call mvn -f backend\pom.xml clean
call mvn -f backend\pom.xml package
call mvn -f backend\pom.xml spring-boot:run