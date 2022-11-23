# StringJobApp

Spring boot App - for processing strings.

App has placeholders for env configurations (MySQL database connection settings):

spring.datasource.password=${DB_PASSWORD}

spring.datasource.url=${JDBC_URL}

spring.datasource.username=${DB_USERNAME}

therefore to run the app type to command line:


****mvn spring-boot:run -Dspring-boot.run.arguments=--spring.datasource.password=your_password, --spring.datasource.url=your_jdbc_address, --spring.datasource.username=your_DB_username****

Optionally you can modify properties.




