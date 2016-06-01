package pl.raziel.jaxrs.resources;

import pl.raziel.jaxrs.model.Profile;
import pl.raziel.jaxrs.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by RazieL on 01.06.2016.
 */
@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);

    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public Profile removeProfile(@PathParam("profileName") String profileName) {
        return profileService.removeProfile(profileName);
    }

}
