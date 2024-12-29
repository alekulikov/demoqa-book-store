package api.bookstore;

import api.base.BaseApi;
import io.qameta.allure.Step;
import models.bookstore.AddBooksRequest;
import models.bookstore.AddBooksResponse;

import static io.restassured.RestAssured.given;

public class BookStoreApi extends BaseApi {

    @Step("Добавить по API список книг в профиль")
    public AddBooksResponse addBooks(AddBooksRequest request) {
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

    @Step("Удалить по API все книги из профиля")
    public void deleteAllBooks(String userId) {
        given()
                .spec(getRequestSpecification())
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(getResponseSpecification())
                .statusCode(204);
    }
}
