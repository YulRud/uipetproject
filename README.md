# uipetproject
This is a UI test project
Technologies and approaches:
1. PageObject + Selenium + TestNG + Java 8 for test
2. AllureReporting for reporting (with Allure logs, Screenshots on failure, Steps)
3. Crossbrowser testing: Firefox and Chrome
4. Also for creation a proper webDriver SimpleFactory was used in the WebDriverFactory class

As the task for my pet project I took a final project from this course program (but used another technological stack and changed some tests): 
https://cdn.otus.ru/media/public/ed/36/ed367d_program_java_qa_engineer.pdf - page 10

Prerequisites for project:

There is maven 3.6.1 or higher installed
There is allure 2.13.8 or higher installed

To start the project:

1. to download a project: git clone https://github.com/YulRud/uipetproject.git
2. to start testing: mvn clean test
3. to start allure report with results: mvn allure:serve
