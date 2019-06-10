package token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class Token {
    private static final int EXPIRES_IN_ONE_HOUR = 3_600_000;
    private static final String APP_NAME = "Auction";
    private static final String SECRET = "Secret";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


    public static String generateTokenString(Integer buyerId) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject("" + buyerId)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }


    private static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    private static Date generateCurrentDate() {
        return new Date(getCurrentTimeMillis() * 1000);
    }

    private static Date generateExpirationDate() {
        return new Date((getCurrentTimeMillis() + EXPIRES_IN_ONE_HOUR) * 1000);
    }

}
