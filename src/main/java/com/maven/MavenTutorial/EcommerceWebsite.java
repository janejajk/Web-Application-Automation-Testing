package com.maven.MavenTutorial;
//import java.awt.Dimension;
import java.time.Duration;
import java.util.List;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EcommerceWebsite 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String productName="ZARA COAT 3";
        System.setProperty("webdriver.edge.driver", "C:\\Users\\2151512\\msedgedriver.exe");
        WebDriver driver =new EdgeDriver();
//        driver.get("https://rahulshettyacademy.com/client");
        driver.navigate().to("https://rahulshettyacademy.com/client");
//        Dimension p = new Dimension(0,3000);     	
        driver.manage().window().maximize(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("abcd@gmaill.com");
       
        //implicit wait  
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Abcdabcd@1");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@id='login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         
        // Explicit wait example!
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-md-0")));
         
        List<WebElement> products= driver.findElements(By.cssSelector(".offset-md-0"));
        int size=products.size();
        int i=0;
        while(size!=0) {
        	boolean flag=products.get(i).findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3");
        	if(flag==true) {
//        		String ands=
        		products.get(i).findElement(By.xpath("//div[@class='card-body']//button[2]")).click(); 
//        		System.out.println("ands");
        		break;
        	}
        	size--;
        	i++;
        }
        
        boolean added=driver.findElement(By.cssSelector(".toast-container")).isDisplayed();
        System.out.println("item is added?"+added);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    
        
        List<WebElement> cartList=driver.findElements(By.xpath("//div[@class='cart']//h3"));
        
        boolean productVerified=cartList.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(productVerified);
        
        driver.findElement(By.cssSelector(".totalRow .btn")).click();
        driver.findElement(By.cssSelector(".form-group .txt")).sendKeys("india"); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
//        Select country=new Select(driver.findElement(By.cssSelector(".form-group .text")));
//        country.deselectByVisibleText("India"); 
        driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        
        String orderConformation=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals("done", "THANKYOU FOR THE ORDER.", orderConformation);
    }

	
}
