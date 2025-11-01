package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.Constants;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор поля "Имя"
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    //локатор поля "Фамилия"
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //локатор поля "Адрес"
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля "Метро"
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    //локатор поля "Номер телефона"
    private final By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки "Далее"
    private final By buttonNext = By.xpath("//button[contains(@class, 'Button_Middle') and text()='Далее']");
    //локатор поля "Когда привезти самокат"
    private final By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор выпадающего календаря от поля "Когда привезти самокат"
    private final By calendar = By.xpath(".//div[@class='react-datepicker__week']/*[@tabindex='0']");
    //локатор поля "Срок аренды"
    private final By rentalPeriodField = By.xpath("//div[text()='* Срок аренды']");
    //локатор поля для комментария курьеру
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //локатор для кнопки "Заказать" после заполнения всех полей заказа
    private final By finalOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //локатор кнопки "Да" в попапе подтверждения заказа
    private final By buttonYes = By.xpath("//button[text()='Да']");
    //локатор попапа "Заказа оформлен"
    private final By popupOrderReady = By.xpath("//div[contains(text(), 'Заказ оформлен')]");
    //локатор заголовка попапа "Заказ оформлен
    private final By popupTitleOrderReady = By.className("Order_ModalHeader__3FDaJ");



    public void fillName(String name){
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nameField));
        driver.findElement(nameField).sendKeys(name);
    }

    public void fillSurname(String surname){
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void fillAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    public void fillMetro(String metro){
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(By.xpath( "//*[text()='" + metro + "']")).click();
    }

    public void fillPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    public void fillDeliveryDate(String deliveryDate){
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deliveryDateField));
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(calendar).click();
    }

    public void fillOrderDuration(String orderDuration){
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath("//*[text()='" + orderDuration + "']")).click();
    }

    public void choiceOfScooterColour(String scooterColour){
        driver.findElement(By.id(scooterColour)).click();
    }

    public void fillCourierComment(String commentForCourier){
        driver.findElement(commentField).sendKeys(commentForCourier);
    }

    public void clickFinalOrderButton(){
        driver.findElement(finalOrderButton).click();
    }

    public void clickButtonYes(){
        driver.findElement(buttonYes).click();
    }

    public String getPopupText(){
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(popupOrderReady));
        return driver.findElement(popupTitleOrderReady).getText();
    }
}
