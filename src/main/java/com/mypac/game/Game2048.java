package com.mypac.game;

import com.mypac.board.Board;
import com.mypac.board.SquareBoard;
import com.mypac.direction.Direction;
import com.mypac.exception.NotEnoughSpaceException;
import com.mypac.key.Key;

import java.util.*;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    GameHelper helper = new GameHelper();
    Random random = new Random();

    /**
     * Initiates the start of the game.
     */
    @Override
    public void init() {
        board.fillBoard(Collections.nCopies(GAME_SIZE * GAME_SIZE, null));
        for (int i = 0; i < 2; i++) {
            try {
                addItem();
            } catch (NotEnoughSpaceException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    /**
     * Checks can we make a game move or not.
     *
     * @return
     */
    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        }
        for (int i = 0; i < board.getHeight(); i++) {
            var rowCurrent = board.getValues(board.getRow(i));
            if (!rowCurrent.equals(helper.moveAndMergeEqual(rowCurrent))) {
                return true;
            }
        }
        for (int j = 0; j < board.getWidth(); j++) {
            var column = board.getValues(board.getColumn(j));
            if (!column.equals(helper.moveAndMergeEqual(column))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a game move in the indicated direction.
     *
     * @param direction indicated direction
     * @return bool result of the move success ont not.
     */
    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case RIGHT -> {
                for (int i = 0; i < board.getHeight(); i++) {
                    List<Integer> inputList = board.getValues(board.getRow(i));
                    Collections.reverse(inputList);
                    List<Integer> rowRight = helper.moveAndMergeEqual(inputList);
                    int rowRightSize = rowRight.size();
                    for (int j = 0; j < rowRightSize; j++) {
                        board.addItem(board.getKey(i, rowRightSize - 1 - j), rowRight.get(j));
                    }
                }
            }
            case LEFT -> {
                for (int i = 0; i < board.getHeight(); i++) {
                    List<Integer> rowLeft = helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    for (int j = 0; j < rowLeft.size(); j++) {
                        board.addItem(board.getKey(i, j), rowLeft.get(j));
                    }
                }
            }
            case FORWARD -> {
                for (int j = 0; j < board.getWidth(); j++) {
                    List<Integer> colForward = helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
                    for (int i = 0; i < colForward.size(); i++) {
                        board.addItem(board.getKey(i, j), colForward.get(i));
                    }
                }
            }
            case BACK -> {
                for (int j = 0; j < board.getWidth(); j++) {
                    List<Integer> inputList = board.getValues(board.getColumn(j));
                    Collections.reverse(inputList);
                    List<Integer> colBack = helper.moveAndMergeEqual(inputList);
                    int colBackSize = colBack.size();
                    for (int i = 0; i < colBackSize; i++) {
                        board.addItem(board.getKey(colBackSize - 1 - i, j), colBack.get(i));
                    }
                }
            }
        }
        try {
            addItem();
        } catch (NotEnoughSpaceException except) {
            if(!canMove()){
                return false;
            }
        }
        return true;

    }

    /**
     * Adds a new element to the game.
     */
    @Override
    public void addItem() throws NotEnoughSpaceException {
        List<Key> freeSpaceKeys = board.availableSpace();
        if (!freeSpaceKeys.isEmpty()) {
            Key keySpace = freeSpaceKeys.get(random.nextInt(freeSpaceKeys.size()));
            int n = random.nextInt(1, 11);
            if (n == 10) {
                board.addItem(keySpace, 4);
            } else {
                board.addItem(keySpace, 2);
            }
        } else {
            throw new NotEnoughSpaceException("NotEnoughSpace");
        }
    }

    /**
     * Getting the playing field.
     *
     * @return board Board
     */
    @Override
    public Board getGameBoard() {
        return board;
    }

    /**
     * Winning the game.
     *
     * @return true if win.
     */
    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
