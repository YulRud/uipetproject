package com.yulrud.uiproject.TaskLibraryPage;

import com.yulrud.uiproject.BaseTest;
import com.yulrud.uiproject.pageObject.pages.HomePage;
import com.yulrud.uiproject.steps.TasksLibrarySteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class TaskLibraryFilterTest extends BaseTest {
    HomePage homePage;
    TasksLibrarySteps tasksLibrarySteps;


    @BeforeMethod
    public void before() {
        homePage = new HomePage(this.driver);
        tasksLibrarySteps = new TasksLibrarySteps(this.driver);

    }

    //Task 6
    @Test(description = "Check that Category, Location and Language filters filter expected talks")
    public void checkCategoryAndLocationAndLanguageFiltersFilterValidValues() {
        tasksLibrarySteps.openHomePage();
        tasksLibrarySteps.navigateToVideoTab();
        tasksLibrarySteps.filterByLocationCategoryLanguage("Belarus", "Testing", "ENGLISH");
        tasksLibrarySteps.checkByTitlesOnlyExpectedTalksDisplayed(Collections.singletonList("Testing takeover - success story"));
    }

    //Task 7
    @Test(description = "Check that filtering by key word filters only talk cards with title with this key word")
    public void checkKeyWordFilterValidValues() {
        String keyWord = "qa";
        tasksLibrarySteps.openHomePage();
        tasksLibrarySteps.navigateToVideoTab();
        tasksLibrarySteps.filterByKeyWord(keyWord);
        tasksLibrarySteps.checkOnlyTalksWithTitlesWithKeyWordDisplayed(keyWord);
    }
}
