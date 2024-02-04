# Readme

### Requirements
* JDK version 11
* Gradle version 8.3

### Getting Started
* Import the project into an IDE of your choosing for code changes.
* Navigate to the project home directory.
* Run the following, which will start the application.
```bash
gradle bootRun
```
* Navigate to a rest client of your choosing.
* Enter the URl and call the microservice.
<img width="817" alt="Screenshot 2024-02-04 at 2 00 33 PM" src="https://github.com/ctharindace/two-way-ssl-helloworld/assets/3063347/d33d7c17-fa30-4f07-b8f5-ab19d0df087f">

## Improvements
Following are the improvements that can be done in the implementation:

* Containerise the project for deployment flexibility.
* Use a configuration server instead of in project configs.
* Refrain fom committing the keystore and credentials.
* Introduce an authentication mechanism.
* Introduce fault tolerance.
* Introduce observability.
* Improve unit tests.
