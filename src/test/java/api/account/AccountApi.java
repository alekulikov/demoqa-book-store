package api.account;

import api.base.BaseApi;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class AccountApi extends BaseApi {

    @Step("Авторизоваться по API")
    public LoginResponse login(LoginRequest request) {
        return given()
                .spec(getRequestSpecification())
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(getResponseSpecification())
                .statusCode(200)
                .extract().as(LoginResponse.class);
    }
}
