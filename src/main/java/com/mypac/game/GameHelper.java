package com.mypac.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        var resListSize = list.size();
        List<Integer> resList = new ArrayList<Integer>(resListSize);

        if(list.isEmpty()){
            return resList;
        }

        List<Integer> listExtNull = new ArrayList<>();
        for (Integer i : list) {
            if (i != null){
                listExtNull.add(i);
            }
        }
        var listExtNullSize = listExtNull.size();

        for (int i = 0; i < resListSize; i++) {
            if(i < listExtNullSize){
                var curVal = listExtNull.get(i);
                if (i < listExtNullSize - 1 && curVal == listExtNull.get(i+1)){
                    resList.add(curVal * 2);
                    i++;
                    resListSize ++;///
                } else {
                    resList.add(curVal);
                }
            } else {
                resList.add(null);
            }
        }

        return resList;
    }
}
