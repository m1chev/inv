package rest.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Test;
import util.Constants;

public class BankAccountsTest {

    static {
        RestAssured.baseURI = Constants.INV_URL;
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic("karamfilovs@gmail.com", "123456");
    }

    @Test
    public void getBankAccounts(){
        Response response = RestAssured.given().contentType(ContentType.JSON).log().all().when().get("/settings/bankaccounts");
        response.prettyPrint();
        System.out.println("Status code:" + response.getStatusCode());
    }

}
