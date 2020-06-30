package cn.codexing.blog.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description: token相关的工具类
 */
@Component
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = -4324967L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Description: 解析token，从token中获取信息
     *
     * @param token
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

    /**
     * Description:获取用户名
     *
     * @param token
     */
    public String getUserNameFromToken(String token) {
        String username = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (ExpiredJwtException e) {
            username = e.getClaims().getSubject();
        }
        return username;
    }

    /**
     * Description:从token中获取角色
     *
     * @param token
     */
    public String getRolesFromToken(String token) {
        String roles;
        try {
            final Claims claims = getClaimsFromToken(token);
            roles = (String) claims.get(CLAIM_KEY_ROLES);
        } catch (Exception e) {
            roles = null;
        }
        return roles;
    }

    /**
     * Description:获取token创建时间
     *
     * @param token
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * Description: 获取token过期时间
     *
     * @param token
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * Description:token生成过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * Description:token是否过期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Description:token创建时间与密码最后修改时间比较，小于返回true，大于返回false
     *
     * @param created
     * @param lastPasswordReset
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * Description: 创建token
     *
     * @param userDetails
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        return generateToken(claims);
    }

    /**
     * Description:使用Rs256签名
     *
     * @param claims
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Description:是否刷新token
     *
     * @param token
     * @param lastPasswordReset
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    /**
     * Description:刷新token
     *
     * @param token
     */
    public String refreshToken(String token) {
        String refreshToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshToken = generateToken(claims);
        } catch (Exception e) {
            refreshToken = null;
        }
        return refreshToken;
    }

    /**
     * Description:验证token
     *
     * @param token
     * @param token
     */
    public boolean validateToken(String token) {
        final String username = getUserNameFromToken(token);
        if (redisTemplate.hasKey(username + "codexing") && !isTokenExpired(token)) {
            /*如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间*/
            redisTemplate.boundValueOps(username + "subjectrace").expire(this.expiration, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }
}