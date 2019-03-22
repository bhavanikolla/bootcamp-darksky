package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.DarkSkyHomePage;
import org.testng.Assert;

public class DarkSkySD {

    DarkSkyHomePage ds = new DarkSkyHomePage();
   //Given I am on Darksky home page
    //Then I verify timeline is displayed with two hours incremented

    @Given("^I am on Darksky home page$")
    public void iAmOnDarkSkyHomePage(){

        Assert.assertEquals("https://darksky.net/forecast/40.7127,-74.0059/us12/en",ds.getURL());

    }

    @Then("^I verify timeline is displayed with two hours incremented$")
    public void verifyTimelineIsIncrementedByTwoHours(){

      Assert.assertTrue(ds.actualList().equals(ds.expectedList()));

    }



    @When("^I expand todays timeline$")
    public void ExpandTodaysTimeline(){

        ds.scrollIntoViewTodaysTimeLineAndExpand();


    }


    @Then("^I verify lowest and highest temp is displayed correctly$")
    public void iVerifyLowestAndHighestTempIsDisplayedCorrectly()  {

        Assert.assertTrue(ds.getMinTemp1().equals(ds.getMinTemp2()));
        Assert.assertTrue(ds.getMaxTemp1().equals(ds.getMaxTemp2()));
        //System.out.println("temperature comparision assertion passed");

    }


    @Then("^I verify current temp is not greater or less then temps from daily timeline$")
    public void verifyCurrentTempIsInRange(){

        Assert.assertTrue(ds.currentTemperature() >= ds.minTemperatureValue()  );
        Assert.assertTrue(ds.currentTemperature() <= ds.maxTemperatureValue() );

    }




}
