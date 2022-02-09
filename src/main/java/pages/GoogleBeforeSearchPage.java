package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleBeforeSearchPage {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchBtn;

    public GoogleBeforeSearchPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//div[@class='RNNXgb']//input"));
        this.searchBtn = chromeDriver.findElement(By.xpath("//div[@jsname='VlcLAe']//input[@class='gNO89b']"));
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchBtn.click();
    }
}
