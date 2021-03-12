package cz.cvut.kotyna.onlineedu.auth;


import cz.cvut.kotyna.onlineedu.backing.ActiveUser;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;

import javax.security.enterprise.CallerPrincipal;

/**
 * @see CustomRememberMeIdentityStore
 * @see ActiveUser
 */
public class CustomCallerPrincipal extends CallerPrincipal {

	private final UserAccount user;

	public CustomCallerPrincipal(UserAccount user) {
		super(user.getUsername());
		this.user = user;
	}

	public UserAccount getUser() {
		return user;
	}

}