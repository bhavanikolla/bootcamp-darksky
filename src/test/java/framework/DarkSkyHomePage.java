package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DarkSkyHomePage extends BasePage {
 private By hour = By.xpath("//span[contains(@class,'hour')]/span");
 private By todaysDetails = By.xpath("//span[contains(text(),'Today')]");
 private By minTemp1 = By.xpath("//span[contains(text(),'Today')]/following::span[1]/span[1]");
 private By maxTemp1 = By.xpath("//span[contains(text(),'Today')]/following::span[1]/span[3]");
 private By minTemp2 = By.xpath("//span[contains(text(),'Today')]/following::div[1]/div[1]/div[2]/div[contains(@class,'highLowTemp')]/span[contains(@class,'highTemp')]/span[1]");
 private By maxTemp2 = By.xpath("//span[contains(text(),'Today')]/following::div[1]/div[1]/div[2]/div[contains(@class,'highLowTemp')]/span[contains(@class,'lowTemp')]/span[1]");
 private By currentTemperature = By.xpath("//span[@class='summary swap']");
 private By templist = By.xpath("//div[@id='timeline']/div/div[4]/span/span");
    List<Integer> temps = new ArrayList<Integer>();

List<WebElement> hours = webActions(hour);
    List<String> hour1 = new ArrayList<String>();
    List<String> hour2 = new ArrayList<String>();

/////////////////////////////////////////////////////////////////////////////test scenario1
    public String getcurrentURL(){
        String url = getURL();
        return url;
    }



    public List<String> actualList(){

    for(WebElement hour: hours) {

        String text = hour.getText();
        hour1.add(text);
       }
    return hour1;
}

public List<String> expectedList(){

    int hour = returnHour();
    //System.out.println("hour is "+hour);
    hour2.add(0, "Now");
    String AMPM = amPm();
   // System.out.println("is it Am or Pm? "+AMPM);
    int hourValue = returnHour();
    int newHour = hourValue+2;
    if(newHour >12) {

        newHour = newHour-12;

    }
    if(newHour == 12 || newHour ==1  ) {

        if(AMPM.equals("pm") ) {

            AMPM = "am";
        }else if(AMPM.equals("am") ) {

            AMPM = "pm";
        }
    }

    hour2.add(1, String.valueOf(newHour)+AMPM);

    for(int i=2;i<hour1.size();i++) {
        newHour += 2;
        if(newHour >12) {

            newHour = newHour-12;

        }
        if(newHour == 12 || newHour==1  ) {

            if(AMPM.equals("pm") ) {

                AMPM = "am";
            }else if(AMPM.equals("am") ) {

                AMPM = "pm";
            }
        }
    hour2.add(i, String.valueOf(newHour)+AMPM );
    }
     return hour2;
}

   public int returnHour(){

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh");

        String hour = sdf.format(d);
        int hour1 = Integer.parseInt(hour);

		return hour1;

    }


    public  String amPm() {

        Date d = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("a");
        String ampm = sdf1.format(d).toLowerCase();
        return ampm;

    }

    //////////////////////////////////////////////////////////////////////test scenario2

    public void scrollIntoViewTodaysTimeLineAndExpand(){
        scrollIntoViewAndClick(todaysDetails);
    }

    public String getMinTemp1(){
      return getTextFromElement(minTemp1);
      }


      public String getMaxTemp1(){
       return getTextFromElement(maxTemp1);
      }


      public String getMinTemp2(){
       return getTextFromElement(minTemp2);
      }

      public String getMaxTemp2(){
       return getTextFromElement(maxTemp2);
      }
///////////////////////////////////////////////////////// test scenario3

    public int currentTemperature(){

        String temp = getTextFromElement(currentTemperature);
        temp = temp.replaceAll("\\D", "");
        int	tempInt = Integer.parseInt(temp);
       // System.out.println("temperature is "+tempInt);
        return tempInt;
    }

    public void sortedTemperatures(){

        for(WebElement temp1: listOfWebElements(templist)) {
            String numTemp = temp1.getText();
            numTemp = numTemp.replaceAll("\\D", "");
            int tempInteger = Integer.parseInt(numTemp);
            temps.add(tempInteger);
            System.out.println(tempInteger);
        }
        Collections.sort(temps);
       // System.out.println(temps);
    }


    public int minTemperatureValue(){

        sortedTemperatures();
        int minTempValue = temps.get(0);
       // System.out.println("min temp is "+minTempValue);
        return minTempValue;

    }

    public int maxTemperatureValue(){

       // sortedTemperatures();
        int maxTempValue =  temps.get((temps.size())-1);
       // System.out.println("max temp is "+maxTempValue);
        return maxTempValue;
    }

}
