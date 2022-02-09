package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleAfterSearchPage {

    protected WebDriver chromeDriver;
    protected List<WebElement> resultSearch;
    protected WebDriverWait wait;

    public GoogleAfterSearchPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, 120);
    }

    public List<WebElement> getResults() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='kWxLod']//div[@class='TbwUpd NJjxre']")));
        resultSearch = chromeDriver
                .findElements(By.xpath("//div[@class='kWxLod']//div[@class='TbwUpd NJjxre']"));
        return resultSearch;
    }
}
