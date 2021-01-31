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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtTokenUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * secret for jwt
     */
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    /**
     * expire time
     */
    @Value("${jwt.expire}")
    private long JWT_EXPIRE_DURATION;

    /**
     * retrieve username from jwt token
     * store the username in "sub"
     * @param token
     * @return String username
     */
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    /**
     * retrieve expiration date from jwt token
     * @param token
     * @return Date expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * get claims from token
     * @param token
     * @param claimsResolver Function interface, take claims and return type T based on the function
     * @param <T>
     * @return T depends on claimsResolver
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * retrieve claims from token
     * @param token
     * @return Claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * check if the token has expired
     * @param token
     * @return true expired false not expired
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * generate token for user
     * @param userDetails
     * @return String token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * generate token
     * 1. define claims of the token
     *      * claims(custom payload): empty hashMap
     *      * sub(subject): recommend to use username
     *      * iat(issued at): currentTimeMillis
     *      * exp(expire at): currentTimeMillis + JWT_EXPIRE_DURATION * 1000
     * 2. sign the JWT using the HS512 algorithm and JWT_SECRET
     * 3. according to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     *    compaction of the JWT to a URL-safe string
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRE_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    /**
     * validate token
     * @param token
     * @param userDetails
     * @return true is valid, false isn't valid
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * generate jwt token
     *
     * @param loginUsername username of login user
     * @return token with the structure as follow: header.subject.signature
     */
    @Deprecated
    public String generateToken(String loginUsername) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + JWT_EXPIRE_DURATION * 1000);

        // create payload to record the user info
        Map<String, Object> claims = new HashMap<>();
        claims.put("username",loginUsername);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact(); // JWS Compact Serialization reference: https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1
    }

    /**
     * get claims from the token
     * @param token jwt token
     * @return payload that extracted from token
     */
    @Deprecated
    public Claims getClaimByToken(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[1];
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET)
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
