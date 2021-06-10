package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)  //**runner class has to be outside of class, because no annotations are allowed inside.**
    @CucumberOptions(

            features = "@target/failed.txt",  //to enter a specific feature""src/test/resources/features/Login.feature"   //"src/test/resources/features/", //"src/test/resources/features/EmployeeSearch.feature" //"src/test/resources/features/Dashboard.feature"
            glue = "steps",   //"no need to write the whole path, just "steps" and it will run
            //glue is where we can find implementation for gherkin steps, We provide the path of package for steps.

            //  dryRun = true  //if we set dryRun to True, it will quickly scan all gherkin steps are implemented or not
            //if it is true, then no actual execution happens
            //if you make it false, then it will Run your execution

            dryRun = false,

            monochrome = true, //this means the console output for the cucumber test is easily readable
            //it will remove unreadable character

            //  strict = true //if strict is set to true at the time of execution  if cucumber encounters
            //any undefined steps, it will give an error & stops execution. Also, it gives code snippet for the unimplemented steps


            //"@simpletag"  //"@errorvalidation
          //  tags = "@dSimpltag",  //will execute the tags with @regression in Login.feature class/pg
            //"@smoke or @regression" tags will find/identify anything with either regression or smoke on it
            //"@smoke and @regression" will find the test case with the one mentioning both regression & smoke
            //we can add multiple tags in the runner class to execute scenarios belonging to different tags like: as, and, or



            // plugin = {"html:target/cucumber.html"} //the regular way
           plugin={"pretty"} //("pretty" is just something extra). will provide you all the gherkin steps in your console when you run it below


)

            public class FailedRunner{
    }
