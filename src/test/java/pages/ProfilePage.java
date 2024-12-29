package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    SelenideElement tableItem = $(".rt-tbody");
    SelenideElement deleteButton = $("#delete-record-undefined");
    SelenideElement okButton = $("#closeSmallModal-ok");


    @Step("Открыть профиль")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Убрать баннеры")
    public ProfilePage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Проверить, что корзина пуста")
    public ProfilePage checkProfileIsEmpty() {
        tableItem.shouldBe(empty);
        return this;
    }

    @Step("Проверить, что корзина не пуста")
    public ProfilePage checkProfileIsNotEmpty() {
        tableItem.shouldNotBe(empty);
        return this;
    }

    @Step("Удалить книгу из списка")
    public ProfilePage deleteBook() {
        deleteButton.click();
        okButton.click();
        return this;
    }

    @Step("Проверить по isbn, что в корзине содержится книга")
    public ProfilePage checkBookExistByIsbn(String isbn) {
        tableItem.shouldHave(href("profile?book=" + isbn));
        return this;
    }
}
