package cz.cvut.kotyna.onlineedu.service;

import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AuthService {

    public static String encodeSHA256(String password, String salt)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return Base64.getEncoder().encodeToString(digest);
    }

    public static void main(String[] args) {
        try {
            System.out.println("SHA256 for 'student'/'scIYclz' = '" + encodeSHA256("student", "") + "'");
            System.out.println("SHA256 for 'admin'/'cYxkby9' = '" + encodeSHA256("admin", "") + "'");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
