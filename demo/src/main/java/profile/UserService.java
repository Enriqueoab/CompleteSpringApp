package profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    
private final UserDataAccessService userDataAccessService;

@Autowired
public UserService(UserDataAccessService userDataAccessService) {
    this.userDataAccessService = userDataAccessService;
}

    List<UserProfile> getUserProfiles(){
        return userDataAccessService.getUserProfiles();
    
}
}
