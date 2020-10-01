# tweet-streamer

[![Build Status](https://travis-ci.org/ismail5701/tweet-streamer.svg?branch=master)](https://travis-ci.org/ismail5701/tweet-streamer) [![codecov](https://codecov.io/gh/ismail5701/tweet-streamer/branch/master/graph/badge.svg)](https://codecov.io/gh/ismail5701/tweet-streamer)

<p>
	<a alt="GitHub repo size">
        <img src="https://img.shields.io/github/repo-size/ismail5701/tweet-streamer" />
    </a>    
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java%20Version-v11-blue" />
    </a>
</p>

description goes here
     
## Application screenshots

<img src="images\home.PNG"/>

## Tech stack & Open-source libraries

### Data

* 	[H2](https://www.h2database.com/html/main.html) - Open-Source Embedded Relational Database Engine

### Client - Frontend/UI

* 	[Angular](https://angular.io/) - Angular is a platform for building mobile and desktop web applications.
* 	[Angular Material](https://material.angular.io/) - UI component infrastructure and Material Design components for mobile and desktop Angular web applications.

### Server - Backend

* 	[JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

###  Libraries and Plugins

* 	[Lombok](https://projectlombok.org/) - Lombok is used to reduce boilerplate code for model/data objects.

### Others 

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)
* 	[Travis CI](https://travis-ci.org/github/ismail5701/tweet-streamer) - A hosted continuous integration service used to build and test software projects hosted at GitHub.
* 	[Codecov](https://codecov.io/gh/ismail5701/tweet-streamer) - A hosted tool that is used to measure the test coverage of your codebase.

## Testing

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
*   Move the root directory and run below command to run tests.

```shell
mvn test
```

## Running the application locally

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
*   Build the project using below command
```shell
mvn clean install
```
*   Use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Explore Rest APIs

The app defines following CRUD APIs.

### URLs

|                  URL                   | Method |          Remarks       |
|----------------------------------------|--------|------------------------|
|`http://localhost:8080/`                | GET    | Home Page              |

## Sample Valid JSON Request Bodys

##### Create Person -> /api/person

```json
{
	"id": 42,
	"data": "{\"data\":{\"created_at\":\"2020-10-01T06:07:43.000Z\",\"author_id\":\"806204521919762432\",\"id\":\"1311548328186834945\",\"text\":\"RT @ashoswai: No end to Upper caste terror! Another Dalit woman has been gang-raped and killed in UP under Modi-Yogi's Raj! https://t.co/6a…\"},\"includes\":{\"users\":[{\"id\":\"806204521919762432\",\"name\":\"rabia\",\"username\":\"rabiiik527\"}]},\"matching_rules\":[{\"id\":1311547334208053248,\"tag\":\"\"}]}"
}
```

## Documentation

* 	Generate Java Documentation in `tweet-streamer\target\site\apidocs` using the Maven command 

```shell
mvn javadoc:javadoc
```

## Files and Directories Structure

```text
.
├── S
```
