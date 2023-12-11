package com.mypac.board;

import com.mypac.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SquareBoard<V> extends Board<Key,V>{
    public SquareBoard(int size) {
        super(size, size);
    }

    /**
     * Filling the board from List.
     * @param list nubers for board filling
     */
    @Override
    public void fillBoard(List<V> list) {
        if (list.size() > width * height){
            throw new RuntimeException(String.format("Size of input data more than board."));
        }
        board.clear();
        int iList = 0;
        int listSize = list.size();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if(iList < listSize){
                    addItem(new Key(i, j), list.get(iList++));
                } else{
                    addItem(new Key(i, j), null); //may be make error or break
                }
            }
            System.out.println();
        }
    }

    /**
     * Provide list free spaces for board.
     * @return ArrayList of free spaces
     */
    @Override
    public List<Key> availableSpace() {
        List<Key> listKeySpace = new ArrayList<Key>();
        for (var entry : board.entrySet()) {
            if (entry.getValue() == null){
                listKeySpace.add(entry.getKey());
            }
        }
        return listKeySpace;
    }

    /**
     * Add element on the board by key.
     * @param key
     * @param value
     */
    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    /**
     * Find key by coordinate.
     * @param i
     * @param j
     * @return key if it was found or null
     */
    @Override
    public Key getKey(int i, int j) {
        for(Key key: board.keySet()){
            if(key.getI() == i && key.getJ() == j){
                return key;
            }
        }
        return null;
    }

    /**
     * Provide value by key.
     *
     * @param key
     * @return
     */
    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    /**
     * Key column by which values can be selected.
     * @param j number of column
     * @return ArrayList of key by j column
     */
    @Override
    public List<Key> getColumn(int j) {
        List<Key> listKeyCol = new ArrayList<Key>();
        for(int i = 0; i < width; i++){
            listKeyCol.add(getKey(i, j)); // check getKey
        }
        return listKeyCol;
    }

    /**
     * String of keys by which you can then select values.
     * @param i number of row
     * @return ArrayList of key by i row
     */
    @Override
    public List<Key> getRow(int i) {
        List<Key> listKeyRow = new ArrayList<Key>();
        for (int j = 0; j < height; j++){
            listKeyRow.add(getKey(i, j)); // check getKey
        }
        return listKeyRow;
    }

    /**
     * Checking for the presence of this value in the board field.
     * @param value
     * @return
     */
    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    /**
     * String of values by string of keys
     * @param keys
     * @return ArrayList of values
     */
    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> values = new ArrayList<V>();
        for (ListIterator<Key> keysIterator = keys.listIterator(); keysIterator.hasNext(); ) {
            //Key elementKey = keysIterator.next();
            values.add(board.get(keysIterator.next()));
        }
        return values;
    }
}