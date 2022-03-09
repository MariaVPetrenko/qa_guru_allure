package com.tsn;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "69 nice";

    @Test
    public void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Search the repo " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Open the repo " + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Go to the Issue tab", () -> {
            $(By.partialLinkText("Issues")).click();
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });
        step("Check if there is the Issue with name " + ISSUE_NAME, () -> {
            $(withText("69 nice")).should(Condition.exist);
        });
    }
}
