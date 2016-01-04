/**
 * 
 */
package hu.infokristaly.homework.auth.back.service;

import hu.infokristaly.homework.auth.back.model.SystemUser;
import hu.infokristaly.homework.auth.back.model.UserJoinGroup;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * @author pzoli
 *
 */
@Named
@Stateless
public class UserService implements Serializable {

    private static final long serialVersionUID = -2360286862757860424L;

    @Inject
    private EntityManager em;
    
    public static String USER_GROUP = "ROLE_USER";
    public static String ADMIN_GROUP = "ROLE_ADMIN";
    
    public void createUser(SystemUser user) {
        em.persist(user);
    }

    public void createRole(UserJoinGroup userJoinGroup) {
        em.persist(userJoinGroup);
    }

}
