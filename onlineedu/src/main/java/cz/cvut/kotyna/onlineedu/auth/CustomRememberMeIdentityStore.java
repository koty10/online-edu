package cz.cvut.kotyna.onlineedu.auth;

import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.auth.LoginTokenService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static org.omnifaces.util.Servlets.getRemoteAddr;

/*
@ApplicationScoped
public class CustomRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private HttpServletRequest request;

    @Inject
    private UserService userService;

    @Inject
    private LoginTokenService loginTokenService;

    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        UserAccount user = userService.findUserAccountByLoginToken(credential.getToken());
        if (user != null) {
            return new CredentialValidationResult(new CallerPrincipal(user.getEmail()));
        }
        else {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        String ipAddress = getRemoteAddr(request);
        String description = "Remember me session for " + ipAddress + " on " + request.getHeader("User-Agent");
        return loginTokenService.generate(callerPrincipal.getName(), ipAddress, description);
    }

    @Override
    public void removeLoginToken(String loginToken) {
        loginTokenService.remove(loginToken);
    }

}

 */
@ApplicationScoped
public class CustomRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private HttpServletRequest request;

    @Inject
    private UserService userService; // This is your own EJB.

    @Inject
    private LoginTokenService loginTokenService; // This is your own EJB.

    /*
    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        UserAccount user = userService.findUserAccountByLoginToken(credential.getToken());
        if (user != null) {
            return new CredentialValidationResult(new CallerPrincipal(user.getEmail()));
        }
        else {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }
    */

    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        return CustomIdentityStore.validate(() -> userService.findUserAccountByLoginToken(credential.getToken()));
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        String ipAddress = getRemoteAddr(request);
        String description = "Remember me session for " + ipAddress + " on " + request.getHeader("User-Agent");
        return loginTokenService.generate(callerPrincipal.getName(), ipAddress, description);
    }

    @Override
    public void removeLoginToken(String loginToken) {
        loginTokenService.remove(loginToken);
    }

}