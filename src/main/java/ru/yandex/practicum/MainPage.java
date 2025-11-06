package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.Constants;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    String questionLocatorTemplate = "accordion__heading-";
    String answerLocatorTemplate = "accordion__panel-";
    private final By topOrderButton = By.xpath(".//*[@class='Button_Button__ra12g']");
    private final By middleOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    public void scrollToFaq(String questionIndex){
        WebElement element = driver.findElement(By.id(questionLocatorTemplate + questionIndex));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
    }

    public void clickOnQuestion(String questionIndex){
        driver.findElement(By.id(questionLocatorTemplate + questionIndex)).click();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(answerLocatorTemplate + questionIndex)));
    }

    public String getAnswerText(String questionIndex){
        return driver.findElement(By.id(answerLocatorTemplate + questionIndex)).getText();
    }

    public void clickOrderButton(String startButton) {
        if (startButton.equals("top")) {
            driver.findElement(topOrderButton).click();
        } else if (startButton.equals("middle")){
            WebElement element = driver.findElement(middleOrderButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
            driver.findElement(middleOrderButton).click();
        }
    }
}
