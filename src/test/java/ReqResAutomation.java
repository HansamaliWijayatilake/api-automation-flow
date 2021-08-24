import dataProvider.ReqResData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.StringUtils.RequestParams.JOB;
import static utils.StringUtils.RequestParams.NAME;
import static utils.StringUtils.ResponseParams.DATA_ID;
import static utils.StringUtils.TestDataParams.ID;

public class ReqResAutomation {

    public static final String baseUrl = "https://reqres.in";

    @Test(dataProviderClass = ReqResData.class, dataProvider = "addUser")
    public void createUser(String name, String job){

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        JSONObject requestPayload = new JSONObject();
        requestPayload.put(NAME, name);
        requestPayload.put(JOB, job);

        Response response = request.contentType("application/json").body(requestPayload).post("/api/users");

        ResponseBody body = response.getBody();

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,HttpStatus.SC_CREATED);

        String responseName = body.path(NAME);
        Assert.assertEquals(responseName,name);

        String responseJob = body.path(JOB);
        Assert.assertEquals(responseJob,job);

    }

    @Test
    public void getUser(){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        Response response = request.contentType("application/json").get("/api/users/"+Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,HttpStatus.SC_OK);

        int id = response.getBody().path(DATA_ID);
        Assert.assertEquals(String.valueOf(id),ID);
    }

    @Test(dataProviderClass = ReqResData.class, dataProvider = "updateUser")
    public void updateUser(String name){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        JSONObject requestPayload = new JSONObject();
        requestPayload.put(NAME, name);

        Response response = request.contentType("application/json").body(requestPayload).put("/api/users/"+Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,HttpStatus.SC_OK);

        String nameResponse = response.getBody().path(NAME);
        Assert.assertEquals(name,nameResponse);
    }
}
