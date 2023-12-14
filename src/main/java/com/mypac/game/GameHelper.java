package com.mypac.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> listInput) {
        var outputListSize = listInput.size();
        var outputList = new ArrayList<Integer>(outputListSize);

        List<Integer> listWithoutNulls = listInput.stream()
                .filter(Objects::nonNull).toList();

        var listWithoutNullsSize = listWithoutNulls.size();

        for (int i = 0; i < listWithoutNullsSize; i++) {
            var currentValue = listWithoutNulls.get(i);
            if (i < listWithoutNullsSize - 1 && currentValue == listWithoutNulls.get(i + 1)){
                outputList.add(currentValue * 2);
                i++;
            } else {
                outputList.add(currentValue);
            }
        }

        outputList.addAll(new ArrayList<>(Collections.nCopies(outputListSize - outputList.size(), null)));

        return outputList;
    }
}
