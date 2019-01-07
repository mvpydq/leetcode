package simple;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Main.java
 * @Description TODO
 * @createTime 2018年10月23日 14:49:00
 */
public class Main {
    public static void main(String[] args) {
        First100 s = new First100();
        s.test();
    }

    public static void getIndex(int offset) {
        int len = 100;
        int max = 200;
        int start = 0;
        int end = 0;
        if (len + offset < max) {
            start = 0;
            end = len + offset;
        } else {
            start = offset - (max - len);
            end = offset + len;
        }

        System.out.println(start + ", " + end);
    }

    public static String int2hex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        return a;
    }

    public static String toHex(int n) {
        String str = "0123456789ABCDEF";
        StringBuilder s = new StringBuilder();
        while (n != 0) {
            s.append(str.charAt(n % 16));

            n = n / 16;
        }

        return s.reverse().toString();
    }

    public static String toHExcel(int n) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder();
        while (n != 0) {
            s.append(str.charAt(n % 26));

            n = n / 26;
        }

        return s.reverse().toString();
    }
}
