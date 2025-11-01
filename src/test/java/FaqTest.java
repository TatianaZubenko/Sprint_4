import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.util.Constants;

import java.time.Duration;

@RunWith(Parameterized.class)
public class FaqTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String questionIndex;
    private final String answer;

    public FaqTest(String questionIndex, String answer) {
        this.questionIndex = questionIndex;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                { "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                { "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },
        };
    }


    @Test
    public void faqTest(){
        WebDriver driver = factory.getDriver();

        //открываем тестируемый стенд
        driver.get(Constants.MAIN_PAGE_LINK);

        MainPage objMainPage = new MainPage(driver);

        //скролл до FAQ
        objMainPage.scrollToFaq(questionIndex);

        //клик по вопросу
        objMainPage.clickOnQuestion(questionIndex);

        //сравнение ожидаемого ответа с реальным
        Assert.assertEquals(answer, objMainPage.getAnswerText(questionIndex));

    }
}
