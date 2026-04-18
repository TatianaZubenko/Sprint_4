import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.OrderPage;
import ru.yandex.practicum.util.Constants;


@RunWith(Parameterized.class)
public class OrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String startButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String orderDuration;
    private final String scooterColour;
    private final String commentForCourier;

    public OrderTest(String startButton, String name, String surname, String address, String metro, String phoneNumber, String deliveryDate, String orderDuration, String scooterColour, String commentForCourier) {
        this.startButton = startButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.orderDuration = orderDuration;
        this.scooterColour = scooterColour;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"top", "Иван", "Иванов", "г. Москва, ул. Спортивная, 48", "Комсомольская", "89998886644", "13.12.2025", "сутки", "black", "Хорошая погода"},
                {"middle", "Петр", "Петров", "г. Москва, ул. Тверская, 25", "Спортивная", "89996665544", "16.12.2025", "двое суток", "grey", "Плохая погода"}
        };
    }



    @Test
    public void orderTest(){
        WebDriver driver = factory.getDriver();

        //открываем тестируемый стенд
        driver.get(Constants.MAIN_PAGE_LINK);

        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        //скролл до нужного элемента и нажатие на кнопку "Заказать"
        objMainPage.clickOrderButton(startButton);
        //ожидание перед открытием формы заказа, заполняем поле "Имя"
        objOrderPage.fillName(name);
        //заполняем поле "Фамилия"
        objOrderPage.fillSurname(surname);
        //заполняем поле "Адрес"
        objOrderPage.fillAddress(address);
        //заполняем поле "Метро", кликаем на станцию метро в выпадающем списке
        objOrderPage.fillMetro(metro);
        //заполняем поле "Телефон"
        objOrderPage.fillPhoneNumber(phoneNumber);
        //нажимаем кнопку "Далее"
        objOrderPage.clickButtonNext();
        //ожидание следующей части формы заказа, заполняем поле "Когда привезти самокат", кликаем на дату в выпадающем календаре
        objOrderPage.fillDeliveryDate(deliveryDate);
        //кликаем на поле "Срок аренды", выбираем из выпадающего списка вариант
        objOrderPage.fillOrderDuration(orderDuration);
        //выбираем цвет самоката чекбоксом
        objOrderPage.choiceOfScooterColour(scooterColour);
        //заполняем поле "Комментария для курьера"
        objOrderPage.fillCourierComment(commentForCourier);
        //нажимаем на кнопку "Заказать" внизу под формой
        objOrderPage.clickFinalOrderButton();
        //подтверждаем заказ, нажимая на кнопку "Да"
        objOrderPage.clickButtonYes();
        //ожидание попапа "Заказ оформлен", сравниваем текст попапа с "Заказ оформлен"
        Assert.assertTrue(objOrderPage.getPopupText().startsWith("Заказ оформлен"));

    }

}

