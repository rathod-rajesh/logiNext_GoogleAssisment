package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class GoogleMapsPage {
    private WebDriver driver;

    private By directionsButton = By.xpath("//button[@aria-label='Directions']");
    private By startingLocationInput = By.xpath("//*[contains(@placeholder,'Choose starting point, or click on the map')]");
    private By destinationInput = By.xpath("//*[contains(@placeholder,'Choose destination')]");
    private By firstRoute = By.xpath("//div[@id='section-directions-trip-0']");
    private By drivingInstructions = By.xpath("//div[@id='hideable_nontransit_0_0']//div[@class='j3isMd']");

    public GoogleMapsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDirectionsButton() {
        driver.findElement(directionsButton).click();
    }

    public void enterStartingLocation(String location) {
        driver.findElement(startingLocationInput).sendKeys(location);
    }

    public void enterDestination(String destination) {
        driver.findElement(destinationInput).sendKeys(destination);
        driver.findElement(destinationInput).sendKeys(Keys.ENTER);
    }

    public void clickFirstRoute() {
        driver.findElement(firstRoute).click();
    }

    public int getDrivingInstructionsCount() {
        return driver.findElements(drivingInstructions).size();
    }

    public String getDrivingInstructionText(String index) {
        System.out.println("index :- "+ index);
        String listPath = "//div[@id='hideable_nontransit_0_0']//div[@jsinstance='index']//span[contains(@class,'JxBYrc')]";
        listPath = listPath.replace("index",String.valueOf(index));
        List<WebElement> element = driver.findElements(By.xpath(listPath));
        String line = "";
        for(int i=0;i<element.size();i++){
            String text = element.get(i).getAttribute("textContent");
            line = line+text;
        }
        System.out.println(line);
        return line;
    }
}
