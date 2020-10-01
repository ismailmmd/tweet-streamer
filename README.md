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

## Table of Contents

    * [Application screenshots](#application-screenshots)
    * [Tech stack & Open-source libraries](#tech-stack---open-source-libraries)
        + [Data](#data)
        + [Client - Frontend/UI](#client---frontend-ui)
        + [Server - Backend](#server---backend)
        + [Libraries and Plugins](#libraries-and-plugins)
        + [Others](#others)
    * [Running the application locally](#running-the-application-locally)
    * [Explore Rest APIs](#explore-rest-apis)
        + [URLs](#urls)
    * [<a id="personcreate">Create Person -> /api/person</a>](#-a-id--personcreate--create-person-----api-person--a-)
    * [Documentation](#documentation)
    * [Files and Directories Structure](#files-and-directories-structure)
        
## Application screenshots

<img src="images\home.PNG"/>

## Tech stack & Open-source libraries

### Data

* 	[H2](https://www.h2database.com/html/main.html) - Open-Source Embedded Relational Database Engine

### Client - Frontend/UI

* 	[Bootstrap](https://getbootstrap.com/) - Bootstrap is a free and open-source CSS framework directed at responsive, mobile-first front-end web development.

### Server - Backend

* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

###  Libraries and Plugins

* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.

### Others 

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* 	[Travis CI](https://travis-ci.org/github/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted continuous integration service used to build and test software projects hosted at GitHub and Bitbucket.
* 	[Codecov](https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted tool that is used to measure the test coverage of your codebase.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.arc.sbtest.SBtemplateApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command `java -jar SBtemplate-0.0.1-SNAPSHOT.jar`

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

The app will start running at <http://localhost:8080>, change the database settings in **application.properties** file as per your need.

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

}
```

## Documentation

*	Find Java Doc in **javadoc** folder
* 	Java Doc is generated in `Spring-Boot-Application-Template\target\site\apidocs` folder using the Maven command 

```text
`mvn javadoc:javadoc`                   //Generate JavaDoc
```

## Files and Directories Structure

```text
.
├── S
```
