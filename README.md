Step to be follow to run the project from command prompt
1.Open project directory
> drive\LambdaTest-Selenium101>	

2. Clean and compile projct directory using maven command
> drive\LambdaTest-Selenium101>	mvn clean copile

-------------------------------------------------------
if compilation eror then need to change maven compiler plugin release from 12 to 11

			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.7.0</version>
				<configuration>
					<release>11</release>
				</configuration>
			</plugin>
			
-----------------------------------------------------------------
3. run specific suite from project

Note: I have created two suites 
1. single.xml --> Cross browser testing for given specification for three scenarios given in assignment
2. parallel.xml ---> for parallel testing (for specific browser specification)
 (user can change parameter values for different brower, version & platform in )


 mvn clean integration-test -Duite="name of xml file"

**** for cross browser testing sequential 
 mvn clean test -Dsurefire.suite=single.xml


**** for parallel testing 
 mvn clean test -Dsurefire.suiteXmlFiles=parallel.xml

----------------------------------------------------------------
Or you can execute both suite in single shot

mvn clean test -Dsurefire.suiteXmlFiles=single.xml,parallel.xml


4. Once build execution done you can see test report in target folder under project
>surefire-reports--> refer--> emailable-report.html
Open this file in system editor, you can see clear report of all test cases as per given in assignment
------------------------------------------------------------------