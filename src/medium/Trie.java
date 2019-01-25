package medium;

import java.util.*;

public class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = TrieNode.sentinel;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.trim().length() == 0) {
            return;
        }

        TrieNode cursor =  root;
        int len = word.length();
        for (int i = 0; i < word.length(); i++) {
            Character tmp = word.charAt(i);
            TrieNode child = cursor.getChild(tmp);
            if (child == null) {
                child = new TrieNode();
                child.setKey(tmp);
                cursor.addChild(child);
            }
            cursor = child;
        }
        cursor.addChild(TrieNode.sentinel);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNode cursor = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            Character temp = word.charAt(i);
            TrieNode child = cursor.getChild(temp);
            if (child == null) {
                return false;
            }
            cursor = child;
        }

        if (!cursor.containChild(TrieNode.sentinel)) {
            return false;
        }

        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        TrieNode cursor = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            Character temp = prefix.charAt(i);
            TrieNode child = cursor.getChild(temp);
            if (child == null) {
                return false;
            }
            cursor = child;
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.startsWith("a"));
        //trie.insert("apple");
        //System.out.println(trie.search("apple"));
        //System.out.println(trie.search("app"));
        //System.out.println(trie.startsWith("apple"));
        //trie.insert("apple");

        //trie.insert("ab");
        //System.out.println(trie.search("a"));
        //System.out.println(trie.startsWith("a"));
    }
}
