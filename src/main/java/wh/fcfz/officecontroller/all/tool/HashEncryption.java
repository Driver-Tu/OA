package wh.fcfz.officecontroller.all.tool;

import cn.hutool.crypto.SecureUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/*
* 配置MD5加密
* */
public class HashEncryption {
    public static String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        StringBuilder sb=new StringBuilder();
        for(byte b:result){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
}
