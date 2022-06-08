package pl.edu.pjwstk.tau;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTesting {
    @Test
    public void checkStatus() {
        given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/").
                then().
                assertThat().
                log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    public void checkIfStatusFailAfterPuttingWrongUrl() {
        given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/wrong").
                then().
                assertThat().
                statusCode(404);
    }
    @Test
    public void checkIfResponseBodyContainThatTitle() {
        var response = given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/1")
                .getBody()
                .asString();
        Assert.assertTrue("Response body contains that title", response.contains("delectus aut autem"));
    }
    @Test
    public void checkIfResponseBodyContainThatStatusOfCompletedTask() {
        var response = given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/1")
                .getBody()
                .asString();
        Assert.assertTrue("Response body contains that status", response.contains("false"));
    }
    @Test
    public void checkIfResponseBodyContainThatUserId() {
        var response = given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/1")
                .getBody()
                .asString();
        Assert.assertTrue("Response body contains that user id", response.contains("1"));
    }

    @Test
    public void checkIfResponseBodiesContainIdEqualsTo5() {
        var response = given().
                when().
                get("https://jsonplaceholder.typicode.com/todos")
                .getBody()
                .asString();
        Assert.assertTrue("Response bodies contains id = 5", response.contains("5"));
    }

    @Test
    public void confirmThatThisTitleIsWrong() {
        var response = given().
                when().
                get("https://jsonplaceholder.typicode.com/todos")
                .getBody()
                .asString();
        Assert.assertNotSame("Response body shouldn't include this title", response.contains("TAU 2022"));
    }
}
