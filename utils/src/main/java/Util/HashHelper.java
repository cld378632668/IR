package Util;

public class HashHelper {

    /**
     * <<字符串哈希到整数函数算法>>. https://blog.csdn.net/hzhsan/article/details/25552153
     */

    /**
     *根据 <<字符串哈希到整数函数算法>>. https://blog.csdn.net/hzhsan/article/details/25552153
     * 中的同名C函数翻译为Java。
     *
     * @param in
     * @return
     */
    public static int SDBMHash(String in){
        int hash = 0;

        for (int i = 0; i< in.length();i++){
            hash = in.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return (hash & 0x7FFFFFFF);
    }

    public static void main(String[] args) {
        System.out.println(SDBMHash("<http://knowledge.microsoft.com/00000000-0000-0000-attr-000000000016>"));
        System.out.println(SDBMHash("<http://knowledge.microsoft.com/70c3b47b-d9c1-bcb1-a2cf-730fe5e13aaf> "));
    }
}
