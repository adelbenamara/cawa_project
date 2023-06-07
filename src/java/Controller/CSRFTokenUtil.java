/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author adel
 */
import java.security.SecureRandom;
import java.util.Base64;

public class CSRFTokenUtil {
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[32]; // Adjust the token length as needed
        random.nextBytes(tokenBytes);

        // Encode the random bytes to a secure string representation
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

        return token;
    }
}
