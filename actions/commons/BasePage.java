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
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(locator)))
        //driver.switchTo().frame(getWebElement(driver,locator));
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
    public  void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop
                (getWebElement(driver,sourceLocator),getWebElement(driver,targetLocator)).perform();
    }
    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,locator),key).perform();
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

    public Object executeForBrowser(WebDriver driver, String javaScript) {

        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver,) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));

    }
    public void waitForListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));

    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));

    }
    public void waitForListElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locator));

    }
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver,locator)));

    }

}
