package rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import rest.pojos.Item;
import rest.utils.RESTClient;
import rest.utils.ResponseUtils;
import util.enums.Endpoints;

import java.util.List;

public class ItemAPI {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Response getAllItems() {
        return RESTClient.get(Endpoints.ITEMS_ENDPOINT.getPath(), "");
    }

    public Response getItem(String itemId) {
        return RESTClient.get(Endpoints.ITEM_ENDPOINT.getPath(), itemId);
    }

    public Response deleteItem(String itemId) {
        return RESTClient.delete(Endpoints.ITEM_ENDPOINT.getPath(), itemId);
    }

    public Response createItem(Item item) {
        return RESTClient.post(Endpoints.ITEM_ENDPOINT.getPath(), GSON.toJson(item));
    }

    public void deleteAllExistingItems() {
        Response response = getAllItems();
        List<String> idsForDeletion = ResponseUtils.getList(response, "$.*.id");
        idsForDeletion.forEach(id -> deleteItem(id));
    }


}
