package pl.raziel.jaxrs.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import pl.raziel.jaxrs.model.Message;
import pl.raziel.jaxrs.resources.beans.MessageFilterBean;
import pl.raziel.jaxrs.service.MessageService;

/**
 * Created by RazieL on 01.06.2016.
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAlMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");

		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResources")
				.path(CommentResource.class)
				.resolveTemplate("messageId", Long.toString(message.getId()))
				.build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() // http://localhost:8080/messenger/webapi/
				.path(MessageResource.class) // messages
				.path(Long.toString(message.getId())) // /{messageId}
				.build().toString();
		return uri;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);

		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

		return Response.created(uri).entity(newMessage).build();
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

	@Path("/{messageId}/comments")
	public CommentResource getCommentResources() {
		return new CommentResource();
	}

}
