/**
 * 
 */
package hu.infokristaly.homework.auth.back.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_join_group")
public class UserJoinGroup {

    @Id
    private UserJoinGroupId userJoinGroupId;

    public UserJoinGroupId getUserJoinGroupId() {
        return userJoinGroupId;
    }

    public void setUserJoinGroupId(UserJoinGroupId userJoinGroup) {
        this.userJoinGroupId = userJoinGroup;
    }
    
}
