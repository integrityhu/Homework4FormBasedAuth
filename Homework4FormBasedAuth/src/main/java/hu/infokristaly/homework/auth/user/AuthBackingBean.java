package hu.infokristaly.homework.auth.user;

import hu.infokristaly.homework.auth.back.model.SystemUser;
import hu.infokristaly.homework.auth.back.model.UserJoinGroup;
import hu.infokristaly.homework.auth.back.model.UserJoinGroupId;
import hu.infokristaly.homework.auth.back.service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.util.Base64;

@Named
@RequestScoped
public class AuthBackingBean {

	private String password;
	private String password2;
	private String emailAddress;
	private String userName;
	
	@Inject
	private UserService userService;
	
    public void logout() {
        String result = "/index.xhtml";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if (request.getUserPrincipal() != null) {
            try {
            	request.logout();
                HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                session.invalidate();
                String url = context.getApplication().getViewHandler().getActionURL(context, result);
                context.getExternalContext().redirect(url);
            } catch (ServletException e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", "Exception occurred at logout. "+e.getLocalizedMessage()));
            } catch (IOException ex) {
            }
        }

    }
    
    public static byte[] toSHA256(byte[] convertme) {
        byte[] result = {};
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            result = md.digest(convertme);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void create() {
    	System.out.println("UserName:"+userName+"; email:"+emailAddress+"; password:"+password+"; password2:"+password2);
    	String SHA256pass = "";
    	try {
    		SHA256pass = Base64.encodeBytes(toSHA256(password.getBytes("UTF-8")));
    		SystemUser user = new SystemUser();
    		user.setEmailAddress(emailAddress);
    		user.setEnabled(true);
    		user.setUsername(userName);
    		user.setUserpassword(SHA256pass);
    		userService.createUser(user);
    		UserJoinGroupId userJoinGroupId = new UserJoinGroupId();
    		userJoinGroupId.setGroupName(UserService.USER_GROUP);
    		userJoinGroupId.setUserName(emailAddress);
    		UserJoinGroup userJoinGroup = new UserJoinGroup();
    		userJoinGroup.setUserJoinGroupId(userJoinGroupId);
    		userService.createRole(userJoinGroup);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	System.out.println("SHA-256/Base64:"+SHA256pass);
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
