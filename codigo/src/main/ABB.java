package main;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ABB<T> {

    private TreeMap<String, T> data;

    public ABB(){
        this.data = new TreeMap<>();
    }

    public T find(String key){
        return this.data.get(key);
    }

    public boolean add(String key, T newElement){
        boolean result = false;
        if(!this.data.containsKey(key)){
            this.data.put(key, newElement);
            result = true;
        }
        return result;
    }

    public boolean del(String key) {
        boolean result = false;

        if(this.data.containsKey(key)) {
            this.data.remove(key);
            result = true;
        }

        return result;
    }
    
    public int size(){
        return this.data.size();
    }

    public T[] allElements(T[] array){
        T[] allData = this.data.values().toArray(array);
        return allData;
    }

    public String[] allKeys() {
        String[] keys = new String[this.data.size()];
        int i = 0;

        for(Map.Entry<String, T> entryData : this.data.entrySet()) {
            keys[i] = entryData.getKey();
            i++;
        }

        return keys;
    }
}
