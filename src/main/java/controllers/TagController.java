package controllers;

import dao.TagDao;
import api.CreateTagRequest;
import api.ReceiptResponse;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.ReceiptTagRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import io.dropwizard.jersey.sessions.Session;
import java.util.List;

import static java.util.stream.Collectors.toList;

// For a Java class to be eligible to receive ANY requests
// it must be annotated with at least @Path
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/tags/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, Integer receipt_id) {
        System.out.println(tagName);
        System.out.println(receipt_id);
        tags.insert(tagName, receipt_id);
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getTaggedReceipts(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getAllTaggedTuples(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());

    }
}
