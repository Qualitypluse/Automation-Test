package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By headerText = By.cssSelector("h1.display-4.fw-bold.mb-4");// simplified for homepage
    private By bookThisRoomTitle = By.cssSelector("div.booking-form h3");
    private By bookNowButton = By.cssSelector("a.btn.btn-primary.btn-lg");
    private By roomsSection = By.id("rooms"); // عدلي حسب id أو css للقسم
    private By checkInField = By.xpath("//*[@id=\"booking\"]/div/div/div/form/div/div[1]/div/div/input");
    private By checkOutField = By.xpath("//*[@id=\"booking\"]/div/div/div/form/div/div[2]/div/div/input");
    private By checkAvailabilityButton = By.cssSelector("button.btn.btn-primary.w-100.py-2");

    private By roomsLocator = By.cssSelector(".room-card");
    private By roomTitle = By.cssSelector("h5");
    private By roomDescription = By.cssSelector("p");
    private By roomPrice = By.cssSelector("div.card-footer .fw-bold.fs-5");
    //    private By roomBookButton = By.cssSelector("a.btn.btn-primary");
    private By locationLink = By.xpath("//*[@id=\"navbarNav\"]/ul/li[4]");
    private By roomsLink = By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]");
    private By contactLink = By.xpath("//*[@id=\"navbarNav\"]/ul/li[5]");
    private By adminLink = By.xpath("//*[@id=\"navbarNav\"]/ul/li[6]"); // مثال لو فيه Admin
    private By homeLink = By.xpath("//footer//a[text()='Home']");

    // Method 1
    public boolean isBookRoomTitleDisplayed() {
        try {
            WebElement title = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(bookThisRoomTitle));
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method 2
    public boolean isHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerText));
        return header.isDisplayed();
    }

    // Method 3
    public void clickBookNow() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(bookNowButton));
        button.click();
    }

    // تحقق إن قسم الغرف ظهر
    public boolean isRoomsSectionDisplayed() {
        try {
            WebElement rooms = wait.until(ExpectedConditions.visibilityOfElementLocated(roomsSection));
            return rooms.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method 4
    public void setCheckInDate(String date) {
        WebElement checkIn = wait.until(ExpectedConditions.elementToBeClickable(checkInField));
        checkIn.clear();
        checkIn.sendKeys(date);
    }

    public void setCheckOutDate(String date) {
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(checkOutField));
        checkOut.clear();
        checkOut.sendKeys(date);
    }

    // Click "Check Availability" button
    public void clickCheckAvailability() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(checkAvailabilityButton));
        button.click();
    }

    public boolean isAvailableRoomsDisplayed() {
        try {
            WebElement rooms = wait.until(ExpectedConditions.visibilityOfElementLocated(roomsSection));
            return rooms.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Method 5
    public List<WebElement> getAllRooms() {
        return driver.findElements(roomsLocator);
    }

    //Method 6
    // Scroll للغرفة
    private void scrollToRoom(WebElement room) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", room);
    }

    public String getRoomTitle(WebElement room) {
        scrollToRoom(room);
        return room.findElement(roomTitle).getText();
    }

    public String getRoomDescription(WebElement room) {
        scrollToRoom(room);
        return room.findElement(roomDescription).getText();
    }

    public String getRoomPrice(WebElement room) {
        scrollToRoom(room);
        // استخدام findElements لتجنب NoSuchElementException
        List<WebElement> prices = room.findElements(roomPrice);
        return prices.isEmpty() ? "Price not found" : prices.get(0).getText();

    }

    //Method 7
    // Check if room has a book button
    public boolean hasBookButton(WebElement room) {
        List<WebElement> btn = room.findElements(By.cssSelector("a.btn.btn-primary"));
        return !btn.isEmpty();
    }

    //Method 8
    public void clickNavLink(By locator) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        link.click();
    }
    //Method 9
    // Methods to expose links for tests
    public By getLocationLink() {
        return locationLink;
    }
    //Method 10
    public By getRoomsLink() {
        return roomsLink;
    }
    //Method 11
    public By getContactLink() {
        return contactLink;
    }
    //Method 12
    public By getAdminLink() { return adminLink; }
    //Method 13
    public void clickHomeLink() throws InterruptedException {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(homeLink));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        Thread.sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
}
