package fitpeo;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.fitpeo.com/");
		driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();
		String sliderValue = null;

		try {
	    // Step 1: Scroll to the slider section
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement slider = driver.findElement(By.xpath("(//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op'])[1]")); // Replace with actual slider element ID
        js.executeScript("arguments[0].scrollIntoView(true);", slider);
        Thread.sleep(5000); // Wait for slider to be in view

        // Step 2: Adjust the slider to 820
        WebElement sliderHandle = driver.findElement(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']")); // Replace with correct locator
        js.executeScript("arguments[0].value=820;", sliderHandle); // Adjust slider value
        Thread.sleep(1000); // Wait for slider to update

        // Verify slider value updated to 820
        WebElement textField = driver.findElement(By.xpath("//input[@id=':r0:']")); // Replace with actual text field ID
         sliderValue = textField.getAttribute("value");
        if (sliderValue != null && sliderValue.equals("expectedValue")) {
            System.out.println("Slider value matches expected value.");
        } else {
            System.out.println("Slider value does not match or is null.");
        }
        // Step 3: Update the text field to 560
        textField.clear();
        textField.sendKeys("560");
        Thread.sleep(1000); // Wait for the slider to update

    
       
        // Verify slider position updated to match 560
        String updatedSliderValue = sliderHandle.getAttribute("value");
        if (updatedSliderValue.equals("560")) {
            System.out.println("Text field value updated to 560 successfully.");
        } else {
            System.out.println("Text field value update failed.");
        }

        // Step 4: Scroll and select CPT checkboxes
        WebElement cptCheckbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")); // Replace with actual checkbox ID
        WebElement cptCheckbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        WebElement cptCheckbox3 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
    

       // js.executeScript("arguments[0].scrollIntoView(true);", cptCheckbox1);
        cptCheckbox1.click();
        cptCheckbox2.click();
        cptCheckbox3.click();
        // Step 5: Validate Total Recurring Reimbursement value
        WebElement reimbursementHeader = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'][contains(text(),'Total Recurring Reimbursement for all Patients Per')]")); // Replace with actual header ID
        String reimbursementValue = reimbursementHeader.getText();

        if (reimbursementValue.contains("$110,700")) {
            System.out.println("Reimbursement value validation successful: " + reimbursementValue);
        } else {
            System.out.println("Reimbursement value validation failed.");
        }
		} catch (NoSuchElementException e) {
	        System.out.println("Slider element not found: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
       

		 Thread.sleep(8000);

        // Close the browser
        driver.quit();
	}

}
