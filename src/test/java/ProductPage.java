import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProduct(String productId,String productname, String quantity, String price, String expiryDate, String supplierId) {
         driver.findElement(By.name("prodid")).sendKeys(productId);
        driver.findElement(By.name("prodname")).sendKeys(productname);
        driver.findElement(By.name("prodqty")).sendKeys(quantity);
        driver.findElement(By.name("prodprice")).sendKeys(price);
        driver.findElement(By.name("prodexp")).sendKeys(expiryDate);
        driver.findElement(By.name("supid")).sendKeys(supplierId);
        driver.findElement(By.id("productadd")).click();
        
    }

    public void editProductQuantity(String newQuantity) throws InterruptedException {
        driver.findElement(By.className("edit-button")).click();
        driver.findElement(By.id("prodqty")).clear();
        driver.findElement(By.id("prodqty")).sendKeys(newQuantity);
        Thread.sleep(4000);
        driver.findElement(By.className("button")).click();
    }

    public void deleteProduct() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.className("delete-button")).click();
        Thread.sleep(4000);
    }

    public void navigateBackToHomePage() {
        driver.findElement(By.id("backbutton")).click();
    }
}
