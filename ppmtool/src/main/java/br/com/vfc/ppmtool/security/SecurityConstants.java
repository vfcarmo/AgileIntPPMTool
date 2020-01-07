package br.com.vfc.ppmtool.security;

public interface SecurityConstants {

    String SIGN_UP_URLS = "/api/users/**";

    String H2_URL = "/h2-console/**";

    String SECRECT = "SecrectKeyToGenJWTs";

    String TOKEN_PREFIX = "Bearer ";

    String TOKEN_HEADER = "Authorization";

    long EXPRIRATION_TIME = 300_000;
}
