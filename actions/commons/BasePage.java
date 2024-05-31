package commons;

import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // WebDriver driver;
    // ham tuong ta tra ve void
    //ham lay ra du lieu tra ve string/int/WebElement/ list<WebElement>
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);

    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getCurrentPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode (WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();

    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();

    }
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitForPresence(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }
    public void acceptToAlert(WebDriver driver){
        waitForPresence(driver).accept();
        //driver.switchTo().alert().accept();
    }
    public void cancelToAlert(WebDriver driver){
        waitForPresence(driver).dismiss();
       // driver.switchTo().alert().dismiss();
    }
    public String getTextInAlert(WebDriver driver){
        //return driver.switchTo().alert().getText();
        return waitForPresence(driver).getText();
    }
    public void senkeyToAlert(WebDriver driver, String keysToSend){
        //driver.switchTo().alert().sendKeys(keysToSend);
        waitForPresence(driver).sendKeys(keysToSend);
    }
    public void switchToWindowByID(WebDriver driver, String pageID){
        Set<String> allIDS = driver.getWindowHandles();
        // use for to go through
        for(String id:allIDS){
            if(!id.equals(pageID)){
                driver.switchTo().window(id);

            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver,String pageTitle){
        // get all tab's id
        Set<String> allIDs= driver.getWindowHandles();
        for(String id:allIDs){
            driver.switchTo().window(id);
            sleepInSeconds(2);
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(pageTitle)){
                break;

            }
        }

    }
    public void closeAllWindowWithoutParentID(WebDriver driver,String parentID){
        Set<String> allIDs= driver.getWindowHandles();
        for(String id:allIDs){

            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();

            }
        }
        driver.switchTo().window(parentID);

    }

    public  void sleepInSeconds(long timeSecond){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public Set<Cookie> getBrowserCookies(WebDriver driver){
       return driver.manage().getCookies();
    }
    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies)
         driver.manage().getCookies();
    }
    public  void deleteAkkCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    /*web element*/
    public  By getByXpath(String locator){
        return By.xpath(locator);
    }
    public WebElement getWebElement(WebDriver driver, String locator){
       return driver.findElement(getByXpath(locator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).clear();
    }
    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver,locator).clear();
        getWebElement(driver,locator).sendKeys(valueToSend);
    }
    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver,locator).getText();

    }
    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver,locator).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver,locator).getCssValue(cssPropertyName);
    }
    public String convertRGBAToHexaColor(WebDriver driver, String locator){
        String backgroundColorRGBA = getElementCssValue(driver,locator,"background-color");
        return Color.fromString(backgroundColorRGBA).asHex();
    }
    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver,locator).size();

    }
    /*apply for checkbox and radio button*/
    public void checkToElement(WebDriver driver, String locator){
        if(!getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }
    /*only apply for checkbox*/
    public void uncheckToElement(WebDriver driver, String locator){
        if(getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }
    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }

    public void switchToIframe(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver,locator));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver,locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver,locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(driver,locator)).perform();
    }












    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue){
        new Select(getWebElement(driver,locator)).selectByVisibleText(itemValue);
    }
    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();

    }
    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).isMultiple();
    }
    public void selectItemInDropdown(WebDriver driver, String parentLocator, String childLocator, String itemTextExpected){

        getWebElement(driver,parentLocator).click();
        sleepInSeconds(1);
        List<WebElement> speedDropdownItems = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        for(WebElement tempitem:speedDropdownItems){
            if (tempitem.getText().trim().equals(itemTextExpected)){
                sleepInSeconds(1);
                tempitem.click();
                break;
            }
        }

    }




}
