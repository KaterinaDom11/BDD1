package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.getAuthInfo;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var info = getAuthInfo(); //добавляем в переменную info - логин и пароль пользователя
        var verificationCode = DataHelper.getVerificationCodeFor(info); // в переменную verificationCode - добавляем проверочный код
        var loginPage = Selenide.open("http://localhost:9999", LoginPage.class); //запускам страницу и используем пейдж с сохраненными полями страницы
        var verificationPage = loginPage.validLogin(info); // положили в loginPage валидные логин и пароль пользователя
        var dashboardPage = verificationPage.validVerification(verificationCode); //вызываем метод validVerification он вставляет код и нажимает на кнопку. Код хранится в verificationCode

    }


}