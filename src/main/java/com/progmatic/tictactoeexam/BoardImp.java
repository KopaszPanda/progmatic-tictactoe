package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.ArrayList;
import java.util.List;

public class BoardImp implements Board {

    private final Cell[][] board;
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;

    public BoardImp() {
        this.board = new Cell[ROW_COUNT][COL_COUNT];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    @Override
    public PlayerType getCell(int rowIdx, int colIdx) throws CellException {
        if (rowIdx >= ROW_COUNT || rowIdx < 0) {
            throw new CellException(rowIdx, colIdx, "Row index is not valid");
        }
        if (colIdx >= COL_COUNT || colIdx < 0) {
            throw new CellException(rowIdx, colIdx, "Column index is not valid");
        }
        return this.board[rowIdx][colIdx].getCellsPlayer();
    }

    @Override
    public void put(Cell cell) throws CellException {
        if (!getCell(cell.getRow(), cell.getCol()).equals(PlayerType.EMPTY)) {
            throw new CellException(cell.getRow(), cell.getCol(), "Cell is not empty");
        } else {
            board[cell.getRow()][cell.getCol()]=cell;//.setCellsPlayer(cell.getCellsPlayer());
        }
    }

    @Override
    public boolean hasWon(PlayerType p) {
        //Checks in rows
        for (int i = 0; i < ROW_COUNT; i++) {
            if (board[i][0].getCellsPlayer() == board[i][1].getCellsPlayer()) {
                if (board[i][0].getCellsPlayer() == board[i][2].getCellsPlayer()) {
                    return board[i][0].getCellsPlayer() == p;
                }
            }
        }
        //Checks in columns
        for (int i = 0; i < COL_COUNT; i++) {
            if (board[0][i].getCellsPlayer() == board[1][i].getCellsPlayer()) {
                if (board[0][i].getCellsPlayer() == board[2][i].getCellsPlayer()) {
                    return board[0][i].getCellsPlayer() == p;
                }
            }
        }
        //Checks diagonal top-left corner ->bottom-right corner
        if (board[1][1].getCellsPlayer() == board[0][0].getCellsPlayer()) {
            if (board[1][1].getCellsPlayer() == board[2][2].getCellsPlayer()) {
                return board[1][1].getCellsPlayer() == p;
            }
        }
        //Checks diagonal bottom-left corner -> top-right corner 
        if (board[1][1].getCellsPlayer() == board[0][2].getCellsPlayer()) {
            if (board[1][1].getCellsPlayer() == board[2][0].getCellsPlayer()) {
                return board[1][1].getCellsPlayer() == p;
            }
        }
        return false;
    }

    @Override
    public List<Cell> emptyCells() {
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (board[i][j].getCellsPlayer()==PlayerType.EMPTY) {
                    cellList.add(new Cell(i,j));
                }
            }
        }
//        for (Cell[] cells : board) {
//            for (Cell cell : cells) {
//                if (cell.getCellsPlayer().equals(PlayerType.EMPTY)) {
//                    cellList.add(cell);
//                }
//            }
//        }
        return cellList;
    }

}
