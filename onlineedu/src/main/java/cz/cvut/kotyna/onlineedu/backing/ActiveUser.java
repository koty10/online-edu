package cz.cvut.kotyna.onlineedu.backing;

import cz.cvut.kotyna.onlineedu.auth.CustomCallerPrincipal;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
@SessionScoped
public class ActiveUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Boolean> is = new ConcurrentHashMap<>();
	private Map<String, Boolean> can = new ConcurrentHashMap<>();
	private Map<String, Boolean> canView = new ConcurrentHashMap<>();

	private UserAccount activeUser;

	@Inject
	private SecurityContext securityContext;

	public UserAccount get() { // For use in backing beans.
		if (activeUser == null) {
			activeUser = securityContext
				.getPrincipalsByType(CustomCallerPrincipal.class).stream()
				.map(CustomCallerPrincipal::getUser)
				.findAny().orElse(null);
		}

		return activeUser;
	}

	public boolean isPresent() { // For use in both backing beans and EL #{activeUser.present}
		return get() != null;
	}

	public Integer getId() { // For use in both backing beans and EL #{activeUser.id}
		return isPresent() ? activeUser.getId() : null;
	}

	@Override
	public String toString() { // Must print friendly name in EL #{activeUser}.
		return isPresent() ? activeUser.getFirstname() + " " + activeUser.getSurname() : null;
	}

}