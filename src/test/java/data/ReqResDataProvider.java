package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import utils.helpers.HelperUtils;

import java.util.Arrays;
import java.util.List;

import static utils.params.TestDataParams.*;

public class ReqResDataProvider {

    @DataProvider(name = "addUser")
    public Object[][] createUserTestData() {

        HelperUtils helperUtils = new HelperUtils();

        Object jsonFileContents = helperUtils.readJsonFile(TEST_DATA_PATH, CREATE_USER_JSON_FILE, CREATE_USER_JSON_PATH);
        List<Object> userDetails = Arrays.asList(jsonFileContents);


        Object[][] dataProvider = new Object[userDetails.size()][2];
        userDetails.forEach(userObj -> {
            JSONObject user = (JSONObject) userObj;
            dataProvider[userDetails.indexOf(jsonFileContents)][0] = user.get("name");
            dataProvider[userDetails.indexOf(jsonFileContents)][1] = user.get("job");

        });


        return dataProvider;
    }

    @DataProvider(name = "addUsers")
    public Object[][] createUsersTestData() {

        HelperUtils helperUtils = new HelperUtils();
        JSONArray jsonFileContents = helperUtils.readJsonArrayFromFile(TEST_DATA_PATH, CREATE_USER_JSON_FILE, USERS_JSON_PATH);
        Object[][] dataProvider = new Object[jsonFileContents.size()][2];

        jsonFileContents.forEach(obj -> {
            JSONObject resultItem = (JSONObject) obj;
            String planName = (String) resultItem.get("name");
            String job = (String) resultItem.get("job");
            dataProvider[jsonFileContents.indexOf(obj)][0] = planName;
            dataProvider[jsonFileContents.indexOf(obj)][1] = job;

        });

        return dataProvider;
    }

    @DataProvider(name = "updateUser")
    public Object[][] updateUserTestData() {

        HelperUtils helperUtils = new HelperUtils();

        Object jsonFileContents = helperUtils.readJsonFile(TEST_DATA_PATH, UPDATE_USER_JSON_FILE, UPDATE_USER_JSON_PATH);
        List<Object> userDetails = Arrays.asList(jsonFileContents);


        Object[][] dataProvider = new Object[userDetails.size()][1];
        userDetails.forEach(userObj -> {
            JSONObject user = (JSONObject) userObj;
            dataProvider[userDetails.indexOf(jsonFileContents)][0] = user.get("name");
        });


        return dataProvider;
    }

}
