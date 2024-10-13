package com.AutoAttendance.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AutoAttendance.pages.HomePage;
import com.AutoAttendance.pages.LoginPage;

@RestController
public class Scheduler {

    @Value("${baseURL}")
    private String baseURL;

    @GetMapping("/test")
    @Scheduled(cron = "0 50 9,18 * * 1-6") // Runs at 9:50 AM and 6:50 PM, Monday to Saturday (IST)
    public void checkInOut() {
        WebDriver driver = new ChromeDriver();
        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/users.csv"));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                String username = record.get("username");
                String password = record.get("password");

                driver.get(baseURL);
                LoginPage loginPage = new LoginPage(driver);
                loginPage.Login(username, password);

                HomePage homePage = new HomePage(driver);
                homePage.MarkAttendance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) { // Catch any other exceptions
            e.printStackTrace();
        } finally {
            driver.quit(); // Ensure the driver is quit only once after processing all records
        }
    }
}
