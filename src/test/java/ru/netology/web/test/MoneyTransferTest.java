package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;


import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.data.DataHelper.getAuthInfo;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() { // проверка перевода с первой карты на вторую
        var info = getAuthInfo(); //добавляем в переменную info - логин и пароль пользователя (пользователь Вася)
        var verificationCode = DataHelper.getVerificationCodeFor(info); // в переменную verificationCode - добавляем проверочный код
        var cardFirstInfo = DataHelper.getFerstCardInfo(); //номер первой карты
        var cardSecondInfo = DataHelper.getSecondCardInfo(); //номер второй карты
        var amount100 = DataHelper.genTransferNumber();

        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class); //запускам страницу и используем пейдж с сохраненными полями страницы
        var verificationPage = loginPage.validLogin(info); // положили в loginPage валидные логин и пароль пользователя, нажали клик и перешли на страницу вертификации (verificationPage)
        var dashboardPage = verificationPage.validVerification(verificationCode); //на странице вертификации заполнили код и переходим на страницу личный кабинет (dashboardPage)
        var transferCard = dashboardPage.selectCard(cardFirstInfo);
        var transferAmount = transferCard.enterTransferAmount(amount100, cardSecondInfo);

        }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var info = getAuthInfo(); //добавляем в переменную info - логин и пароль пользователя (пользователь Вася)
        var verificationCode = DataHelper.getVerificationCodeFor(info); // в переменную verificationCode - добавляем проверочный код
        var cardFirstInfo = DataHelper.getFerstCardInfo(); //номер первой карты
        var cardSecondInfo = DataHelper.getSecondCardInfo(); //номер второй карты
        var amount100 = DataHelper.genTransferNumber();

        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class); //запускам страницу и используем пейдж с сохраненными полями страницы
        var verificationPage = loginPage.validLogin(info); // положили в loginPage валидные логин и пароль пользователя, нажали клик и перешли на страницу вертификации (verificationPage)
        var dashboardPage = verificationPage.validVerification(verificationCode); //на странице вертификации заполнили код и переходим на страницу личный кабинет (dashboardPage)
        var transferCard = dashboardPage.selectCard(cardFirstInfo);
        $("[data-test-id='dashboard']")
                .shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("[class=App_appContainer__3jRx1]");
        $("[data-test-id=amount] .input_type_text").click();

        $("[data-test-id=amount] .money-input__value").setValue("100");
    }
}