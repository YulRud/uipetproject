package com.yulrud.uiproject.steps;

import com.yulrud.uiproject.pageObject.pages.HomePage;
import com.yulrud.uiproject.pageObject.pages.TalksLibraryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class TasksLibrarySteps extends BaseSteps {
    TalksLibraryPage talksLibraryPage = new TalksLibraryPage(this.driver);
    HomePage homePage = new HomePage(this.driver);

    public TasksLibrarySteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openHomePage(){
        homePage.openPage();
    }

    @Step
    public void navigateToVideoTab(){
        homePage.navigateToVideoTab();
        checkTalksLibraryPageIsOpened();
    }

    @Step
    public void filterByLocationCategoryLanguage(String location, String category, String language) {
        talksLibraryPage.openMoreFilters();
        talksLibraryPage.filterByLocation(location);
        talksLibraryPage.filterByCategory(category);
        talksLibraryPage.filterByLanguage(language);
    }

    @Step
    public void filterByKeyWord(String keyWord) {
        talksLibraryPage.searchByKeyWord(keyWord);
    }

    @Step
    public void checkOnlyTalksWithTitlesWithKeyWordDisplayed(String keyWord) {
        List<String> titles = talksLibraryPage.getTalksTitles();
        Assert.assertTrue(titles.stream()
                .map(t -> t.toLowerCase())
                .allMatch(t -> t.contains(keyWord.toLowerCase())), "Some titles do not contain key word");
    }

    @Step
    public void checkByTitlesOnlyExpectedTalksDisplayed(List<String> expectedTalksTitles) {
        List<String> actualTalksTitles = talksLibraryPage.getTalksTitles();
        Assert.assertEquals(actualTalksTitles, expectedTalksTitles, "Talks with not expected titles are displayed");
    }

    private void checkTalksLibraryPageIsOpened() {
        this.checkTitleIs(talksLibraryPage.getExpectedPageTitle());
    }
}
