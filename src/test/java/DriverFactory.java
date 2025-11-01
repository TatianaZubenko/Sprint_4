import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))){
            startFirefox();
        }else {
            startChrome();
        }
    }

    private void startChrome() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void startFirefox() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
