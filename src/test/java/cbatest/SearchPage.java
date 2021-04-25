package cbatest;

import org.openqa.selenium.By;

public class SearchPage {
    public static By searchInputBox = By.id("search-input");
    public static By searchButton = By.id("search-button");
    public static By errorMessage = By.id("error-empty-query");
    public static By resultList = By.xpath("//*[@id='search-results']");
    public static By resultListItem = By.xpath("//*[@id='search-results']/li");
    public static By noResult = By.id("error-no-results");
}
