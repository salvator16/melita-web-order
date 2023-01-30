# Web Order Challenge Service


# Technology Stack
- JDK Adoptium Temurin 17.0.1 https://adoptium.net/
- Gradle 7.3.2
- Spring Boot 2.6.1
- Spring Flyway
- Spring Cloud Feign
- RabbitMQ
- MySQL 8+

# How to run
The project uses [Gradle](https://gradle.org)
as a dependency management tool. Can be buildable using either simple Gradle tasks or a command-line tool.

./gradlew clean build

./gradlew run

When the project is runs for the first time, Rabbit definitions(exchange, queue and routing), schema and table creations on 
MySql, will be done by Service. All properties are bounded dynamically with default value.

Test coverage result can be seen under build/reports/jacoco/test/html/index/html

Here is the sample auth information and it will be registering the simple user table when the flyway first run automatically;

username: melita@melita.com

password: 123456

For getting image's
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped mysql:8

docker run -d --hostname rabbit-local --name rabbit-local -p 15672:15672 -p 5672:5672 rabbitmq:management-alpine


# How can be improved
- To be able to stay in project sample scope, and shortened time test coverage is not enough. Most of the edge cases are not covered.
- Domain model can be improved like defining all the products and packages as in database, and the service layer can be cached for product related data
- To be able to provide full authentication, I prefer to add Basic Auth. JWT or Api Token Filter layer can be easily added in to flow
- Password can be salted with using BCrypt provider.



