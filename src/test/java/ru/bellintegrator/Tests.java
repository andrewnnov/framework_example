package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleAfterSearchPage;
import pages.GoogleBeforeSearchPage;

public class Tests extends BaseTest{

    @Feature("Проверка результаов поиска")
    @DisplayName("проверка результатов поиска с помощью ПО")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Гладиолус, https://ru.wikipedia.org"})
    public void findWikiLinkOnPageTest(String keyWord, String result) {
        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearchPage googleBeforeSearch = new GoogleBeforeSearchPage(chromeDriver);
        googleBeforeSearch.find(keyWord);
        GoogleAfterSearchPage googleAfterSearch = new GoogleAfterSearchPage(chromeDriver);
        Assertions.assertTrue(googleAfterSearch.getResults().stream().anyMatch(x->x.getText()
                .contains(result)), "Ссылки на викепедию не найдены");
    }
}
