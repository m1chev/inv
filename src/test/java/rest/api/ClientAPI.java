package rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import rest.pojos.Client;
import rest.utils.RESTClient;
import rest.utils.ResponseUtils;
import util.enums.Endpoints;

import java.util.List;

public class ClientAPI {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public Response getAllClients() {
        return RESTClient.get(Endpoints.CLIENTS_ENDPOINT.getPath(), "");
    }

    public Response getClient(String clientId) {
        return RESTClient.get(Endpoints.CLIENT_ENDPOINT.getPath(), clientId);
    }

    public Response deleteClient(String clientId) {
        return RESTClient.delete(Endpoints.CLIENT_ENDPOINT.getPath(), clientId);
    }

    public Response createClient(Client client) {
        return RESTClient.post(Endpoints.CLIENT_ENDPOINT.getPath(), GSON.toJson(client));
    }

    public void deleteAllExistingClients() {
        Response response = getAllClients();
        List<String> idsForDeletion = ResponseUtils.getList(response, "$.*.id");
        idsForDeletion.forEach(id -> deleteClient(id));
    }


}
