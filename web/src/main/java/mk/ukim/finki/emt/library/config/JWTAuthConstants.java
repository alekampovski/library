package mk.ukim.finki.emt.library.config;

public class JWTAuthConstants {
    public static final long EXPIRATION_TIME = 840_000_000;
    public static final String SECRET_KEY = "th1s1sS3cr3tK3y";
    public static final String HEADER_STRING = "Authorisation";
    public static final String TOKEN_PREFIX = "Bearer ";
}
