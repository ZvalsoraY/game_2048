package com.mypac.board;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SquareBoardTest {


    private SquareBoard<Integer> squareBoard;


    @Test
    void testFillBoardWrongLIstSize() {
        squareBoard = new SquareBoard<>(4);
        List<Integer> list = new ArrayList<>();
        for (var i = 0; i < 17; i++) {
            list.add(i);
        }
        assertThrows(RuntimeException.class, () -> squareBoard.fillBoard(list));
    }
}
