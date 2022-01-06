package com.bookIt.stepDefinitions;

import com.bookIt.utils.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIStep_defs {

    String token;

    //public static String ApiLastName;
    public static String ApiRole;
    public static String ApiFirstName;
    public static String ApiTeam;
    public static String ApiCampus;
    public static String ApiBatch;



    @When("User logs in BookIt API using {string} and {string}")
    public void user_logs_in_BookIt_API_using_and(String email, String password) {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get(ConfigurationReader.get("qa3api.url") + "/sign")
                .then()
                .statusCode(200)
                .extract().response();

        String accessToken = response.path("accessToken" );
        token = "Bearer " + accessToken;

    }

    @Then("User gets API information")
    public void user_gets_API_information() {
        System.out.println("finalToken = " + token);

        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when()
                .get(ConfigurationReader.get("qa3api.url") + "/api/users/me")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        ApiFirstName = jsonPath.getString("firstName")+" "+jsonPath.getString("lastName");
       // ApiLastName = jsonPath.getString("lastName"); full names shows in users profile
        ApiRole = jsonPath.getString("role");

        System.out.println("ApiFirstName = " + ApiFirstName);
        //System.out.println("ApiLastName = " + ApiLastName);
        System.out.println("ApiRole = " + ApiRole);//student team member

        JsonPath jsonPathTeam = RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when()
                .get(ConfigurationReader.get("qa3api.url") + "/api/teams/my")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        ApiTeam = jsonPathTeam.getString("name");
        System.out.println("ApiTeam = " + ApiTeam);


        ///api/batches

        JsonPath batch = RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when()
                .get(ConfigurationReader.get("qa3api.url") + "/api/batches/my")
                .then()
                .statusCode(200)
                .extract().jsonPath();
            ApiBatch = "#" + batch.getString("number");
        System.out.println("ApiBatch = " + ApiBatch);


        JsonPath campus = RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when()
                .get(ConfigurationReader.get("qa3api.url") + "/api/campuses/my")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        ApiCampus = campus.getString("location");
        System.out.println("campus = " + campus);

    }
}
