package cz.cvut.kotyna.onlineedu.backing.auth;

import org.omnifaces.cdi.Param;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import java.io.IOException;

import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class LoginBacking extends AuthBacking {

	@Inject @Param(name = "continue") // Defined in @LoginToContinue of KickoffFormAuthenticationMechanism.
	private boolean loginToContinue;

	public void login() throws IOException {
		authenticate(withParams()
			.credential(new UsernamePasswordCredential(user.getUsername(), password))
			.newAuthentication(!loginToContinue)
			.rememberMe(rememberMe));
	}

}