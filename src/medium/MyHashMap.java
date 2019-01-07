package medium;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName MyHashMap.java
 * @Description TODO
 * @createTime 2019年01月02日 15:50:00
 */
class MyHashMap {
    private int[] map;
    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new int[1000000];

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        map[key] = value + 1;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (map.length <= key) {
            return -1;
        }

        return map[key] - 1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if (key >= map.length) {
            return;
        }
        map[key] = 0;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.remove(14);
        System.out.println(map.get(4));
        map.put(7, 3);
        map.put(11, 1);
        map.put(12, 1);
        System.out.println(map.get(7));
        map.put(1, 19);
        map.put(0, 3);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));
    }
}

