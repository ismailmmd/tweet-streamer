# tweet-streamer

[![Build Status](https://travis-ci.org/ismail5701/tweet-streamer.svg?branch=master)](https://travis-ci.org/ismail5701/tweet-streamer) [![codecov](https://codecov.io/gh/ismail5701/tweet-streamer/branch/master/graph/badge.svg)](https://codecov.io/gh/ismail5701/tweet-streamer)

<p>
	<a alt="GitHub repo size">
        <img src="https://img.shields.io/github/repo-size/ismail5701/tweet-streamer" />
    </a>    
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java%20Version-v1.8-blue" />
    </a>
</p>

A spring boot application to stream live tweets from Twitter Stream APIs. User can set the rule by which tweets are filtered.
     
## Application screenshots

<img src="docs\history.PNG"/>
<img src="docs\live.PNG"/>

## The tech stack & Open-source libraries

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
*   Replace the Twitter API Key in `application.properties` file `twitter.config.token=<your API key>`
*   Build the project using below command
```shell
mvn clean install
```
*   Use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Sample Valid JSON Request Bodys


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

* 	Postmnan collection for APIs included in `/docs` folder
*   [Importing Collection to Postman](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/)


## Files and Directories Structure

```text
│   .gitignore
│   .travis.yml
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│   tweet-streamer.iml
│
├───.mvn
│   └───wrapper
│           maven-wrapper.jar
│           maven-wrapper.properties
│           MavenWrapperDownloader.java
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───demo
│   │   │           │   TweetStreamer.java
│   │   │           │
│   │   │           ├───config
│   │   │           │       AsyncConfiguration.java
│   │   │           │       Config.java
│   │   │           │
│   │   │           ├───controller
│   │   │           │       TweetController.java
│   │   │           │
│   │   │           ├───dto
│   │   │           │       Datum.java
│   │   │           │       RuleAddRequest.java
│   │   │           │       RuleDeleteRequest.java
│   │   │           │       RuleObject.java
│   │   │           │
│   │   │           ├───entity
│   │   │           │   │   TweetEntity.java
│   │   │           │   │   TweetView.java
│   │   │           │   │
│   │   │           │   └───repo
│   │   │           │           TweetRepo.java
│   │   │           │
│   │   │           └───service
│   │   │                   ITweetService.java
│   │   │                   TweetService.java
│   │   │
│   │   └───resources
│   │       │   application.properties
│   │       │
│   │       └───static
│   │               3rdpartylicenses.txt
│   │               favicon.ico
│   │               index.html
│   │               main.99369130811ab3e1a6fa.js
│   │               polyfills.35a5ca1855eb057f016a.js
│   │               runtime.acf0dec4155e77772545.js
│   │               styles.7a56496004aef477cd49.css
│   │
│   └───test
│       └───java
│           └───com
│               └───demo
│                   │   TweetStreamerTest.java
│                   │
│                   ├───controller
│                   │       TweetControllerTest.java
│                   │
│                   ├───dto
│                   │       DatumTest.java
│                   │       RuleDeleteRequestTest.java
│                   │
│                   ├───entity
│                   │       TweetEntityTest.java
│                   │
│                   └───service
│                           TweetServiceTest.java
```
## Reference

[Twitter Stream API](https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/quick-start) - Twitter Documentation
