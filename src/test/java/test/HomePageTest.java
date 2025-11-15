package tests;


import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import pages.HomePage;



public class HomePageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By headerText = By.cssSelector("h1.display-4.fw-bold.mb-4");

    @BeforeClass
    public void setUp() {
        // WebDriverManager يحل مشكلة النسخة
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationintesting.online/#booking");
    }
    @Test(priority = 1)
    public void testPageTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Restful-booker-platform demo"),
                " Page title is correct! Found: " + title);
    }
    @Test(priority = 2)
    public void testHeaderIsDisplayed() {
        // ننتظر العنصر يظهر قبل ما نفحصه
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerText));
        Assert.assertTrue(header.isDisplayed(),
                " Header is  visible!");
    }

    @Test(priority = 3)
    public void testBookNowButtonNavigatesToRoomsSection() {
        // 1. اضغط على زر "Book Now"
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary.btn-lg")));
        button.click();

        // 2. انتظر ظهور قسم الغرف
        WebElement roomsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rooms"))); // عدلي حسب id أو css لقسم الغرف
        Assert.assertTrue(roomsSection.isDisplayed(), " Rooms section is  visible after clicking the button!");

    }

    @Test(priority = 4)
    public void testAvailableRoomsUpdateWithDates() {
        HomePage home = new HomePage(driver);

        // 1. Set Check-in & Check-out dates
        home.setCheckInDate("2025-12-20");
        home.setCheckOutDate("2025-12-22");

        // 2. Click "Check Availability"
        home.clickCheckAvailability();

        // 3. Verify available rooms are displayed
        Assert.assertTrue(home.isAvailableRoomsDisplayed(),
                "❌ Available rooms not displayed for selected dates!");
    }
    @Test(priority = 5)
    public void testRoomsExist() {
        HomePage home = new HomePage(driver);

        List<WebElement> rooms = home.getAllRooms();
        System.out.println("no.of rooms: " + rooms.size());
        Assert.assertTrue(rooms.size() > 0, "❌ No rooms found on the page!");

    }
    @Test(priority = 6)
    public void testRoomsHaveTitleDescriptionPrice() {
        HomePage home = new HomePage(driver);

        List<WebElement> rooms = home.getAllRooms();
        Assert.assertTrue(rooms.size() > 0, "❌ No rooms found on the page!");

        for (WebElement room : rooms) {
            String title = home.getRoomTitle(room);
            String desc = home.getRoomDescription(room);
            String price = home.getRoomPrice(room);

            Assert.assertFalse(title.isEmpty(), "❌ Missing room title!");
            Assert.assertFalse(desc.isEmpty(), "❌ Missing room description!");

            System.out.println("Room: " + title + " | " + desc + " | " + price);
        }
    }
    @Test(priority =7)
    public void testEachRoomHasBookButton() {
        HomePage home = new HomePage(driver);
        List<WebElement> rooms = home.getAllRooms();

        for (WebElement room : rooms) {
            Assert.assertTrue(
                    home.hasBookButton(room),
                    "❌ Room missing Book button!"
            );
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
   // UI Functional Testing .    col-md-6 col-lg-4"




}
