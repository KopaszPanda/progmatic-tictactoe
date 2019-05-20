package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;

public class VictoryAwarePlayerImp extends AbstractPlayer {

    public VictoryAwarePlayerImp(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) {
        //           |?|
        //Mega WTF   ...
        Cell[][] fakeBoard = new Cell[3][3];
        if (b.emptyCells().isEmpty()) {
            return null;
        }
        if (b.emptyCells().size() == 1) {
            Cell c = new Cell(b.emptyCells().get(0).getRow(), b.emptyCells().get(0).getCol(), myType);
            return c;
        }
        try {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    fakeBoard[i][j] = new Cell(i, j, b.getCell(i, j));
                }
            }
        } catch (CellException e) {
        }
        //row
        int same;
        for (int i = 0; i < 3; i++) {
            same = 0;
            Cell emptyCell = null;
            for (int j = 0; j < 3; j++) {
                if (fakeBoard[i][j].getCellsPlayer() == PlayerType.EMPTY) {
                    emptyCell = fakeBoard[i][j];
                }
                if (fakeBoard[i][j].getCellsPlayer() == myType) {
                    same++;
                }
            }
            if (same == 2 && emptyCell != null) {
                for (Cell emptyCell1 : b.emptyCells()) {
                    if (emptyCell.getCol() == emptyCell1.getCol() && emptyCell.getRow() == emptyCell1.getRow()) {
                        emptyCell1 = new Cell(emptyCell.getRow(), emptyCell.getCol(), myType);
                        return emptyCell1;
                    }
                }
            }
        }
        //column
        for (int j = 0; j < 3; j++) {
            same = 0;
            Cell emptyCell = null;
            for (int i = 0; i < 3; i++) {
                if (fakeBoard[i][j].getCellsPlayer() == PlayerType.EMPTY) {
                    emptyCell = fakeBoard[i][j];
                }
                if (fakeBoard[i][j].getCellsPlayer() == myType) {
                    same++;
                }
            }
            if (same == 2 && emptyCell != null) {
                for (Cell emptyCell1 : b.emptyCells()) {
                    if (emptyCell.getCol() == emptyCell1.getCol() && emptyCell.getRow() == emptyCell1.getRow()) {
                        emptyCell1 = new Cell(emptyCell.getRow(), emptyCell.getCol(), myType);
                        return emptyCell1;
                    }
                }
            }
        }
        //diagonal 1
        same = 0;
        Cell emptyCell = null;
        for (int j = 0; j < 3; j++) {

            if (fakeBoard[j][j].getCellsPlayer() == PlayerType.EMPTY) {
                emptyCell = fakeBoard[j][j];
            }
            if (fakeBoard[j][j].getCellsPlayer() == myType) {
                same++;
            }

            if (same == 2 && emptyCell != null) {
                for (Cell emptyCell1 : b.emptyCells()) {
                    if (emptyCell.getCol() == emptyCell1.getCol() && emptyCell.getRow() == emptyCell1.getRow()) {
                        emptyCell1 = new Cell(emptyCell.getRow(), emptyCell.getCol(), myType);
                        return emptyCell1;
                    }
                }
            }
        }
        //diagonal 2    
        for (int j = 0; j < 3; j++) {
            if (fakeBoard[Math.abs(j - 2)][j].getCellsPlayer() == PlayerType.EMPTY) {
                emptyCell = fakeBoard[Math.abs(j - 2)][j];
            }
            if (fakeBoard[Math.abs(j - 2)][j].getCellsPlayer() == myType) {
                same++;
            }
            if (same == 2 && emptyCell != null) {
                for (Cell emptyCell1 : b.emptyCells()) {
                    if (emptyCell.getCol() == emptyCell1.getCol() && emptyCell.getRow() == emptyCell1.getRow()) {
                        emptyCell1 = new Cell(emptyCell.getRow(), emptyCell.getCol(), myType);
                        return emptyCell1;
                    }
                }
            }
        }
        return null;
    }

}
