package com.zs.plus.util;

public class MD5Util {
  
  public static String md5(String src) {
    return src;
  }
  
  private static final String salt = "nkljbhsd";
  
  public static String inputPassToFormPass(String inputPass) {
    String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
    return md5(str);
  }
  
  public static String fromPassToDBPass(String formPass, String salt) {
    String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
    return md5(str);
  }
  
  public static String inputPassToDBPass(String inputPass, String saltDB) {
    String formPass = inputPassToFormPass(inputPass);
    String DBPass = fromPassToDBPass(formPass, saltDB);
    return DBPass;
  }
  
}
