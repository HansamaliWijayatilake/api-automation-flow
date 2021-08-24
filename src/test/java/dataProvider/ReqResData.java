package dataProvider;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import utils.helpers.HelperUtils;
import java.util.*;

import static utils.StringUtils.TestDataParams.*;

public class ReqResData {

    @DataProvider(name = "addUser")
    public Object[][] createUserTestData() {

        HelperUtils helperUtils = new HelperUtils();

        Object jsonFileContents =  helperUtils.readJsonFile(TEST_DATA_PATH,CREATE_USER_JSON_FILE,CREATE_USER_JSON_PATH);
        List<Object> userDetails = Arrays.asList(jsonFileContents);


        Object[][] dataProvider = new Object[userDetails.size()][2];
        userDetails.forEach(userObj -> {
            JSONObject user = (JSONObject) userObj;
            dataProvider[userDetails.indexOf(jsonFileContents)][0] = user.get("name");
            dataProvider[userDetails.indexOf(jsonFileContents)][1] = user.get("job");

        });


        return dataProvider;
    }


}
