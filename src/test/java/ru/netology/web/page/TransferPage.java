package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading1 = $("[data-test-id=dashboard]");
    private SelenideElement heading2 = $("h1");
    private SelenideElement amountField = $("[data-test-id=amount] .input__control").shouldBe(visible); // Поле для ввода суммы
    private SelenideElement cardNumberField = $("[data-test-id=from] .input__control").shouldBe(visible); //Поле для ввода карты с которой переводим
    private SelenideElement buttonReplenish = $(".button"); // кнопка

    public TransferPage() {
        heading1.shouldBe(visible);
        heading2.shouldBe(visible);
    }


    // Метод для заполнения поля суммы перевода
    public DashboardPage enterTransferAmount(DataHelper.TransferNumber amount100, DataHelper.CardInfo cardSecondInfo) {
        amountField.setValue(String.valueOf(amount100.getAmount()));
        cardNumberField.setValue(cardSecondInfo.getNumber());
        buttonReplenish.click();
        return new DashboardPage(); // Возвращаем текущий объект для цепочки вызовов
    }
       //метод ищем поля для ввода суммы

}
