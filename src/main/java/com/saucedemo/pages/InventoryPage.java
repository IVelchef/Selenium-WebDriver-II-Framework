package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BaseSauceDemoPage {

    public InventoryPage() {
        super("/inventory.html");
    }

    // Locators
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By pageTitle = By.xpath("//span[@class='title' and text()='Products']");
    private final By burgerMenu = By.xpath("//div[@class='bm-burger-button']");
    private final By logoutButton = By.xpath("//a[@data-test='logout-sidebar-link']");
    private final By burgerButton = By.id("react-burger-menu-btn");
    private final By resetSidebarLink = By.id("reset_sidebar_link");



    // Actions
    public void addProductsByTitle(String... titles) {
        for (String title : titles) {
            var container = driver().findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
            container.findElement(By.className("btn_inventory")).click();
        }
    }
    public void clickShoppingCartLink() {
        driver().findElement(shoppingCartLink).click();
    }
    public Integer getShoppingCartItemsNumber() {
        String itemsCountString = driver().findElement(shoppingCartLink).getText().trim();
        if (itemsCountString.isEmpty()){
            return 0;
        }
        else {
            return Integer.parseInt(itemsCountString);
        }
    }
    public void waitForPageTitle() {
        var title = driver().findElement(pageTitle);
        driverWait().until(ExpectedConditions.visibilityOf(title));
    }

    public void logout() {
        driver().findElement(burgerMenu).click();
        var logoutBtn = driver().findElement(logoutButton);
        driverWait().until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutBtn.click();
    }

    public void resetShoppingCart() {
        driver().findElement(burgerButton).click();
        driver().findElement(resetSidebarLink).click();
        driver().navigate().refresh();
    }
}