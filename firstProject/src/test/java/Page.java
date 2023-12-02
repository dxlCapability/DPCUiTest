import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {
  WebDriver driver;

  public Page( WebDriver driver){
      this.driver = driver;
  }
    public void elementToWait(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // By a = By.xpath("//input[contains(@id,'username')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
    public void clickToButton(By elementLocator){
            driver.findElement(elementLocator).click();
    }
    public void sendKeysToLabel(By elementLocator,String text){
      driver.findElement(elementLocator).sendKeys(text);
    }
}
