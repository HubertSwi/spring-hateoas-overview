# spring-hateoas overview
Brief overview of basic capabilities of [spring-hateoas](https://spring.io/projects/spring-hateoas).

## 1. Used tools
| tool        | version | link                                           |
|-------------|---------|------------------------------------------------|
| Gradle      | 8.7     | https://gradle.org/                            |
| Java        | 21      | https://openjdk.org                            |
| Spring Boot | 3.2.4   | https://github.com/spring-projects/spring-boot |
| Lombok      | 1.18.32 | https://github.com/projectlombok/lombok        |
| H2          | 2.2.224 | https://github.com/h2database/h2database       |

## 2. Usage
### 2.1. get all continents
```bash
curl localhost:8080/continents
```
### 2.2. get continent details
```bash
curl localhost:8080/continents/{continentId}
```
### 2.3. get country details
```bash
curl localhost:8080/continents/{continentId}/countries/{countryId}"
```
