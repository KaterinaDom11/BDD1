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

import static com.codeborne.selenide.Condition.visible;
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
        var transferCard = dashboardPage.selectCard(cardFirstInfo); //в странице с картами
        transferCard.enterTransferAmount(amount100, cardSecondInfo); //на странице перевода
        dashboardPage.updateCardBalance(cardFirstInfo,amount100);
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
                .shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(15)).shouldBe(visible);
        $("[class=App_appContainer__3jRx1]");
        $("[data-test-id=amount] .input__control").shouldBe(visible).setValue("100");
        $("[data-test-id=from] .input__control").shouldBe(visible).setValue("5559 0000 0000 0002");
        $(".button").click();
        $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] .list__item").shouldHave(Condition.text("10100")).shouldBe(visible);
    }
}