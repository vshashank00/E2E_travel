package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Datatofeedon {
    
    @DataProvider(name = "data",parallel = true)
    Object[][] signupData() throws IOException {
        List<HashMap<String,String>>hashMapList=getdatafromjson(System.getProperty("user.dir")+"/src/test/java/Data/Signup.json");

        return new Object[][]{{hashMapList.get(0)}};

    }
    @DataProvider(name = "logindata")
    Object[][] loginData() throws IOException {
        List<HashMap<String,String>>hashMapList=getdatafromjson(System.getProperty("user.dir")+"/src/test/java/Data/Login_Detail.json");

        return new Object[][]{{hashMapList.get(0)}};

    }
    @DataProvider(name="timeofflight" ,parallel = true)
        Object[][] timeofflight() throws IOException {
        List<HashMap<String,String>>list=getdatafromjson(System.getProperty("user.dir")+"/src/test/java/Data/Time.json");
        return new Object[][]{{list.get(0)}};


    }

    List<HashMap<String, String>> getdatafromjson(String path) throws IOException {
        String s= FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        ObjectMapper objectMapper=new ObjectMapper();
        List<HashMap<String,String>>list=objectMapper.readValue(s, new TypeReference<List<HashMap<String, String>>>(){});
        return list;


    }
}
