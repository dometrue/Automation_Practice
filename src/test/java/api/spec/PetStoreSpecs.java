package api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class PetStoreSpecs {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .log(io.restassured.filter.log.LogDetail.ALL) // 👈 log request
                .build();
    }

    public static ResponseSpecification response200Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(io.restassured.filter.log.LogDetail.ALL) // 👈 log response
                .build();
    }
}
