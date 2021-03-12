package cz.cvut.kotyna.onlineedu.service.auth;

import cz.cvut.kotyna.onlineedu.entity.LoginToken;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.Instant;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import static org.omnifaces.utils.security.MessageDigests.digest;

@Stateless
public class LoginTokenService {

	private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-256";

	// connection to the database
	@PersistenceContext
	EntityManager em;

	@Inject
	private UserService userService;

	public String generate(String email, String ipAddress, String description) {
		Instant expiration = now().plus(14, DAYS);
		return generate(email, ipAddress, description, expiration);
	}

	public String generate(String email, String ipAddress, String description, Instant expiration) {
		String rawToken = randomUUID().toString();
		UserAccount user = userService.findUserAccountByUsername(email);

		if (user == null) {
			throw new NoResultException();
		}

		LoginToken loginToken = new LoginToken();
		loginToken.setTokenHash(digest(rawToken, MESSAGE_DIGEST_ALGORITHM));
		loginToken.setExpiration(expiration);
		loginToken.setDescription(description);
		loginToken.setIpAddress(ipAddress);
		loginToken.setUserAccount(user);
		user.getLoginTokens().add(loginToken);
		return rawToken;
	}

	public void remove(String loginToken) {
		em.createNamedQuery("LoginToken.remove", LoginToken.class).setParameter("tokenHash", loginToken);
	}

	//public void removeExpired() {
	//	createNamedQuery("LoginToken.removeExpired")
	//		.executeUpdate();
	//}

}