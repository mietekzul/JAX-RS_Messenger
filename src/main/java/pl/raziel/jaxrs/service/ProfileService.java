package pl.raziel.jaxrs.service;

import pl.raziel.jaxrs.database.DatabaseClass;
import pl.raziel.jaxrs.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RazieL on 01.06.2016.
 */
public class ProfileService {
    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("RazieL", new Profile(1, "RazieL", "Daniel", "Ostapiuk"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }
}
