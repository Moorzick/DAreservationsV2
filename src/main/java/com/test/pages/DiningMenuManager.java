package com.test.pages;

import com.test.tools.Tools;
import org.openqa.selenium.By;

public class DiningMenuManager extends DiningMenu {
    private static By selectMenus = Tools.selectFromId("main_ddlMenu");
    private static By selectSubmenus = Tools.selectFromId("main_ddlSubMenu");

    private static By buttonAddItems = Tools.aFromId("main_lbtnApply");


    private By getSelectorToAdd (String itemName){
        String xp=String.format("//td[@class='pretty-wrap-please' and text()='%s']/preceding-sibling::td/input", itemName);
        return By.xpath(xp);
    }

    public DiningMenuManager selectItems(String itemName){
        Pages.icsHeader().check4Frame();
        click(getSelectorToAdd(itemName));
        click(buttonAddItems);
        return Pages.diningMenuManager();
    }

    public DiningMenuManager selectItems(String[] items){
        Pages.icsHeader().check4Frame();
        for (String i:items){
            click(getSelectorToAdd(i));
        }
        click(buttonAddItems);
        return Pages.diningMenuManager();
    }
}
