import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverTest {
        private static FirefoxDriver driver;
        WebElement element;
        public static Logger log;

        @BeforeClass
        public static void openBrowser(){
            System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            log = LogManager.getLogger();
            log.info("Going to website");
            driver.get("https://www.amazon.pl/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.pl%2F%3F_encoding%3DUTF8%26ref_%3Dnav_custrec_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=plflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        }

        @Test
        public void validUserCredentialsShouldPassTheForm(){
            log.info("Fill in field username");
            driver.findElement(By.id("ap_customer_name")).sendKeys("Annie");
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("exampleEmailNumber2@gmail.com");
            log.info("Fill in field password");
            driver.findElement(By.id("ap_password")).sendKeys("example123@!");
            log.info("Fill in field password again");
            driver.findElement(By.id("ap_password_check")).sendKeys("example123@!");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                log.info("Getting element");
                element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div/div/div[1]/span"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }
        @Test
        public void badlyRepeatedPasswordShouldNotLetTheFormPass(){
            log.info("Fill in field username");
            driver.findElement(By.id("ap_customer_name")).sendKeys("Annie");
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("exampleEmailNumber2@gmail.com");
            log.info("Fill in field password");
            driver.findElement(By.id("ap_password")).sendKeys("example123@!");
            log.info("Fill in field password again");
            driver.findElement(By.id("ap_password_check")).sendKeys("example123@!example123@!");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                element = driver.findElement(By.className("a-alert-content"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }

        @Test
        public void invalidEmailCredentialsShouldNotLetTheFormPass(){
            log.info("Fill in field username");
            driver.findElement(By.id("ap_customer_name")).sendKeys("Annie");
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("abcd");
            log.info("Fill in field password");
            driver.findElement(By.id("ap_password")).sendKeys("example123@!");
            log.info("Fill in field password again");
            driver.findElement(By.id("ap_password_check")).sendKeys("example123@!");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                log.info("Getting alert");
                element = driver.findElement(By.id("auth-email-invalid-email-alert"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }
        @Test
        public void firstNameFieldShouldNotBeEmpty(){
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("exampleEmailNumber2@gmail.com");
            log.info("Fill in field password");
            driver.findElement(By.id("ap_password")).sendKeys("example123@!");
            log.info("Fill in field password again");
            driver.findElement(By.id("ap_password_check")).sendKeys("example123@!");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                log.info("Getting alert");
                element = driver.findElement(By.className("a-alert-content"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }
        @Test
        public void passwordFieldShouldNotBeEmpty(){
            log.info("Fill in field username");
            driver.findElement(By.id("ap_customer_name")).sendKeys("Annie");
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("exampleEmailNumber2@gmail.com");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                log.info("Getting alert");
                element = driver.findElement(By.className("a-alert-content"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }

        @Test
        public void siteShouldDisplayAlertWhenEmailIsUsed(){
            log.info("Fill in field username");
            driver.findElement(By.id("ap_customer_name")).sendKeys("Annie");
            log.info("Fill in field email");
            driver.findElement(By.id("ap_email")).sendKeys("example123@gmail.com");
            log.info("Fill in field password");
            driver.findElement(By.id("ap_password")).sendKeys("example123@!");
            log.info("Fill in field password again");
            driver.findElement(By.id("ap_password_check")).sendKeys("example123@!");
            log.info("Clicking confirm");
            driver.findElement(By.id("continue")).click();
            try{
                log.info("Getting alert");
                element = driver.findElement(By.className("a-alert-heading"));
            }catch (Exception e){
            }
            Assert.assertNotNull(element);
        }
//
//        @After
//        public void cleanUp(){
//            log.info("Cleaning up the fields");
//            driver.findElement(By.id("ap_customer_name")).clear();
//            driver.findElement(By.id("ap_email")).clear();
//            driver.findElement(By.id("ap_password")).clear();
//            driver.findElement(By.id("ap_password_check")).clear();
//        }
//        @AfterClass
//        public static void closeBrowser(){
//            log.info("Closing website");
//            driver.quit();
//        }
}

