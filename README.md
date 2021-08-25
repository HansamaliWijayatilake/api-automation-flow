![](https://img.shields.io/github/languages/top/hansamaliWijayatilake/req-res-automation-task) ![](https://img.shields.io/maven-central/v/io.rest-assured/rest-assured) ![](https://badgen.net/github/status/micromatch/micromatch) ![](https://badgen.net/badge/icon/restAssured?icon=restAssured&label)
# :tada: Overview
API Automation for [ReqRes](https://reqres.in/) endpoints using [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), [RestAssured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured) and [TestNg](https://mvnrepository.com/artifact/org.testng/testng)

* Covered HTTP Methods:
  * POST
  * GET
  * PUT
  * DELETE
# :zap: Functionalities
* Data driven approach: **DataProviders** feature is a part of data-driven testing wherein it provides different values to a test case in a single execution. 
It means you can run a single test case once but can execute a method with different data values. 
For instance, you can test the create user feature with different inputs but you will be executing the test only once. 
TestNG data-providers feature passes all these values to the test case one at a time so that it tests different data sets in a single execution.

* Helper methods and Utils: Helper methods are provided to read json files with json arrays and json objects and they can be found under **HelperUtils.java**

* Parallel test execution: TestNg helps to execute tests in parallel thread. It is facilitated in the **smokeTest.xml**  

* Delayed Response Validation: With the help of [awaitility](https://mvnrepository.com/artifact/org.awaitility/awaitility) delayed response validation is covered and related test for that is **delayResponseTest**
