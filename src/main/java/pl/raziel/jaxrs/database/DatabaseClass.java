package pl.raziel.jaxrs.database;

import pl.raziel.jaxrs.model.Message;
import pl.raziel.jaxrs.model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RazieL on 01.06.2016.
 */
public class DatabaseClass {
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
