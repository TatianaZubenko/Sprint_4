import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FaqTest {
    WebDriver driver;

@Before
public void startUp(){
    //создаем вебдрайвер
    WebDriver driver = new ChromeDriver();
    //ссылка на стенд тестируемый
    driver.get("https://qa-scooter.praktikum-services.ru/");
}

    @Test
    public void faqTest(){


        //локаторы FAQ
        By faqQuestion = By.id("accordion__heading-0");
        By faqAnswer = By.id("accordion__panel-0");

        //скролл до FAQ
        WebElement element = driver.findElement(faqQuestion);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        //клик по вопросу
        driver.findElement(faqQuestion).click();
        //явное ожидание ответа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(faqAnswer));

        //сравнение ожидаемого ответа с реальным
        Assert.assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", driver.findElement(faqAnswer).getText());

        //закрыть браузер
        driver.quit();
    }


}
