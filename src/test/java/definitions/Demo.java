package definitions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import cucumber.runtime.junit.Assertions;
import net.minidev.json.JSONUtil;
import org.junit.Assert;
import rest.api.ItemAPI;
import rest.pojos.Item;
import rest.utils.ResponseUtils;
import util.Constants;

public class Demo {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Response lastResponse;

    static {
        RestAssured.baseURI = "https://st2016.inv.bg";
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic("karamfilovs@gmail.com", "123456");
    }


    public static void main(String []args){
        Item item = new Item("DemoItem123", "1", "кг.");
        ItemAPI itemAPI = new ItemAPI();
        lastResponse = itemAPI.createItem(item);
        Assert.assertEquals(200, lastResponse.getStatusCode());
        String itemId = ResponseUtils.getString(lastResponse.getBody().asString(), "$.success.id");
        lastResponse = itemAPI.getItem(itemId);
        itemAPI.deleteItem(itemId);


    }
}
