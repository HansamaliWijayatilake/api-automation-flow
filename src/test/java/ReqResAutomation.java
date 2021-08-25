import data.ReqResDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static utils.params.QueryParams.DELAY;
import static utils.params.RequestParams.JOB;
import static utils.params.RequestParams.NAME;
import static utils.params.ResponseParams.DATA_ID;
import static utils.params.TestDataParams.ID;

public class ReqResAutomation {

    public static final String baseUrl = "https://reqres.in";

    @Test(dataProviderClass = ReqResDataProvider.class, dataProvider = "addUser")
    public void createUser(String name, String job) {

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        JSONObject requestPayload = new JSONObject();
        requestPayload.put(NAME, name);
        requestPayload.put(JOB, job);

        Response response = request.contentType(ContentType.JSON).body(requestPayload).post("/api/users");

        ResponseBody body = response.getBody();

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);

        String responseName = body.path(NAME);
        Assert.assertEquals(responseName, name);

        String responseJob = body.path(JOB);
        Assert.assertEquals(responseJob, job);

    }

    @Test(dataProviderClass = ReqResDataProvider.class, dataProvider = "addUsers")
    public void createUsers(String name, String job) {

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        JSONObject requestPayload = new JSONObject();
        requestPayload.put(NAME, name);
        requestPayload.put(JOB, job);

        Response response = request.contentType(ContentType.JSON).body(requestPayload).post("/api/users");

        ResponseBody body = response.getBody();

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);

        String responseName = body.path(NAME);
        Assert.assertEquals(responseName, name);

        String responseJob = body.path(JOB);
        Assert.assertEquals(responseJob, job);

    }

    @Test
    public void getUser() {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        Response response = request.contentType(ContentType.JSON).get("/api/users/" + Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);

        int id = response.getBody().path(DATA_ID);
        Assert.assertEquals(String.valueOf(id), ID);
    }

    @Test(dataProviderClass = ReqResDataProvider.class, dataProvider = "updateUser")
    public void updateUser(String name) {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        JSONObject requestPayload = new JSONObject();
        requestPayload.put(NAME, name);

        Response response = request.contentType(ContentType.JSON).body(requestPayload).put("/api/users/" + Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);

        String nameResponse = response.getBody().path(NAME);
        Assert.assertEquals(name, nameResponse);
    }

    @Test
    public void deleteUser() {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        Response response = request.contentType(ContentType.JSON).delete("/api/users/" + Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void delayResponseTest() {
        try {
            Awaitility.await().atMost(6, TimeUnit.SECONDS).until(() -> this.getStatus() == 200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private int getStatus() {
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        JSONObject queryParams = new JSONObject();
        queryParams.put(DELAY, 3);

        Response response = request.contentType(ContentType.JSON).queryParam(queryParams.toJSONString()).get("/api/users");

        int statusCode = response.statusCode();
        return statusCode;
    }
}
