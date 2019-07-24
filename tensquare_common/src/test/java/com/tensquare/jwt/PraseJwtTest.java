package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class PraseJwtTest {

    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("choi")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NjM3ODA2MjcsImV4cCI6MTU2Mzc4MDY4N30.xsvUglMQJQfr2K0p7u6_oUC9bo9An1lGrs-4yLbxWfk\n")
                .getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
    }
}
