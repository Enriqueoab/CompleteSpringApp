package profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import datastore.TestUserProfie;

@Repository
public class UserDataAccessService {
    private final TestUserProfie userProfie;

    @Autowired
    public UserDataAccessService(TestUserProfie userProfie) {
        this.userProfie = userProfie;
    }

    
    List<UserProfile> getUserProfiles(){
        return userProfie.getUserProfiles();

    }
}
