package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    ;

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private SelenideElement getCardInfo(DataHelper.CardInfo cardInfo) { //ищем карту по ID
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) { //ищем баланс, принимает объект номер карты
        var text = getCardInfo(cardInfo).getText(); //getCardInfo-выполняет поиск карты, получает текст getText
        return extractBalance(text);
    }

    public TransferPage selectCard(DataHelper.CardInfo cardInfo) {
        getCardInfo(cardInfo).$("button").click(); //getCardInfo(cardInfo) - ищет карту, далее в этом же элементе ищет кнопку
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int updateCardBalance(DataHelper.CardInfo cardInfo, DataHelper.TransferNumber transferAmount) {
        int currentBalance = getCardBalance(cardInfo); // Получаем текущий баланс
        int newBalance = currentBalance + transferAmount.getAmount(); // Обновляем баланс
        enterTransferAmount(cardInfo, transferAmount.getAmount());

        // Проверяем, что текст на карточке соответствует новому балансу
        getCardInfo(cardInfo).shouldHave(Condition.text(String.valueOf(newBalance))); // Приводим newBalance к строке

        // Обновляем интерфейс
        refreshUI(); // Обновляем интерфейс после выполнения перевода

        return newBalance;; // Возвращаем новый баланс
    }

}