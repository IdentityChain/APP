package com.project.isc.iscdbserver.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5TokenGenerator implements TokenGenerator {
    @Override
    public String generate(String... strings) {
        Long timestamp = System.currentTimeMillis();
        String  tokenMeta = "";
        for (String s : strings){
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
