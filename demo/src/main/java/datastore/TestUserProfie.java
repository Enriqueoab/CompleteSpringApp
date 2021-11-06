package datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import profile.UserProfile;

@Repository
public class TestUserProfie {

private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

static{
    USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"enriquebarca",null));
    USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"oscarlinares",null));
    
}

public List<UserProfile> getUserProfiles() {
    return USER_PROFILES;
}


}
