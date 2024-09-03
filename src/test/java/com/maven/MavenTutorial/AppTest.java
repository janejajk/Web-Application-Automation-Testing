package com.maven.MavenTutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void WeblogIn()
    {
        WebDriverManager.edgedriver().setup();
        EdgeDriver driver=new EdgeDriver();
        
        driver.manage().window().maximize();
        
        driver.get("https://www.orange.hcm");
        driver.findElement(By.cssSelector("#login")).sendKeys("jkjkkj1999@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("Crack@google");
        driver.findElement(By.cssSelector("submit")).click();
        
        driver.quit();
    }

   
}
