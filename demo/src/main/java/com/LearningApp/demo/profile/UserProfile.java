package com.LearningApp.demo.profile;

import java.util.Optional;
import java.util.UUID;

public class UserProfile {

private UUID userId; 
private String username;
private String userImageLink;

public UserProfile(UUID userId, String username, String userImageLink) {
    this.userId = userId;
    this.username = username;
    this.userImageLink = userImageLink;
}

public UUID getUserId() {
    return userId;
}
public void setUserId(UUID userId) {
    this.userId = userId;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public Optional<String> getUserImageLink() {

    return Optional.ofNullable(userImageLink);

}
public void setUserImageLink(String userImageLink) {
    this.userImageLink = userImageLink;
}
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((userImageLink == null) ? 0 : userImageLink.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
}
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserProfile other = (UserProfile) obj;
    if (userId == null) {
        if (other.userId != null)
            return false;
    } else if (!userId.equals(other.userId))
        return false;
    if (userImageLink == null) {
        if (other.userImageLink != null)
            return false;
    } else if (!userImageLink.equals(other.userImageLink))
        return false;
    if (username == null) {
        if (other.username != null)
            return false;
    } else if (!username.equals(other.username))
        return false;
    return true;
} 


}
