package rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import rest.pojos.Client;
import util.enums.Endpoints;
import util.RESTClient;

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

    public Response createItem(Client client) {
        return RESTClient.post(Endpoints.ITEM_ENDPOINT.getPath(), GSON.toJson(client));
    }
}
