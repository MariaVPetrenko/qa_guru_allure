package com.tsn;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WithAnnotationsTest {
    @Test
    @Owner("Maria Petrenko")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking repos")
    @Story("Search an issue")
    @DisplayName("Check that issue with a specific name exists")
    @Link(value = "Testing", url = "https://github.com")

    public void issueSearchWithAnnotationSteps() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    AnnotationStep steps = new AnnotationStep();

    steps.openMainPage();
    steps.searchForRepository();
    steps.openRepository();
    steps.openIssueTab();
    steps.shouldSeeIssueWithName();
    steps.takeScreenshot();
    }
}
