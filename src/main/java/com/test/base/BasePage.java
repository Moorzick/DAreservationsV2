package com.test.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    //public WebDriver driver;
    public WebDriverWait wait = new WebDriverWait(BaseTest.driver, 30000);



    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void waitVisibility(String selector) {
        waitVisibility(By.xpath(selector));
    }

    public void waitVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    //Click Method
    public void click (By elementBy) {
        waitVisibility(elementBy);
        new Actions(BaseTest.driver).moveToElement(BaseTest.driver.findElement(elementBy)).click().build().perform();
        //BaseTest.getDriver().findElement(elementBy).click();
    }

    public void click (WebElement element) {
        waitVisibility(element);
        new Actions(BaseTest.driver).moveToElement(element).click().build().perform();
    }

    public void click (String selector){
        click(By.xpath(selector));
    }

    public void rightClick (By elementBy){
        waitVisibility(elementBy);
        Actions actions = new Actions(BaseTest.getDriver());
        WebElement targetElement = BaseTest.driver.findElement(elementBy);
        actions.moveToElement(targetElement).contextClick().build().perform();
    }

    public void rightClick (String selector){
        rightClick(By.xpath(selector));
    }

    public void rightClick (WebElement element){
        waitVisibility(element);
        Actions actions = new Actions(BaseTest.getDriver());
        actions.moveToElement(element).contextClick().build().perform();
    }

    //Write Text
    public void writeText (By elementBy, String text) {
        waitVisibility(elementBy);
        BaseTest.getDriver().findElement(elementBy).clear();
        BaseTest.getDriver().findElement(elementBy).sendKeys(text);
    }

    public void writeText (String selector, String text) {
        writeText(By.xpath(selector),text);
    }

    //Read Text
    public String readText (By elementBy) {
        waitVisibility(elementBy);
        return BaseTest.getDriver().findElement(elementBy).getText();
    }

    //Assert
    public void assertEquals (By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);

    }

    public void hoverAbove (By elementBy) {
        waitVisibility(elementBy);
        WebElement target = BaseTest.getDriver().findElement(elementBy);
        Actions actions = new Actions(BaseTest.getDriver());
        actions.moveToElement(target);
    }

    public void switch2Frame (By elementBy) {
        waitVisibility(elementBy);
        WebElement target = BaseTest.getDriver().findElement(elementBy);
        BaseTest.getDriver().switchTo().frame(target);
    }

    public void switchOutOfFrame () {
        BaseTest.getDriver().switchTo().parentFrame();
    }

    public boolean verifyElementExist (By elementBy) {
    if (!BaseTest.getDriver().findElements(elementBy).isEmpty()){
        return true;
    }
    else {
        return false;
        }
    }
    public boolean verifyElementExist (String selector) {
        return verifyElementExist(By.xpath(selector));
    }


    public boolean verifyElementVisible (By elementBy){
        WebElement webElement = BaseTest.getDriver().findElement(elementBy);
        System.out.println(String.format("Checking if %s is enabled", elementBy.toString()));
        if (webElement.isEnabled())
        {
            System.out.println("Enabled");
            return true;
        }
        else {
            System.out.println("Disabled");
            return false;
        }
    }

    public String getAText(By elementBy){
        waitVisibility(elementBy);
        WebElement webElement = BaseTest.getDriver().findElement(elementBy);
        return webElement.getText();
    }

    public String getAText (String selector){
        return getAText(By.xpath(selector));
    }

    public boolean verifyIsChecked (By elementBy){
        boolean isChecked;
        WebElement webElement = BaseTest.driver.findElement(elementBy);
        String checked = webElement.getAttribute("checked");
        if (checked!=null){
            isChecked=true;
        }
        else {
            isChecked=false;
        }
        return isChecked;
    }

    public boolean verifyIsChecked (String selector){
        return verifyIsChecked(By.xpath(selector));
    }

    public void alertAccept(){
        BaseTest.driver.switchTo().alert().accept();
    }

    public void alertDecline(){
        BaseTest.driver.switchTo().alert().dismiss();
    }

    public void waitForElementToDisappear (By by){
        wait.until(ExpectedConditions.invisibilityOf(BaseTest.driver.findElement(by)));
    }

    public void droplistSelectByName (By droplistBy, String optionName){
        waitVisibility(droplistBy);
        Select droplist = new Select(BaseTest.driver.findElement(droplistBy));
        droplist.selectByVisibleText(optionName);
    }

    public void droplistSelectByIndex (By droplistBy, int index){
        waitVisibility(droplistBy);
        Select droplist = new Select(BaseTest.driver.findElement(droplistBy));
        droplist.selectByIndex(index);
    }

    public Select getDroplist (By droplistBy){
        waitVisibility(droplistBy);
        return new Select(BaseTest.driver.findElement(droplistBy));
    }

    public List<WebElement> getAllElements (By by){
        List<WebElement> elementsSet = BaseTest.driver.findElements(by);
        return elementsSet;
    }

    public List<WebElement> getAllElements (String selector){
        By by = By.xpath(selector);
        List<WebElement> elementsSet = BaseTest.driver.findElements(by);
        return elementsSet;
    }

    public int getAllElementsCount (By by){
        waitVisibility(by);
        return BaseTest.driver.findElements(by).size();
    }



    public String getFieldValue (By by){
        waitVisibility(by);
        String value;
        String retrievedValue = BaseTest.driver.findElement(by).getAttribute("value");
        if (retrievedValue!=null){
            value=retrievedValue;
        }
        else {
            value="";
        }
        return value;
    }

    public String getFieldValue (String selector, int index){
        return getFieldValue(By.xpath(String.format(selector, index)));
    }

    public String getActiveOptionText (String selectId){
        String xpath = String.format("//select[@id='%s']/option[@selected='selected']", selectId);
        return getAText(By.xpath(xpath));
    }

    public void sendKeys (By elementBy, String charSequence){
        BaseTest.driver.findElement(elementBy).sendKeys(charSequence);
    }

    public void dragNdrop (By source, By target){
        WebElement elementSource = BaseTest.driver.findElement(source);
        WebElement elementTarget = BaseTest.driver.findElement(target);
        new Actions(BaseTest.driver).dragAndDrop(elementSource, elementTarget).perform();
    }

    public void dragNdropWShifting (By source, By target){
        WebElement elementSource = BaseTest.driver.findElement(source);
        WebElement elementTarget = BaseTest.driver.findElement(target);
        Point sourcePoint = elementSource.getLocation();
        System.out.println("sourceX: "+sourcePoint.getX());
        System.out.println("sourceY: "+sourcePoint.getY());
        Point targetPoint = elementTarget.getLocation();
        int targetX = targetPoint.getX();
        int targetY = targetPoint.getY();
        System.out.println("targetX: "+targetX);
        System.out.println("targetY: "+targetY);
        int shiftX = 0;
        int shiftY = 0;
        int newX = targetX+shiftX;
        int newY = targetY+shiftY;
        System.out.println("dnd");
        new Actions(BaseTest.driver).dragAndDropBy(elementSource, newX, newY).perform();
    }

    public Point getCoordinates (By by){
        WebElement element = BaseTest.driver.findElement(by);
        return element.getLocation();
    }

    public void check (By checkbox) throws InterruptedException {
        waitVisibility(checkbox);
        if (!verifyIsChecked(checkbox)){
            System.out.println("Checkbox is not checked, checking");
            click(checkbox);
        }
        else {
            System.out.println("Checkbox is already checked, skipping");
        }
    }

    public void check (String selector) throws InterruptedException {
        check(By.xpath(selector));
    }


    public void uncheck (By checkbox){
        waitVisibility(checkbox);
        if (verifyIsChecked(checkbox)){
            System.out.println("Checkbox is not checked, unchecking");
            click(checkbox);
        }
        else {
            System.out.println("Checkbox is already unchecked, skipping");
        }
    }

    public void uncheck (String selector){
        uncheck(By.xpath(selector));
    }

    public String getAttribute (By element, String attribute){
        return BaseTest.driver.findElement(element).getAttribute(attribute);
    }

    public By getObjectFromSelector (String selector, Object identifier){
        return By.xpath(String.format(selector, identifier));
    }

    public String getCurrentUrl (){
        return BaseTest.driver.getCurrentUrl();
    }
}