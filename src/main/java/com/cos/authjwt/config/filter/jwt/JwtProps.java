package com.cos.authjwt.config.filter.jwt;

interface JwtProps {
	public static final String SUBJECT = "COS토큰";
	public static final String SECRET = "부산it"; // not good!!
	public static final String AUTH = "Bearer "; // 띄어쓰기 잊지마!!
	public static final String HEADER = "Authorization";
	public static final Long EXPIRESAT = 10 * 60 * 60L;
}
