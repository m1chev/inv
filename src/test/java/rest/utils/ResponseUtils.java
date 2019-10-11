package rest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ResponseUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtils.class);

    public static List<String> getList(Response response, String path) {
        LOGGER.info("Extracting list of values from response body with path:" + path);
        List<String> values = JsonPath.read(response.getBody().asString(), path);
        LOGGER.info("Values found:" + values.toString());
        return values;
    }

    public static String getString(String responseBody, String path) {
        LOGGER.info("Extracting value from response body with path:" + path);
        String value = JsonPath.read(responseBody, path).toString();
        LOGGER.info("Value found:" + value);
        return value;
    }

}
