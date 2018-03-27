package com.target.myretail.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SplitAndReturnTest {
    SplitAndReturn splitAndReturn = new SplitAndReturn();
    List<Long> inputList = new ArrayList<>();

    @Before
    public void SetUp() {
        inputList.addAll(Arrays.asList(13860428L, 6933639L, 92326560L, 22368388L));

    }

    @Test
    public void testSplitAndReturn() {
        List<List<Long>> respList = splitAndReturn.splitAndReturn(inputList, 2);
        assertTrue(2 == respList.size());
    }

    @Test
    public void testSplitAndReturnWithSizeThree() {
        List<Long> inputList1 = new ArrayList<>();
        inputList1.addAll(Arrays.asList(13860428L, 6933639L, 92326560L, 1L, 22368388L));

        List<List<Long>> respList = splitAndReturn.splitAndReturn(inputList1, 2);
        assertTrue(3 == respList.size());
    }
}
