package com.team2.findmytown.security;




import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.service.UserDetailsImpl;
import com.team2.findmytown.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {


    private final UserDetailsServiceImpl userDetailsService;
    private static final String SECRET_KEY = "NMsasA8JPctFuna59f5";

    public TokenProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String create(UserEntity userEntity) { //JWT 라이브러리를 이용해 JWT 토큰 생성 (임의로 지정한 시크릿키 사용)
        Date expiryDate = Date.from(
                Instant.now().plus(20, ChronoUnit.DAYS));



        	/*
		{ // header
		  "alg":"HS512"
		}.
		{ // payload
		  "sub":"40288093784915d201784916a40c0001",
		  "iss": "demo app",
		  "iat":1595733657,
		  "exp":1596597657
		}.
		// SECRET_KEY를 이용해 서명한 부분
		Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */
        // JWT Token 생성
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) //header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                //payload에 들어갈 내용
                .setSubject(userEntity.getId()) //sub
                .setIssuer("mytown app") //iss
                .setIssuedAt(new Date()) //iat
                .claim("email",userEntity.getEmail())
                .setExpiration(expiryDate) //exp

                .compact();
    }


        public String validateAndGetUserId(String token){
            //parseClaimsJws 메서드가 Base 64 디코딩 및 파싱,
            // 즉 헤더와 헤이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명 후 token의 서명과 비교
            //위조되지 않았다면 페이로드 (Claims) 리턴, 위조라면 예외를 날림
            //그 중 우리는 userId가 필요하므로 getBody를 부른다
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

        return claims.getSubject().toString();

    }

    public String validateAndGetEmail(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("email").toString();

    }

    public Authentication getAuthentication(String token) {
        String email = validateAndGetEmail(token);
        String id = validateAndGetUserId(token);
        UserDetailsImpl userDetailsImpl = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(id, "", userDetailsImpl.getAuthorities());
    }


}
