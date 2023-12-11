package com.mypac.game;

import com.mypac.board.Board;
import com.mypac.board.SquareBoard;
import com.mypac.direction.Direction;
import com.mypac.key.Key;
import com.mypac.exception.NotEnoughSpaceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypac.game.Game2048.GAME_SIZE;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class Game2048Test {
    @Test
    void gameTest() throws NotEnoughSpaceException {
        var game = new Game2048();
        Board<Key, String> b2 = new SquareBoard<>(1);
        b2.fillBoard(asList("hello"));
        if (!"hello".equals(b2.getValue(b2.getKey(0 ,0)))) throw new RuntimeException("board not work =(");
        if (!b2.hasValue("hello")) throw new RuntimeException("board not work =(");
        Board<String, Double> b3 = new Board<>(1,1) {
            @Override public void fillBoard(List<Double> list) {

            }
            @Override public List<String> availableSpace() {
                return null;
            }
            @Override public void addItem(String key, Double value) {

            }
            @Override public String getKey(int i, int j) {
                return null;
            }
            @Override public Double getValue(String key) {
                return null;
            }
            @Override public List<String> getColumn(int j) {
                return null;
            }
            @Override public List<String> getRow(int i) {
                return null;
            }
            @Override public boolean hasValue(Double value) {
                return false;
            }
            @Override public List<Double> getValues(List<String> keys) {
                return null;
            }
        };
        Board<Key, Integer> b = game.getGameBoard();
        if (!b.availableSpace().isEmpty()) throw new RuntimeException("Game board must be empty before initialize");
        b.fillBoard(asList(2,null,null,8, 2,2,8,8, 2,null,2,2, 4,2,4,2048));
        if (!game.hasWin()) throw new RuntimeException("hasWin not work =(");
        game.move(Direction.LEFT);
        if (b.availableSpace().size() != 5) throw new RuntimeException("move must be add item");
        assertEquals(b.getValues(b.getRow(0)).subList(0,2), asList(2, 8));
        assertEquals(b.getValues(b.getRow(1)).subList(0,2), asList(4, 16));
        assertEquals(b.getValues(b.getRow(2)).subList(0,2), asList(4, 2));
        assertEquals(b.getValues(b.getRow(3)), asList(4, 2, 4, 2048));
        game.move(Direction.BACK);
        assertEquals(b.getValues(b.getColumn(0)).subList(1,4), asList(2, 4, 8));
        assertEquals(b.getValues(b.getColumn(1)).subList(1,4), asList(8, 16, 4));

        game.init();
        if (b.availableSpace().size() != 14) throw new RuntimeException("init must be add 2 item");
        if (!game.canMove()) throw new RuntimeException("canMove not work =(");
        game.addItem();
        if (b.availableSpace().size() != 13) throw new RuntimeException("addItem must be add 1 item");
    }

    @Test
    void init_exception() throws NotEnoughSpaceException {
        var game = new Game2048();
        game.init();
        for (var i = 0; i < GAME_SIZE * GAME_SIZE - 2; i++) {
            game.addItem();
        }
        assertThrows(NotEnoughSpaceException.class, game::addItem);
    }

}