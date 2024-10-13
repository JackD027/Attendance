package com.AutoAttendance.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,  Duration.ofSeconds(20)); // Set a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'mark-attendance-btn')]")    
    private WebElement markAttendanceButton;
    
    @FindBy(xpath = "//button[contains(@class, 'btn btn-success')]")
    private WebElement markAttendanceButtonOnPopup;

    public void MarkAttendance() {
    	
        wait.until(ExpectedConditions.elementToBeClickable(markAttendanceButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(markAttendanceButtonOnPopup)).click();
        System.out.println("done");
    }
}
