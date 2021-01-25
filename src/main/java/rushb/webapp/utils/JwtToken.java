/*
JSON Web Token (JWT) is a compact, URL-safe means of representing claims to be transferred between two parties.
The claims in a JWT are encoded as a JSON object that is used as the payload of a JSON Web Signature (JWS) structure
or as the plaintext of a JSON Web Encryption (JWE) structure, enabling the claims to be digitally signed or integrity
protected with a Message Authentication Code (MAC) and/or encrypted.

Reference: https://tools.ietf.org/html/rfc7519
*/

package rushb.webapp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtToken {
    private static Logger logger = LoggerFactory.getLogger(JwtToken.class);

    /**
     * secret for jwt
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * expire time
     */
    @Value("${jwt.expire}")
    private long expire;

    /**
     * generate jwt token
     *
     * @param loginUsername username of login user
     * @return token with the structure as follow: header.subject.signature
     */
    public String generateToken(String loginUsername) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        // create payload to record the user info
        Map<String, Object> claims = new HashMap<>();
        claims.put("username",loginUsername);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact(); // JWS Compact Serialization reference: https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1
    }

    /**
     * get claims from the token
     * @param token jwt token
     * @return payload that extracted from token
     */
    public Claims getClaimByToken(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[1];
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * if the token is expired
     * @param expiration
     * @return true: expired    false: not expired
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
