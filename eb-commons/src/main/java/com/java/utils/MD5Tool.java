package com.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {
    private static final String[] digital={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    private static String MD5Strs(String txt) throws Exception {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        if(txt==null){
            throw new Exception("明文为空");
        }else{
            byte[] bytes=md5.digest(txt.getBytes("UTF-8"));
            StringBuilder enCodeStr= new StringBuilder();
            for(byte temp: bytes){
                int num=temp;
                if(num < 0){
                    num+=256;
                }
                int index1=num/16;
                int index2=num%16;
                enCodeStr.append(digital[index1]).append(digital[index2]);
            }
            return enCodeStr.toString();
        }
    }

    public static String MD5(String txt) throws Exception {
        return MD5Strs(MD5Strs(MD5Strs(MD5Strs(txt)+"dingP")+"dingP")+"dingP");
    }
    public static void main(String[] args) throws Exception {
        String str = "123456";
        System.out.println(MD5Tool.MD5(str));
    }
}
