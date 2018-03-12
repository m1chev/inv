package rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import rest.pojos.Invoice;
import rest.utils.RESTClient;
import rest.utils.ResponseUtils;
import util.enums.Endpoints;

import java.util.List;

public class InvoiceAPI {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public Response getAllInvoices() {
        return RESTClient.get(Endpoints.INVOICES_ENDPOINT.getPath(), "");
    }

    public Response getInvoice(String invoiceId) {
        return RESTClient.get(Endpoints.INVOICE_ENDPOINT.getPath(), invoiceId);
    }

    public Response deleteInvoice(String invoiceId) {
        return RESTClient.delete(Endpoints.INVOICE_ENDPOINT.getPath(), invoiceId);
    }

    public Response createInvoice(Invoice invoice) {
        return RESTClient.post(Endpoints.INVOICE_ENDPOINT.getPath(), GSON.toJson(invoice));
    }

    public void deleteAllExistingInvoices() {
        Response response = getAllInvoices();
        List<String> idsForDeletion = ResponseUtils.getList(response, "$.invoices.*.id");
        idsForDeletion.forEach(id -> deleteInvoice(id));
    }

}
