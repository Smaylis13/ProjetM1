package fr.univtln.madapm.votemanager.metier;

import java.util.*;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */
public class CMap<I, O> implements Map<I, O> {

    private Map<I, O> mMap = new HashMap<>();

    public CMap() {}

    public CMap(Map<I, O> map) {
        this.mMap = map;
    }

    /**
     * Implémentation des méthodes Map
     */
    @Override
    public int size() {
        return this.mMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.mMap.isEmpty();
    }

    @Override
    public boolean containsKey(java.lang.Object key) {
        return this.mMap.containsKey(key);
    }

    @Override
    public boolean containsValue(java.lang.Object value) {
        return this.mMap.containsValue(value);
    }

    @Override
    public O get(java.lang.Object key) {
        return this.mMap.get(key);
    }

    @Override
    public O put(I key, O value) throws IllegalArgumentException{
        return this.mMap.put(key, value);
    }

    @Override
    public O remove(java.lang.Object key) {
        return this.mMap.remove(key);
    }

    @Override
    public void clear() {
        this.mMap.clear();
    }

    @Override
    public Set<I> keySet() {
        return this.mMap.keySet();
    }

    @Override
    public Collection<O> values() {
        return this.mMap.values();
    }

    @Override
    public Set<Entry<I, O>> entrySet() {
        return this.mMap.entrySet();
    }

    @Override
    public void putAll(Map<? extends I, ? extends O> m) {
        this.mMap.putAll(m);
    }

    /**
     * Surcharge de toString pour affichage
     * @return Affichache en texte de la Map
     */
    public String toString(){
        //Lambda expression
        String str = "\n";
        this.mMap.forEach((I x, O y) -> System.out.println(x + " : " + y));
        return str;
    }

}
