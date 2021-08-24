import com.jayway.jsonpath.JsonPath;
import dataProvider.ReqResData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
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

    @Test
    public void updateUser(){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        Response response = request.contentType("application/json").put("/api/users/"+Integer.parseInt(ID));

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,HttpStatus.SC_OK);

        int id = response.getBody().path(DATA_ID);
        Assert.assertEquals(String.valueOf(id),ID);
    }
}
