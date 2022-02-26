package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleAfterSearchPage;
import pages.GoogleBeforeSearchPage;
import pages.GooglePageWithSearch;
import pages.OpenPage;
import steps.Steps;

import java.util.List;
import java.util.Map;

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

    @Test
    public void testOpen() {
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver,"открытие");
        List<Map<String,Object>> resultSearch = googlePageWithSearch.getCollectResults();
        googlePageWithSearch.goPage("Банк Открытие");
        OpenPage openPage = new OpenPage(chromeDriver);
        List<Map<String,String>> collectExchangeRates = openPage.getCollectExchangeRates();
        System.out.println(collectExchangeRates);

        Assertions.assertTrue(
                Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк покупает").replace(",",".")
                )

                        < Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк продает").replace(",",".")
                )
        );
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют cо степами")
    @ParameterizedTest(name="{displayName} {arguments}")
    @Tag("Kotik")
    @CsvSource({"USD"})
    public void testOpenWithPage(String value){
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver,"открытие");
        List<Map<String,Object>> resultSearch = googlePageWithSearch.getCollectResults();
        Steps.checkContainsName(resultSearch, "Банк Открытие", chromeDriver);
        Steps.goPageText(googlePageWithSearch, "Банк Открытие");
        OpenPage openPage = new OpenPage( chromeDriver);
        List<Map<String,String>> collectExchangeRates = openPage.getCollectExchangeRates();
        Steps.checkCourse(value,openPage);
    }
}
