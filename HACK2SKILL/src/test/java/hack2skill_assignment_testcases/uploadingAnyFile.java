package hack2skill_assignment_testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class uploadingAnyFile {
	   WebDriver driver; 
	   @BeforeTest
	   public void S_TC_01() {
		   // Setup our driver
		   WebDriverManager.chromedriver().setup();
		   driver = new ChromeDriver();
		   
		   // Maximize the web page
		   driver.manage().window().maximize();
		   
		   // Implicit wait for 10sec
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   
		   // Open URL
		   driver.get("http://the-internet.herokuapp.com/upload");
		   }
	   
	 
	   @Test
		public void uploading_any_file_by_robot_class() throws AWTException, InterruptedException {
			WebElement chooseFile =  driver.findElement(By.xpath("//input[@id='file-upload']"));
			
			Actions act = new Actions(driver);
            act.moveToElement(chooseFile).click().perform();
            Robot rb = new Robot();
            rb.delay(2000);
            
            // copy file to Clip Board 
            StringSelection resume = new StringSelection("C:\\Users\\Abhay Pathak\\Downloads\\Abhay_Kumar_Resume.pdf");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(resume,null);
           
            // perform control+v action to paste file 
            rb.keyPress(KeyEvent.VK_CONTROL);     rb.keyPress(KeyEvent.VK_V); 
            rb.keyRelease(KeyEvent.VK_CONTROL);    rb.keyRelease(KeyEvent.VK_V);
            rb.keyPress(KeyEvent.VK_ENTER);     rb.keyRelease(KeyEvent.VK_ENTER); 
            
            // Click on File Submit button
            rb.delay(15000);
            driver.findElement(By.xpath("//input[@id='file-submit']")).click();
            
            // For Assertion
            String expectedText = "File Uploaded!";
            String actualText = driver.findElement(By.xpath("//div[@class='example']//following-sibling::h3")).getText();
            Assert.assertEquals(expectedText, actualText);
            Thread.sleep(5000);
            driver.quit();
		}
	   
	   
}
