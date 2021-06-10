package steps;

import io.cucumber.java.After;   //imports for Hooks
import io.cucumber.java.Before;  //import for Hooks

//import org.junit.After;
//import org.junit.Before;

import io.cucumber.java.Scenario;
import utils.CommonMethod;

public class Hooks extends CommonMethod {

    @Before   //your pre-condition
    public void start(){
        setUp();   //setup method is our pre-condition
    }


    @After //your post condition
    public void end(Scenario scenario){ // public void end(){}   before there was nothing in parenthesis

        byte[] pic;  //array of byte  ...pic is stored in byte
        if(scenario.isFailed()){
            pic= takeScreenShot("failed/" + scenario.getName());
        }else{
            pic= takeScreenShot("passed/"+ scenario.getName());
        }

        scenario.attach(pic,"image/png",scenario.getName());

        tearDown();
    }




}
