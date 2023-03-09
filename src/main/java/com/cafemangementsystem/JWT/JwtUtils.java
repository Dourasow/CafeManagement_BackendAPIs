//package com.cafemangementsystem.JWT;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtUtils {
//
//    //Creating my secret key
//    private String secretKey = "Abdurahmanesowbambewelasoftwareengineeratuniversity";
//
//    //Method to extract userName
//    public String extractUsername(String token)
//    {
//        return extractClaims(token, Claims::getSubject);
//    }
//
//    //Method to extract expiration time
//    public Date extractExpiration(String token)
//    {
//        return extractClaims(token, Claims::getExpiration);
//    }
//
//    //Methods to extract Claims
//    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver)
//    {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Claims extractAllClaims(String token)
//    {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//      //  return Jwts.parserBuilder().
//    }
//
//    //Method to check if token is expired
//    private Boolean isTokenExpired(String token)
//    {
//        return extractExpiration(token).before(new Date());
//    }
//
//    //Method to generate Token
//    public String generateToken(String userName, String role)
//    {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", role);
//       // claims.put("userName", userName);
//        return createToken(claims, userName);
//    }
//
//    //Method to create the token
//    private String createToken(Map<String, Object> claims, String subject)
//    {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
//                //.signWith(SignatureAlgorithm.HS256, secretKey).compact();
//    }
//
//    //Method to Validate the token
//    public Boolean validToken(String token, UserDetails userDetails)
//    {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
