package main;

import java.util.Map;
import java.util.TreeMap;

public class ABB<T> {

    private TreeMap<String, T> data;

    public ABB(){
        this.data = new TreeMap<>();
    }

    /**
     * Procura e retorna o objeto
     * @param key chave para pesquisa
     * @return objeto dentro da arvore
     */
    public T find(String key){
        return this.data.get(key);
    }

    /**
     * Método que insere o objeto na arvore
     * @param key objeto a se
     * @param newElement
     * @return True se adicionado corretamente, false caso ja exista
     */
    public boolean add(String key, T newElement){
        boolean result = false;
        if(!this.data.containsKey(key)){
            this.data.put(key, newElement);
            result = true;
        }
        return result;
    }

    /**
     * apaga o objeto da arvore
     * @param key objeto a ser apagado
     * @return  True se retirado corretamente, false caso ja exista
     */
    public boolean del(String key) {
        boolean result = false;

        if(this.data.containsKey(key)) {
            this.data.remove(key);
            result = true;
        }

        return result;
    }

    /**
     * retorna a quantidade de vertices da arvore
     * @return
     */
    public int size(){
        return this.data.size();
    }

    public T[] allElements(T[] array){
        T[] allData = this.data.values().toArray(array);
        return allData;
    }

    /**
     * retorna todas as chaves primarias da arvore
     * @return
     */
    public String[] allKeys() {
        String[] keys = new String[this.data.size()];
        int i = 0;

        for(Map.Entry<String, T> entryData : this.data.entrySet()) {
            keys[i] = entryData.getKey();
            i++;
        }

        return keys;
    }

    public TreeMap<String, T> clone() {
        TreeMap<String, T> cloneABB = new TreeMap<>();

        cloneABB.putAll(this.data);

        return cloneABB;
    }

    public void addAll(TreeMap<String, T> value){
        this.data.putAll(value);
    }
}
