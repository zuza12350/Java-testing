import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ChromeDriverTest {
    private static ChromeDriver driver;
    WebElement element;
    public static Logger log;

    @BeforeClass
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        log = LogManager.getLogger();
        log.info("Going to website");
        driver.get("https://calc.pl/zdrowie/bmi");
        log.info("Closing cookie policy");
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[2]/button[2]")).click();
    }
    @Test
    public void allFieldsFilledShouldDisplayBMI(){
        log.info("Filling weight field");
        driver.findElement(By.id("bmi-weight")).sendKeys("54");
        log.info("Filling height field");
        driver.findElement(By.id("bmi-height")).sendKeys("174");
        log.info("Clicking calculate");
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/section[1]/div[2]/div/div[1]/form/input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().build().perform();

        try{
            log.info("Getting result element");
            element = driver.findElement(By.className("bmi-result"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(element);

    }
    @Test
    public void bmiWithProvidedDataShouldDisplay1651ForWoman(){
        log.info("Checking WOMAN checkbox");
        driver.findElement(By.xpath("//*[@id=\"bmi-form\"]/label[1]/span")).click();
        log.info("Filling weight field");
        driver.findElement(By.id("bmi-weight")).sendKeys("50");
        log.info("Filling height field");
        driver.findElement(By.id("bmi-height")).sendKeys("174");

        log.info("Clicking calculate");
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/section[1]/div[2]/div/div[1]/form/input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().build().perform();

        try{
            log.info("Getting result element");
            element = driver.findElement(By.className("bmi-result"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("16.51",element.getText());
    }
    @Test
    public void bmiWithProvidedDataShouldDisplay661ForMen(){
        log.info("Checking MEN checkbox");
        driver.findElement(By.xpath("//*[@id=\"bmi-form\"]/label[2]/input")).click();
        log.info("Filling weight field");
        driver.findElement(By.id("bmi-weight")).sendKeys("20");
        log.info("Filling height field");
        driver.findElement(By.id("bmi-height")).sendKeys("174");

        log.info("Clicking calculate");
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/section[1]/div[2]/div/div[1]/form/input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().build().perform();

        try{
            log.info("Getting result element");
            element = driver.findElement(By.className("bmi-result"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals("6.61",element.getText());
    }

    @Test
    public void accordingToTheTableNextToItBMIWithDataShouldDisplayHungerButItIsNot(){
        log.info("Checking MEN checkbox");
        driver.findElement(By.xpath("//*[@id=\"bmi-form\"]/label[2]/input")).click();
        log.info("Filling weight field");
        driver.findElement(By.id("bmi-weight")).sendKeys("20");
        log.info("Filling height field");
        driver.findElement(By.id("bmi-height")).sendKeys("189");

        log.info("Clicking calculate");
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/section[1]/div[2]/div/div[1]/form/input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().build().perform();

        try{
            log.info("Getting result element");
            element = driver.findElement(By.className("bmi-name"));
        }catch (Exception e){
        }
        Assert.assertNotEquals("wygłodzenie",element.getText());
    }

    @After
    public void clearUp(){
        log.info("Clearing up fields");
        driver.findElement(By.id("bmi-weight")).clear();
        driver.findElement(By.id("bmi-height")).clear();
    }
    @AfterClass
    public static void closeBrowser(){
        log.info("Closing website");
        driver.quit();
    }
}
