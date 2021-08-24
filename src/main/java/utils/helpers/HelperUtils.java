package utils.helpers;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class HelperUtils {


    public Object readJsonFile(String testDataPath, String filename, String dataName){

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

}
