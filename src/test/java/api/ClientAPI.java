package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Pages;

public class ClientAPI {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClientAPI.class);
    private static final String CLIENT_ENDPOINT = "/client";
    private static final String CLIENTS_ENDPOINT = "/clients";
    private static final String POST_FIX = "RESTapi";

    static {
        RestAssured.baseURI = Pages.INV_URL.getPath();
        RestAssured.basePath = POST_FIX;
        RestAssured.authentication = RestAssured.preemptive().basic("karamfilovs@gmail.com", "123456");
    }

    public void getAllClients() {
        Response response = RestAssured.given().contentType(ContentType.JSON).log().all().get(CLIENTS_ENDPOINT);
        LOGGER.info("Response:" + response.getBody().asString());
    }


}
