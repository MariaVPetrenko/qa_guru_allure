package com.tsn;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnnotationStep {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "69 nice";

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search the repo " + REPOSITORY)
    public void searchForRepository() {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
    }

    @Step("Open the repo " + REPOSITORY)
    public void openRepository() {
        $(By.linkText(REPOSITORY)).click();
    }

    @Step("Go to the Issue tab")
    public void openIssueTab() {
        $(By.partialLinkText("Issues")).click();
    }

    @Step("Check if there is the Issue with name " + ISSUE_NAME)
    public void shouldSeeIssueWithName() {
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
