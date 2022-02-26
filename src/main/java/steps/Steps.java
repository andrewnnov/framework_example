package steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.GooglePageWithSearch;
import pages.OpenPage;

import java.util.List;
import java.util.Map;

public class Steps {

    @Step("Проверка наличия имени: {name}")
    public static void checkContainsName(List<Map<String,Object>> resultSearch, String name, WebDriver driver) {

        //Screenshoter.getScreen(driver);
        Assertions.assertTrue(resultSearch.stream().anyMatch(x->x.get("NAME_PAGE").toString().contains(name)),
                "Не найден заголовок содержащий: " + name);
    }

    @Step("Перейдём по ссылке с текстом {textTitle}")
    public static void goPageText(GooglePageWithSearch googleWithSearch, String textTitle) {
        Assertions.assertTrue(googleWithSearch.goPage(textTitle), "Страница "+textTitle+" не найдена");
    }

    @Step("Проверка курса {moneyType}")
    public static void checkCourse( String moneyType, OpenPage openPage){
        //Screenshoter.getScreen(openPage.getDriver(),openPage.getExchangeRates());
        Assertions.assertTrue(
                Double.parseDouble(
                        openPage.getCollectExchangeRates().stream()
                                .filter(x->x.get("Валюта обмена").contains(moneyType))
                                .findFirst()
                                .get().get("Банк покупает").replace(",","."))
                        <

                        Double.parseDouble(
                                openPage.getCollectExchangeRates().stream()
                                        .filter(x->x.get("Валюта обмена").contains(moneyType))
                                        .findFirst()
                                        .get().get("Банк продает").replace(",","."))
        );

    }
}
