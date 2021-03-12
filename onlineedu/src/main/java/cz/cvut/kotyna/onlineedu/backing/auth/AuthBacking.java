package cz.cvut.kotyna.onlineedu.backing.auth;

import cz.cvut.kotyna.onlineedu.backing.ActiveUser;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static org.omnifaces.util.Faces.*;
import static org.omnifaces.util.Messages.addFlashGlobalWarn;
import static org.omnifaces.util.Messages.addGlobalError;

public abstract class AuthBacking {

	protected UserAccount user;
	protected @NotNull String password;
	protected boolean rememberMe;

	@Inject
	protected UserService userService;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private ActiveUser activeUser;

	@PostConstruct
	public void init() {
		if (activeUser.isPresent()) {
			addFlashGlobalWarn("auth.message.warn.already_logged_in");
			redirect("student/home");
		}
		else {
			user = new UserAccount();
		}
	}

	protected void authenticate(AuthenticationParameters parameters) throws IOException {
		AuthenticationStatus status = securityContext.authenticate(getRequest(), getResponse(), parameters);

		if (status == SEND_FAILURE) {
			addGlobalError("auth.message.error.failure");
			validationFailed();
		}
		else if (status == SEND_CONTINUE) {
			responseComplete(); // Prevent JSF from rendering a response so authentication mechanism can continue.
        }
		else if (activeUser.get().getRole().equals("admin")) {
			redirect("admin/classrooms");
		}
		else if (activeUser.get().getRole().equals("student")) {
			redirect("student/home");
		}
		else {
			redirect("");
		}
	}

	public UserAccount getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}