package com.target.myretail.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SplitAndReturn {
    public List<List<Long>> splitAndReturn(List<Long> inputList,
                                           int size) {
        List<List<Long>> requiredList = new ArrayList<>();
        int i = 0;
        do {
            if (i + size > inputList.size()) {
                requiredList.add(inputList.subList(i, inputList.size()));
            } else {
                requiredList.add(inputList.subList(i, i + size));
            }

            i = i + size;

        } while (i < inputList.size());
        return requiredList;
    }
}
