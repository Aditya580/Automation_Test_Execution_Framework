package com.infosys.testingFramework.execution;

import static io.restassured.RestAssured.given;

public class ApiTestRunner {

    public boolean run(String endpoint, String method) {
        try {
            switch (method.toUpperCase()) {
                case "GET" ->
                        given().when().get(endpoint).then().statusCode(200);

                case "POST" ->
                        given().body("{}")
                                .when().post(endpoint)
                                .then().statusCode(200);

                case "PUT" ->
                        given().body("{}")
                                .when().put(endpoint)
                                .then().statusCode(200);

                case "DELETE" ->
                        given().when().delete(endpoint).then().statusCode(200);

                default -> throw new IllegalArgumentException("Unsupported method: " + method);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
