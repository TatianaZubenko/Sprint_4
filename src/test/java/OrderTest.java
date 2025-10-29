import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderTest {

    WebDriver driver;

    @Before
    public void startUp() {
        //создаем вебдрайвер
        driver = new FirefoxDriver();
        //ссылка на стенд тестируемый
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
    }

    @Test
    public void orderTest(){
        //нажимаем на кнопку "Заказать" в хеддере страницы
        driver.findElement(By.className("Button_Button__ra12g")).click();
        //ожидание перед открытием формы заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='* Имя']")));
        //заполняем поле "Имя"
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys("Иван");
        //заполняем поле "Фамилия"
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys("Иванов");
        //заполняем поле "Адрес"
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys("г. Москва, ул. Спортивная, 48");
        //заполняем поле "Метро"
        driver.findElement(By.xpath("//input[@placeholder='* Станция метро']")).sendKeys("Комсомольская");
        //кликаем на станцию метро в выпадающем списке
        driver.findElement(By.xpath("//*[text()='Комсомольская']")).click();
        //заполняем поле "Телефон"
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys("89998886644");
        //нажимаем кнопку "Далее"
        driver.findElement(By.xpath("//button[contains(@class, 'Button_Middle') and text()='Далее']")).click();
        //ожидание следующей части формы заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='* Когда привезти самокат']")));
        //заполняем поле "Когда привезти самокат"
        driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']")).sendKeys("29.10.2025");
        //кликаем на дату в выпадающем календаре
        driver.findElement(By.xpath(".//div[@class='react-datepicker__week']/*[@tabindex='0']")).click();
        //кликаем на поле "Срок аренды"
        driver.findElement(By.xpath("//div[text()='* Срок аренды']")).click();
        //выбираем из выпадающего списка вариант
        driver.findElement(By.xpath("//*[text()='сутки']")).click();
        //выбираем цвет самоката чекбоксом
        driver.findElement(By.id("black")).click();
        //заполняем поле "Комментария для курьера"
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys("Хорошая погода");
        //нажимаем на кнопку "Заказать" внизу под формой
        driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")).click();
        //подтверждаем заказ, нажимая на кнопку "Да"
        driver.findElement(By.xpath("//button[text()='Да']")).click();
        //ожидание попапа "Заказ оформлен"
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Заказ оформлен')]")));
        //сравниваем текст попапа с "Заказ оформлен"
        Assert.assertTrue(driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText().startsWith("Заказ оформлен"));

    }

    @After
    public void teardown(){
        //закрыть браузер
        driver.quit();
    }

}

