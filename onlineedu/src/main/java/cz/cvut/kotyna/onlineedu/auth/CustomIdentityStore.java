package cz.cvut.kotyna.onlineedu.auth;

import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Supplier;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

	@Inject
	private UserService userService;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		Supplier<UserAccount> userSupplier = null;

		if (credential instanceof UsernamePasswordCredential) {
			String username = ((UsernamePasswordCredential) credential).getCaller();
			String password = ((UsernamePasswordCredential) credential).getPasswordAsString();
			userSupplier = () -> userService.getByEmailAndPassword(username, password);
		} else if (credential instanceof CallerOnlyCredential) {
			String username = ((CallerOnlyCredential) credential).getCaller();
			userSupplier = () -> userService.findUserAccountByUsername(username);
		}

		return validate(userSupplier);
	}

	static CredentialValidationResult validate(Supplier<UserAccount> userSupplier) {
		if (userSupplier == null) {
			return NOT_VALIDATED_RESULT;
		}

		UserAccount user = userSupplier.get();

		return new CredentialValidationResult(new CustomCallerPrincipal(user), new HashSet<>(Collections.singleton(user.getRole())));
	}

}