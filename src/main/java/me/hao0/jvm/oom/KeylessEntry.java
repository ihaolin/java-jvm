package me.hao0.jvm.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haolin
 * @date 6/14/16
 * @mailto haolin.h0@gmail.com
 */
public class KeylessEntry {

    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args){
        Map<Key, String> m = new HashMap<>();
        while (true){
            for (int i=0; i<10000; i++){
                if (!m.containsKey(new Key(i))){
                    m.put(new Key(i), "Number:" + i);
                }
            }
        }
    }
}
