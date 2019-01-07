package toutiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2019年01月03日 17:32:00
 */
public class StrSolution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }

        int res = 0;
        int start = 0;

        int[] charMap = new int[128];
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            int charIdx = c - '\0';
            if (charMap[charIdx] > 0) {
                res = Math.max(i - start, res);
                start = charMap[charIdx] > start ? charMap[charIdx] : start;
            }

            charMap[charIdx] = i + 1;
        }

        if (start < s.length()) {
            res = Math.max(res, s.length() - start);
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] prefix = strs[0].toCharArray();
        int currentLen = prefix.length;
        for (int i = 1; i < strs.length; i++) {
            char[] tmp = strs[i].toCharArray();
            currentLen = Math.min(tmp.length, currentLen);
            for (int j = 0; j < currentLen; j++) {
                if (tmp[j] != prefix[j]) {
                    currentLen = j;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentLen; i++) {
            sb.append(prefix[i]);
        }

        return sb.toString();
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }

        int l1 = s1.length();
        int l2 = s2.length();
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c: s1.toCharArray()) {
            c1[c - 'a'] ++;
        }

        for (int i = 0; i < l2; i++) {
            if (i >= l1) {
                --c2[s2.charAt(i - l1) - 'a'];
            }

            c2[s2.charAt(i) - 'a'] ++;
            if (Arrays.equals(c1, c2)) {
                return true;
            }
        }

        return false;
    }

    public String multiply(String num1, String num2) {
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        int[] d = new int[n1.length()+n2.length()];		// 构建数组存放乘积
        for(int i=0; i<n1.length(); i++){
            for(int j=0; j<n2.length(); j++){
                d[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');		// 在正确位置累加乘积
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<d.length; i++){
            int digit = d[i]%10;		// 当前位
            int carry = d[i]/10;		// 进位
            if(i+1<d.length){
                d[i+1] += carry;
            }
            sb.insert(0, digit);		// prepend
        }

        // 移除前导零
        while(sb.charAt(0)=='0' && sb.length()>1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public String reverseWords1(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (c == ' ') {
                while (i < s.length() && s.charAt(i) == ' ') {
                    i++;
                }
                i --;
            }
        }

        StringBuilder res = new StringBuilder();
        String[] tmp = sb.toString().split(" ");
        int i = 0, j = tmp.length - 1;
        while (i < j) {
            String a = tmp[i];
            tmp[i] = tmp[j];
            tmp[j] = a;
            i++;
            j--;
        }

        for (i = 0; i < tmp.length; i++) {
            res.append(tmp[i]);
            if (i != tmp.length - 1) {
                res.append(" ");
            }
        }

        return res.toString();
    }

    public String reverseWords2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        String[] tmp = s.toString().split(" +");
        int i = 0, j = tmp.length - 1;
        while (i < j) {
            String a = tmp[i];
            tmp[i] = tmp[j];
            tmp[j] = a;
            i++;
            j--;
        }

        for (i = 0; i < tmp.length; i++) {
            res.append(tmp[i]);
            if (i != tmp.length - 1) {
                res.append(" ");
            }
        }

        return res.toString();
    }

    public String simplifyPath(String path) {
        String[] tmp = path.trim().split("/");
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].equals(".") || tmp[i].equals("")) {
                continue;
            } else if (tmp[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(tmp[i]);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0,"/" + stack.pop());
        }
        return sb.toString();
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        List<String> ip = new ArrayList<String>();

        dfs(ip, s, 0, res);

        return res;
    }

    public void dfs(List<String> ip, String s, int start, List<String> list) {
        if (ip.size() == 4) {
            if (start != s.length()) {
                return;
            }
            String ipStr = "";
            for (int i = 0; i < 4; i++) {
                String strVal = ip.get(i);
                int val = Integer.valueOf(strVal);
                if (val > 255 || (strVal.startsWith("0") && (val != 0 || strVal.length() > 1))) {
                    return;
                }

                ipStr += ip.get(i);
                if (i < 3) {
                    ipStr += ".";
                }
            }
            list.add(ipStr);
        } else {
            StringBuilder current = new StringBuilder();
            for (int i = start; i < s.length(); i++) {
                current.append(s.charAt(i));
                if (current.length() > 3) {
                    return;
                }

                ip.add(current.toString());
                dfs(ip, s, start + current.length(), list);
                ip.remove(ip.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        StrSolution s = new StrSolution();
        String[] strs = {"flower","flow","flight"};

        System.out.println(s.restoreIpAddresses("25525511135"));
        System.out.println(s.restoreIpAddresses("010010"));
    }
}
