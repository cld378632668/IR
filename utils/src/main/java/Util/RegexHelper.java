package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

    /**
     *
     * @param sourceString
     * @param regexString
     * @return  第一个匹配出的字符串
     */
    public static String getString(String regexString,String sourceString){
        Pattern p =  Pattern.compile(regexString);
        Matcher m = p.matcher(sourceString);
        if (m.find())
            return m.group(0);
        return null;

    }

    /**
     *
     * @param content
     * @param regexString
     * @return  ArrayList<String> 匹配出的文本列表
     */
    public static List<String> getMatchList(String content, String regexString) {
        List match_list = new ArrayList<String>();
        Pattern p = Pattern.compile(regexString);
        Matcher m = p.matcher(content);
        while(m.find()){
            match_list.add(m.group());
        }
        return match_list;
    }

    /**
     * Leetcode 468 验证IP地址
     * 完全通过。
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        if(IP.matches("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])"))return "IPv4";
        if(IP.matches("(([0-9a-fA-F]{1,4}):){7}([0-9a-fA-F]{1,4})"))return "IPv6";
        return "Neither";
    }



    /**
     *  Matcher m = p.matcher(inString);
     *            if (m.matches())
     *  如果整个sourceString和RegexString匹配，matches()返回true.
     *
     *
     * Leetcode 468 验证IP地址
     *
     * @param inString
     * @return "IPv4"\"IPv6" or "Neither"
     *
     * 以下测试用例均判断正确：
     * 172.16.254.01
     *
     * See my youdao note:
     *
     *
     */
    public static String iPvalidate(String inString){

        if(inString.contains(".")) {
            String ipv4ValiReg = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[1-9])\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[0-9])\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[0-9])\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[0-9])" ;
                    Pattern p = Pattern.compile(ipv4ValiReg);
            Matcher m = p.matcher(inString);
            if (m.matches())
                return "IPv4";
            else
                return "Neither";
        }


        if(inString.contains(":")) {
            String ipv6ValiReg = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
            //至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 这个正则认为是效的 IPv6 地址。
            Pattern p = Pattern.compile(ipv6ValiReg);
            Matcher m = p.matcher(inString);
            if (m.matches())
                return "IPv6";
            else
                return "Neither";
        }

        return "Neither";
    }


    public static void main(String[] args) {

        System.out.println(RegexHelper.iPvalidate("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    }
}
