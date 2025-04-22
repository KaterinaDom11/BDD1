package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading1 = $("[data-test-id=dashboard]");
    private SelenideElement heading2 = $("h1");
    private SelenideElement amountField = $("[data-test-id='amount']"); // Поле для ввода суммы
    private SelenideElement cardNumberField = $("[data-test-id='from']");

    public TransferPage() {
        heading1.shouldBe(visible);
        heading2.shouldBe(visible);
    }
    // Метод для заполнения поля суммы перевода
    public TransferPage enterTransferAmount(int amount) {
        amountField.setValue(String.valueOf(amount)); // Устанавливаем значение суммы
        return this; // Возвращаем текущий объект для цепочки вызовов
    }
    // Метод для заполнения поля номера карты
    public TransferPage enterCardNumber(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber()); // Устанавливаем номер карты
        return this; // Возвращаем текущий объект для цепочки вызовов
    }


}
