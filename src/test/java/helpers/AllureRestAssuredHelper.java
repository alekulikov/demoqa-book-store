package helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class AllureRestAssuredHelper {

    public static AllureRestAssured withAllureListener() {
        var listener = new AllureRestAssured();
        listener.setRequestTemplate("request.ftl");
        listener.setResponseTemplate("response.ftl");
        return listener;
    }
}
