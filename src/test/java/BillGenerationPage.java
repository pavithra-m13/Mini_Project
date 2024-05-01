import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillGenerationPage {
    private final WebDriver driver;

    public BillGenerationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void generateBill(String billId, String productId, String quantity) {
        driver.findElement(By.id("bill-id")).sendKeys(billId);
        driver.findElement(By.id("product-id")).sendKeys(productId);
        Connection con; 
        int id= Integer.parseInt(productId);
        String name=null;
         try {
            String url = "jdbc:mysql://localhost:3306/db_fin";
            String dbUsername = "root";
            String dbPassword = "root";
            con = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            // Fetch user credentials from database
            String query = "SELECT prodname FROM product WHERE prodid =? "; 
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("prodname");
            }
            if(con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("productname")).sendKeys(name);
        driver.findElement(By.id("quantity")).sendKeys(quantity);
        driver.findElement(By.id("add")).click();
    }

    public void navigateBackToHomePage() {
        driver.findElement(By.className("back-button")).click();
    }
}