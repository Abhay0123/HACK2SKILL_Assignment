package hack2skill_assignment_testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class brokenImages {
	 WebDriver driver; 
	   @BeforeTest
	   public void S_TC_01() {
		   // Setup our driver
		   WebDriverManager.edgedriver().setup();
		   driver = new EdgeDriver();
		   
		   // Maximize the web page
		   driver.manage().window().maximize();
		   
		   // Implicit wait for 10sec
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   
		   // Open URL
		   driver.get("http://the-internet.herokuapp.com/broken_images");
		   }
	   
	   @Test
	   public void find_all_image_links() {
		   // Capture Image links from Web Page
		   List <WebElement> Image_links  = driver.findElements(By.tagName("img"));
		   System.out.println("Total Image Links: " +Image_links.size());
		   
		   // Find Links are working or not
		   for(int i=0;i<Image_links.size();i++) {
			   
			   String image_width = Image_links.get(i).getAttribute("naturalWidth");
			   
			   if(image_width.equals("0")) {
				   
				   System.out.println("Broken Link: "+Image_links.get(i).getAttribute("src"));
			   }else {
				   System.out.println("Not Broken Link: "+Image_links.get(i).getAttribute("src"));
			   }
		   }
	   }
	   @Test
	   public void Validate_First_Image_link_with_statusCode() {
		    WebElement Image_links  = driver.findElement(By.xpath("//img[@src='asdf.jpg']"));
            Response statusCode = RestAssured.given()
            		                         .contentType("application/json")
            		                         .get(Image_links.getAttribute("src"));
        	   
               if(statusCode.getStatusCode() == 200) {
            	   
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }else {
				   Assert.assertEquals(statusCode.getStatusCode(), 404);
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }
	   }
	   
	   @Test
	   public void Validate_Second_Image_link_with_statusCode() {
		    WebElement Image_links  = driver.findElement(By.xpath("//img[@src='hjkl.jpg']"));
            Response statusCode = RestAssured.given()
            		                         .contentType("application/json")
            		                         .get(Image_links.getAttribute("src"));
        	   
               if(statusCode.getStatusCode() == 200) {
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }else {
				   Assert.assertEquals(statusCode.getStatusCode(), 404);
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }

	   }
	   
	   @Test
	   public void Validate_Third_Image_link_with_statusCode() {
		    WebElement Image_links  = driver.findElement(By.xpath("//img[@src='img/avatar-blank.jpg']"));
            Response statusCode = RestAssured.given()
            		                         .contentType("application/json")
            	                             .get(Image_links.getAttribute("src"));
        	   
               if(statusCode.getStatusCode() == 200) {
            	   Assert.assertEquals(statusCode.getStatusCode(), 200);
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }else {
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }

	   }
	   
	   @Test
	   public void Validate_Forth_Image_link_with_statusCode() {
		    WebElement Image_links  = driver.findElement(By.xpath("//img[@alt='Fork me on GitHub']"));
            Response statusCode = RestAssured.given()
            		                         .contentType("application/json")
            		                         .get(Image_links.getAttribute("src"));
        	   
               if(statusCode.getStatusCode() == 200) {
            	   Assert.assertEquals(statusCode.getStatusCode(), 200);
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }else {
				   System.out.println(Image_links.getAttribute("src") +" "+"with Status code: "+statusCode.getStatusCode());
			   }

	   }   
}
