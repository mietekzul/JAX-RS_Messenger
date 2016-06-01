package pl.raziel.jaxrs.resources;

import pl.raziel.jaxrs.model.Message;
import pl.raziel.jaxrs.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by RazieL on 01.06.2016.
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessage(@QueryParam(value = "year") int year,
                                    @QueryParam(value = "start") int start,
                                    @QueryParam(value = "size") int size) {
        if (year > 0) {
            return messageService.getAllMessagesForYear(year);
        }
        if (start > 0 && size > 0) {
            return messageService.getAlMessagesPaginated(start, size);
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMesage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message removeMessage(@PathParam("messageId") long messageId) {
        return messageService.removeMessage(messageId);
    }

}
