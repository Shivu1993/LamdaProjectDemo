package main;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

//import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTest {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {

        String username = "sshivukumar7";
        String authkey = "zxTvDdwsaFEBRS3fChDK0gMc9oklf3zsNoVcQHtFdAAx7bja5x";

//        String username = System.getenv("sshivukumar7") == null ? "sshivukumar7" : System.getenv("sshivukumar7");
//        String authkey = System.getenv("zxTvDdwsaFEBRS3fChDK0gMc9oklf3zsNoVcQHtFdAAx7bja5x") == null ? "zxTvDdwsaFEBRS3fChDK0gMc9oklf3zsNoVcQHtFdAAx7bja5x" : System.getenv("zxTvDdwsaFEBRS3fChDK0gMc9oklf3zsNoVcQHtFdAAx7bja5x");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("110.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "sshivukumar7");
        ltOptions.put("accessKey", "zxTvDdwsaFEBRS3fChDK0gMc9oklf3zsNoVcQHtFdAAx7bja5x");
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("video", true);
        ltOptions.put("timezone", "Maldives");
        ltOptions.put("build", "SampleBuild");
        ltOptions.put("project", "SampleProject");
        ltOptions.put("name", "SampleTest");
        ltOptions.put("console", "error");
        ltOptions.put("networkThrottling", "Regular 4G");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        browserOptions.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

        driver.get("https://lambdatest.github.io/sample-todo-app/");

        System.out.println("Checking Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li2")).click();

        System.out.println("Checking Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li4")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li7")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li8")).click();
        Thread.sleep(300);

        System.out.println("Entering Text");
        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li9")).click();

        // Let's also assert that the todo we added is present in the list.

        spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[9]/span")).getText();
        Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
        Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambdastatus=" + Status);
        driver.quit();
    }

}
