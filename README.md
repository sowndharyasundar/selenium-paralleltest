# selenium-paralleltest

Overall idea was to define two tests that would run in parallel. I have tried to structure it using Page Object Model approach.

Baseclass package:

BaseTest takes care of the @Before and @After actions to be performed for a test.
BaseCommands has the selenium commands to perform various actions.
DriverFactory provides the driver instance for the tests to execute as independent thread.
ExtentReportManager provides the report instance for each of the tests.
ListenerTest monitors the test execution and helps the extent test instance to log the execution status in the HTML report.


SampleTest: Holds the test cases that calls the methods from SampleImplementation class. This helps a layman to understand the flow of the test without looking deep into the code.

SampleImplementation: The selenium commands defined in BaseCommands class are invoked here to perform necessary actions on the elements in the web page.

SampleLocators: This returns the locators upon which the action needs to be performed.
