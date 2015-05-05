package fr.univtln.madapm.votemanager.metier;

import java.util.*;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CMap<I, O> implements Map<I, O> {

    protected Map<I, O> map = new HashMap<I, O>();

    public CMap() {}

    public CMap(Map<I, O> map) {
        this.map = map;
    }

    /**
     * Implémentation des méthodes Map
     */
    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(java.lang.Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(java.lang.Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public O get(java.lang.Object key) {
        return this.map.get(key);
    }

    @Override
    public O put(I key, O value) throws IllegalArgumentException{
        return this.map.put(key, value);
    }

    @Override
    public O remove(java.lang.Object key) {
        return this.map.remove(key);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<I> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<O> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<I, O>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public void putAll(Map<? extends I, ? extends O> m) {
        this.map.putAll(m);
    }

    /**
     * Surcharge de toString pour affichage
     * @return
     */
    public String toString(){
        //Lambda expression
        String str = "\n";
        this.map.forEach( (I x, O y) -> {System.out.println(x + " : " + y);});
        return str;
    }

}
