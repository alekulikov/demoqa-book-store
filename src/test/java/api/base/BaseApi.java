package api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Base64;

import static data.Data.USER_NAME;
import static data.Data.USER_PASSWORD;
import static helpers.AllureRestAssuredHelper.withAllureListener;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BaseApi {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .addFilter(withAllureListener())
                .setContentType(JSON)
                .addHeader("authorization", "Basic " +
                        Base64.getEncoder().encodeToString((USER_NAME + ':' + USER_PASSWORD).getBytes()))
                .log(ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectContentType(JSON)
                .build();
    }

    public static ResponseSpecification getResponseSpecificationWithoutBody() {
        return new ResponseSpecBuilder()
                .log(ALL)
                .build();
    }
}
