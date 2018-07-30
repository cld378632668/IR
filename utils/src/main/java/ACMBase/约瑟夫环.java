package ACMBase;

/**
 * Created by ChenLD on 2018/4/3.
 *
 * @author ChenLD
 * @version 1.0
 *          又名丢手绢问题
 */
public class 约瑟夫环 {

    public static void main(String[] args) {
        int n = 3, m = 2;
        int[] f = new int[n + 1];

        for(int i =2; i<=n; i++)
            f[i] = ( f[i -1] + m + 1) % i;

        System.out.println(f[n]);

    }
}
