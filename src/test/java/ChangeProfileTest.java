import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by user on 16.11.16.
 */
public class ChangeProfileTest {
    private WebDriver driver;

    @BeforeTest
    public void testSuccessfulyLogin() {
        driver = new FirefoxDriver();
        driver.get(Constants.URL);
        driver.manage().window().maximize();
        WebElement loginFiled = driver.findElement(By.cssSelector(Constants.CSS_LOGIN_FIELD));
        loginFiled.sendKeys(Constants.LOGIN);
        WebElement passwordField = driver.findElement(By.cssSelector("#txtPassword"));
        passwordField.sendKeys(Constants.PASSWORD);
        // Elements are overlapped, that is why we select element with text
        driver.findElement(By.cssSelector("#loginBtnSecText p")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("#menuBox"));
        Assert.assertTrue(elements.size() == 1);

        //Fins Settings/Profile
        By xpath = By.xpath(".//*[@id='menuBox']/div/ul/li[1]/a[@href='/settings']");
        WebElement element = driver.findElement(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Test(dataProvider = "correctData")
    public void testChangeProfile(String s_first_name, String s_last_name, String s_company_name,
                                  String s_website, String s_title, String s_phone, String s_timezone,
                                  String s_file_listing_page_size, String s_policy_file_age, String sfname,
                                  String slname, String scompany, String swebsite, String stitle, String sphone,
                                  String stimezone, String sfile_listing_page_size, String spolicy) {

        //Save Data

        driver.findElement(By.cssSelector("input[name='s_first_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_first_name']")).sendKeys(s_first_name);
        driver.findElement(By.cssSelector("input[name='s_last_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_last_name']")).sendKeys(s_last_name);
        driver.findElement(By.cssSelector("input[name='s_company_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_company_name']")).sendKeys(s_company_name);
        driver.findElement(By.cssSelector("input[name='s_website']")).clear();
        driver.findElement(By.cssSelector("input[name='s_website']")).sendKeys(s_website);
        driver.findElement(By.cssSelector("input[name='s_title']")).clear();
        driver.findElement(By.cssSelector("input[name='s_title']")).sendKeys(s_title);
        driver.findElement(By.cssSelector("input[name='s_phone']")).clear();
        driver.findElement(By.cssSelector("input[name='s_phone']")).sendKeys(s_phone);

        Select droplistTimeZone = new Select(driver.findElement(By.cssSelector("select[name='s_timezone']")));
        droplistTimeZone.selectByVisibleText(s_timezone);

        Select ItemsPerPage = new Select(driver.findElement(By.cssSelector("select[name='s_file_listing_page_size']")));
        ItemsPerPage.selectByVisibleText(s_file_listing_page_size);

        driver.findElement(By.cssSelector("input[name='s_policy_file_age']")).clear();
        driver.findElement(By.cssSelector("input[name='s_policy_file_age']")).sendKeys(s_policy_file_age);
        driver.findElement(By.id("btnSaveChanges")).click();

        //Find Settings/Profile

        WebDriverWait wait = new WebDriverWait(driver, 10);
        By byPageLoc = By.xpath(".//*[@id='menuBox']/div/ul/li[1]/a[@href='/settings']");
        wait.until(ExpectedConditions.elementToBeClickable(byPageLoc));
        driver.findElement(By.xpath(".//*[@id='menuBox']/div/ul/li[1]/a[@href='/settings']")).click();

        //Check Saved Data

        String firstName = driver.findElement(By.cssSelector("input[name='s_first_name']")).getAttribute("value");
        Assert.assertEquals(sfname, firstName);
        String lastName = driver.findElement(By.cssSelector("input[name='s_last_name']")).getAttribute("value");
        Assert.assertEquals(slname, lastName);
        String companyName = driver.findElement(By.cssSelector("input[name='s_company_name']")).getAttribute("value");
        Assert.assertEquals(scompany, companyName);
        String webSite = driver.findElement(By.cssSelector("input[name='s_website']")).getAttribute("value");
        Assert.assertEquals(swebsite, webSite);
        String title = driver.findElement(By.cssSelector("input[name='s_title']")).getAttribute("value");
        Assert.assertEquals(stitle, title);
        String phone = driver.findElement(By.cssSelector("input[name='s_phone']")).getAttribute("value");
        Assert.assertEquals(sphone, phone);
        String timeZone = driver.findElement(By.xpath("//select[@name='s_timezone']/option[@selected='' and @value='Africa/Abidjan']")).getAttribute("value");
        Assert.assertEquals(stimezone, timeZone);
        String file_listing_page_size = driver.findElement(By.xpath("//select[@name='s_file_listing_page_size']/option[@selected='' and @value='50']")).getAttribute("value");
        Assert.assertEquals(sfile_listing_page_size, file_listing_page_size);
        String policy_file_age = driver.findElement(By.cssSelector("input[name='s_policy_file_age']")).getAttribute("value");
        Assert.assertEquals(spolicy, policy_file_age);

    }

    @DataProvider
    public Object[][] correctData() {
        return new Object[][]{
                {Constants.s_first_name, Constants.s_last_name, Constants.s_company_name, Constants.s_website, Constants.s_title, Constants.s_phone, Constants.s_timezone,  Constants.s_file_listing_page_size, Constants.s_policy_file_age,
                        Constants.s_first_name, Constants.s_last_name, Constants.s_company_name, Constants.s_website, Constants.s_title, Constants.s_phone, Constants.s_timezone,  Constants.s_file_listing_page_size, Constants.s_policy_file_age},

                {Constants.ss_first_name, Constants.ss_last_name, Constants.ss_company_name, Constants.ss_website, Constants.ss_title, Constants.ss_phone, Constants.ss_timezone,  Constants.ss_file_listing_page_size, Constants.ss_policy_file_age,
                        Constants.ss_first_name, Constants.ss_last_name, Constants.ss_company_name, Constants.ss_website, Constants.ss_title, Constants.ss_phone, Constants.ss_timezone,  Constants.ss_file_listing_page_size, Constants.ss_policy_file_age}
        };
    }

}













