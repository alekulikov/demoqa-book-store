package api.bookstore;

import api.base.BaseApi;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class BookStoreApi extends BaseApi {

    @Step("Добавить по API книгу в профиль")
    public AddBooksResponse addBook(AddBooksRequest request) {
        return given()
                .spec(getRequestSpecification())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(getResponseSpecification())
                .statusCode(201)
                .extract().as(AddBooksResponse.class);
    }
}
