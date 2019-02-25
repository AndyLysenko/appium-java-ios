# mobile-appium-automation

Template of UI test automation framework for sample iOS application.

Note!
Can be run om Mac only

to run the test:
1. Install, setup and run local Appium server
https://stackoverflow.com/questions/24813589/how-to-setup-appium-on-mac-os-to-run-automated-tests-from-java-classes-on-androi
2. update path to the .apk file and device UDID in DlobalContext class
3. create maven run configuration with command line 'clean test allure:serve'
4. If you want to integrate test execution with TestRail, update testRailHelper. Most values are hardcoded there.

It will:
1. run the test
2. generate allure report, run allure server, open page with report
3. On TestRail create rest run with one test case, add test result for the case and open page in browser

Tools used:
- OS name: "mac os x", version: "10.14.3", arch: "x86_64", family: "mac"
- Java version: 1.8.0_201
- Apache Maven 3.6.0
- Appium version 1.11.0
- Allure version 2.10.0
