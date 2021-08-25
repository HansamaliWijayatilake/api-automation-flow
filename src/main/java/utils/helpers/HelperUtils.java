package utils.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class HelperUtils {


    public Object readJsonFile(String testDataPath, String filename, String dataName) {

        JSONParser parser = new JSONParser();
        File file = new File(testDataPath, filename);
        Map<String, JSONObject> value = null;
        try {
            value = (Map<String, JSONObject>) parser.parse(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (value.get(dataName));
    }

    public JSONArray readJsonArrayFromFile(String testDataPath, String filename, String dataName) {
        JSONParser parser = new JSONParser();
        JSONArray subjects = null;
        try {
            Object obj = parser.parse(new FileReader(testDataPath + filename));
            JSONObject jsonObject = (JSONObject) obj;

            subjects = (JSONArray) jsonObject.get(dataName);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

}
