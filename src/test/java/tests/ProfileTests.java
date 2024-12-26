package tests;

import api.account.LoginResponse;
import api.bookstore.AddBooksRequest;
import api.bookstore.BookStoreApi;
import api.bookstore.Isbn;
import helpers.WithLogin;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.List;

import static data.Data.ISBN;

@Feature("Профиль пользователя")
@Story("Удаление книг из профиля")
@Owner("alekulikov")
@Link(value = "Testing", url = "https://github.com/alekulikov/demoqa-book-store")
class ProfileTests extends TestBase {

    public ProfilePage profilePage = new ProfilePage();
    public BookStoreApi bookStoreApi = new BookStoreApi();

    @Test
    @WithLogin
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    @DisplayName("Успешное удаление книги из профиля")
    void deleteBookFromProfile(LoginResponse loginResponse) {
        bookStoreApi.addBook(new AddBooksRequest(loginResponse.getUserId(),
                List.of(new Isbn(ISBN))));
        profilePage.openPage()
                .removeBanners()
                .checkAddedBook()
                .deleteBook()
                .checkProfileIsEmpty();
    }
}
