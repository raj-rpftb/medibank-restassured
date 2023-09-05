# medibank-restassured
API Testing Framework

This is a Java-based API testing framework that uses Maven for dependency management. The framework is designed to test the product-related endpoints of an API.

Dependencies

The project uses several dependencies, including:

- Rest-Assured for API testing
- TestNG for test management
- Extent Reports for report generation
- Log4j for logging
- Gson for JSON processing

The dependencies are defined in the pom.xml file.

Key Components
1. API Endpoints

The API endpoints are defined in the Routes and ProductEndPoints classes. The base URL and the endpoint for getting the product list are defined in the Routes class.

2. Test Classes

The ProductTests class contains the tests for the Product API. It uses the GetBodyResponse class to parse API responses and Log4j for logging test events.

3. Running Tests

Tests are configured to run using TestNG. The testng.xml file contains the suite and test configuration. To run the tests, use the Maven Surefire Plugin as configured in the pom.xml file.

4. Report Generation

The ExtentReportManager class is responsible for generating the Extent Reports. It implements the ITestListener interface and overrides its methods to log the test results in the report.

5. Logging

Logging is configured using Log4j. The configuration is defined in the log4j2.xml file.


