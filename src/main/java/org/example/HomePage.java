package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By orderButtonTop = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Button__ra12g')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void scrollToAccordion() {
        WebElement accordion = driver.findElement(By.cssSelector(".accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordion);
    }

    public void clickAccordionHeaderByQuestionText(String questionText) {
        WebElement button = driver.findElement(
                By.xpath("//div[contains(@class, 'accordion__button') and contains(text(), '" + questionText + "')]")
        );
        button.click();
    }

    public String getAccordionPanelTextByQuestionText(String questionText) {
        WebElement button = driver.findElement(
                By.xpath("//div[contains(@class, 'accordion__button') and contains(text(), '" + questionText + "')]")
        );
        String panelId = button.getAttribute("aria-controls");
        WebElement panel = driver.findElement(By.id(panelId));
        wait.until(ExpectedConditions.visibilityOf(panel));
        return panel.getText();
    }

    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    public void clickOrderButtonBottom() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
    }
}