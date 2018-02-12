**Sample automated test using - Selenium PageFactory JUnit Maven**

**Requirements**

*The following software is required to be installed on the computer:*

1. Firefox (version 55 or higher) / Chrome

2. Maven (version 3.3.9 or higher)

3. Compatible geckodriver and chromedriver for you browser. If the ones in the resources folders are outdated, download the geckodriver and chromedriver that is compatible with your OS and browser*

    3.1. Geckodriver - https://github.com/mozilla/geckodriver/releases

    3.2. Chromedriver - https://sites.google.com/a/chromium.org/chromedriver/downloads

**NB! It is also possible to run tests directly using Eclipse or Intellij!**

**Step-by-step guide to run the tests**

1. Download the code and upzip it to folder

2. With the command line navigate to this folder

3. Execute the command to start the test (Other possible commands are in the next chapters):

    mvn clean compile -Denv.url=https://some.url.com/ surefire-report:report site -DgenerateReports=false

**Usage pattern for only running tests and generating reports/results:**

 *When giving the URL parameter in command line*

    mvn clean compile -Denv.url=https://some.url.com/ surefire-report:report site -DgenerateReports=false

 *When using the default URL configured in environment.properties file*

    mvn clean compile -Denv.url= surefire-report:report site -DgenerateReports=false

**Usage pattern for running tests and creating javadoc:**

    mvn clean compile -Denv.url= surefire-report:report site -DgenerateReports=false javadoc:javadoc javadoc:test-javadoc

**Selenium?** - Read more at: http://www.seleniumhq.org/

**What is PageFactory?** - Read more at: https://code.google.com/p/selenium/wiki/PageFactory

**What are PageObjects?** - Read more at: https://code.google.com/p/selenium/wiki/PageObjects

**JUnit?** - Read more at: http://junit.org/

**Maven?** - Read more at: https://maven.apache.org/

**Linux modification**
When using a linux operating system, the geckodriver file in the resources folder should be made executable:  chmod +x src/main/resources/geckodriver

**The main purpose of the PageObject approach:**

    PageObject pattern simply models UI elements as objects 
    within the test code. This reduces the amount of duplicated 
    code and means that if the UI changes, 
    the fix need only be applied in one place.
                                                                                       
    Example:
	- Without PageObjects
	    * You have 200 Selenium/Junit tests
	    * There is new version of the software
	    * You have hardcoded logic into tests
	    * You have to change all those 200 tests

	With PageObjects
	    * You use PageObjects
	    * You change UI elements from the PageObjects
	    * All the tests should be working

**Project dependencies + licence type**

1. Java version 1.8+ (BCL, OpenJDK under GPL)

2. JUnit version 4.12 (EPL 1.0)

3. Log4j version 2.8.2 (Apache 2.0)

4. Maven version 3.5.0 (Apache 2.0)

5. Maven Clean version 3.0.0 (Apache 2.0)

6. Maven Compiler version 3.6.1 (Apache 2.0)

7. Maven Assembly version 3.0.0 (Apache 2.0)

8. Maven Surefire plugin version 2.20 (Apache 2.0)

9. Maven Surefire reporting plugin version 2.20 (Apache 2.0)

10. Maven Site version 3.6 (Apache 2.0)

11. Maven Properties plugin 1.0.0 (Apache 2.0)

12. Javadoc version 2.10.4 (Apache 2.0)

13. Extentreports 3.1.0

**Project arhitecture:**

1. main package: ee.cgi.sample
    - helpers
        * **Constants.java** - Defines all the project constants like properties, buttons, columns etc.
        * **EnvironmentProperties.java** - Helper class for loading environment.properties file and it's contents.
        * **ExtentManager.java** - Helper for creating html reports
        * **PropertiesLoader.java** - Helper methods for reading properties from *.properties files.
        * **ScreenshotRule.java** - Helper class for taking different screenshots.
        * **UtilTest.java** - Helper methods for getting dates, removing folders etc.
        * **WebDriver.java** - Helper class for the WebDriver and various functions.
    - pageobjects
		* **LoginPage.java** - PageObject for the Login page.
		* **MainPage.java** - PageObject for the Main page.
2. resources: src/main/resources
    - **chromedriver.exe** - Chromedriver executable for Windows.
    - **chromedriver_linux** - Chromedriver executable for Linux.
    - **chromedriver_mac** - Chromedriver executable for Mac.
    - **config.xml** - Extentreports configuration file.
    - **environment.properties** - File includes all the properties for the environment (login, evidence output).
    - **geckodriver.exe** - FirefoxDriver executable for Windows.
    - **geckodriver_linux** - FirefoxDriver executable for Linux.
    - **geckodriver_mac** - FirefoxDriver executable for Mac.
    - **log4j2.xml** - Log4j configuration file.
    - **main.properties** - File includes all the test data properties.
3. test package: ee.cgi.sample
	- ee.cgi.sample
	    * **LoginTest.java** - Contains login tests for login to CGI Confluence page;
		* **TestBase.java** - TestBase which provides all the necessary methods for each test and the tests should extend
		this class.
		
**Test results:**

1. main folder: target
	- test-results (folder name depends on the main.properties)
		* **YYYY-MM-DD** - Folder includes detailed logs and evidences for each test.
		    * **TestClassName** - Folder includes test results for the n-tests under that class name.
		        * **testName.log** - File includes detailed test log and result.
		        * **testName.png** - Screenshot from the test which shows results of actions.
		        * **testName_failed.png** - Screenshot from the test which indicates failure.
            * **extentreport.html** - Test results generated into easily-readable HTML file.
    - surefire-reports (will only be shown when the tests are run with maven)
        * **ee.cgi.sample.LoginTest.txt** - File includes simplified test run result.
        * **TEST-ee.cgi.sample.LoginTest.xml** - File includes machine-readable test result in XML-format.

**Javadoc results:**

1. main folder: target
    - javadoc
        * **apidocs** - folder includes javadoc for the .java files under src/main/java
        * **testapidocs** - folder includes javadoc for the .java files under src/test/java
