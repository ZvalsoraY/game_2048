package com.mypac.game;

import com.mypac.board.Board;
import com.mypac.direction.Direction;
import com.mypac.exception.NotEnoughSpaceException;

public interface Game {

    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpaceException;
    Board getGameBoard();
    boolean hasWin();

}
