package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //вытащить все локаторы в объекты

    public void fillName(String name){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='* Имя']")));
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys(name);
    }

    public void fillSurname(String surname){
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys(surname);
    }

    public void fillAddress(String address){
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(address);
    }

    public void fillMetro(String metro){
        driver.findElement(By.xpath("//input[@placeholder='* Станция метро']")).sendKeys(metro);
        driver.findElement(By.xpath( "//*[text()='" + metro + "']")).click();
    }

    public void fillPhoneNumber(String phoneNumber){
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
    }

    public void clickButtonNext(){
        driver.findElement(By.xpath("//button[contains(@class, 'Button_Middle') and text()='Далее']")).click();
    }

    public void fillDeliveryDate(String deliveryDate){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='* Когда привезти самокат']")));
        driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']")).sendKeys(deliveryDate);
        driver.findElement(By.xpath(".//div[@class='react-datepicker__week']/*[@tabindex='0']")).click();
    }

    public void fillOrderDuration(String orderDuration){
        driver.findElement(By.xpath("//div[text()='* Срок аренды']")).click();
        driver.findElement(By.xpath("//*[text()='" + orderDuration + "']")).click();
    }

    public void choiceOfScooterColour(String scooterColour){
        driver.findElement(By.id(scooterColour)).click();
    }

    public void fillCourierComment(String commentForCourier){
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys(commentForCourier);
    }

    public void clickFinalOrderButton(){
        driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']")).click();
    }

    public void clickButtonYes(){
        driver.findElement(By.xpath("//button[text()='Да']")).click();
    }

    public String getPopupText(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Заказ оформлен')]")));
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }
}
