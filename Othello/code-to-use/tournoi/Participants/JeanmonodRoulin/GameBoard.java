package Participants.JeanmonodRoulin;

import java.util.ArrayList;

import Othello.Move;

/**
 * Contains the state of the game (coins position)
 * 
 * @author Ellenberger Patrick and Moll Adrian
 * 
 */
public class GameBoard {

	public static final int RED_COIN = 0;
	public static final int BLUE_COIN = 1;
	public static final int NO_COIN = -1;
	public static final int BOARD_SIZE = 8;

	/***
	 * Contains game state
	 */
	private int[][] gameBoard;

	/***
	 * List of coins to reverse
	 */
	private ArrayList<Move> reverseList;

	/***
	 * List of coins which are eventually going to be reversed
	 */
	private ArrayList<Move> tempReverseList;

	public GameBoard() {
		reverseList = new ArrayList<>();
		tempReverseList = new ArrayList<>();

		this.gameBoard = new int[BOARD_SIZE][BOARD_SIZE];

		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				gameBoard[line][column] = -1;
			}
		}

		// Init start coins
		gameBoard[3][3] = BLUE_COIN;
		gameBoard[3][4] = RED_COIN;
		gameBoard[4][3] = RED_COIN;
		gameBoard[4][4] = BLUE_COIN;

	}

	/***
	 * Cloning a game board : copies only gameState
	 */
	public GameBoard clone() {
		GameBoard newBoard = new GameBoard();
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				newBoard.gameBoard[line][column] = gameBoard[line][column];
			}
		}
		return newBoard;

	}

	/***
	 * Set coin (no reverse actions) If m null --> no action
	 * 
	 * @param m
	 *            position of the coin
	 * @param playerID
	 *            id of the player
	 */
	public void setCoin(Move m, int playerID) {
		if (m != null) {
			gameBoard[m.j][m.i] = playerID;
		}
	}

	/***
	 * Adding coin (reverse action done) If m null --> no action
	 * 
	 * @param m
	 * @param playerID
	 */
	public void addCoin(Move m, int playerID) {
		if (m != null) {
			gameBoard[m.j][m.i] = playerID;
			reverseCoins(m, playerID);

		}
	}

	/***
	 * Get number of coins of the current player
	 * 
	 * @param playerID
	 *            id of the current player
	 * @return
	 */
	public int getCoinCount(int playerID) {
		int n = 0;
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (gameBoard[line][column] == playerID)
					n++;
			}
		}
		return n;
	}

	/***
	 * Get number of edge coins of the current player
	 * 
	 * @param playerID
	 *            id of the current player
	 * @return
	 */
	public int getEdgeCoinCount(int playerID) {
		int n = 0;
		int line = 0;
		// Count coins in first and last line
		for (int column = 0; column < BOARD_SIZE; column++) {

			if (gameBoard[line][column] == playerID)
				n++;

			line = BOARD_SIZE - 1;

			if (gameBoard[line][column] == playerID)
				n++;
		}

		int column = 0;

		// Count coins in first and last column
		for (line = 0; line < BOARD_SIZE; line++) {
			if (gameBoard[line][column] == playerID)
				n++;

			column = BOARD_SIZE - 1;

			if (gameBoard[line][column] == playerID)
				n++;
		}
		return n;
	}

	/***
	 * Get number of corner coins of the current player
	 * 
	 * @param playerID
	 *            id of the current player
	 * @return
	 */
	public int getCornerCoinCount(int playerID) {
		int n = 0;
		// UP LEFT
		if (gameBoard[0][0] == playerID)
			n++;

		// DOWN LEFT
		if (gameBoard[BOARD_SIZE - 1][0] == playerID)
			n++;

		// UP RIGHT
		if (gameBoard[0][BOARD_SIZE - 1] == playerID)
			n++;

		// DOWN RIGHT
		if (gameBoard[BOARD_SIZE - 1][BOARD_SIZE - 1] == playerID)
			n++;

		return n;
	}

	public void displayGameBoard() {
		System.out.println("************ Game Board ************");
		System.out.println();
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				System.out.print(gameBoard[line][column] + "\t");
			}
			System.out.print("\n");
		}
	}

	/***
	 * Get all possible moves at this game state
	 * 
	 * @param playerID
	 *            id of the player
	 * @return
	 */
	public ArrayList<Move> getPossibleMoves(int playerID) {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (isMovePossible(line, column, playerID))
					possibleMoves.add(new Move(column, line));
			}
		}

		return possibleMoves;
	}

	/***
	 * Get the id of the coin at a certain position
	 * 
	 * @param line
	 * @param column
	 * @return
	 */
	public int getPlayerIDAtPos(int line, int column) {
		return gameBoard[line][column];
	}

	/***
	 * Reverse the coins which hove to be for this coin
	 * 
	 * @param move
	 *            position of the coin added
	 * @param currentID
	 *            id of the player
	 */
	private void reverseCoins(Move move, int currentID) {
		reverseList.clear();

		int line = move.j;
		int column = move.i;

		// Checking the entire board if coins have to be reverted (are added to
		// revertList)
		testDirection(line, column, currentID, 0, 1, false);
		testDirection(line, column, currentID, -1, 1, false);
		testDirection(line, column, currentID, -1, 0, false);
		testDirection(line, column, currentID, -1, -1, false);
		testDirection(line, column, currentID, 0, -1, false);
		testDirection(line, column, currentID, 1, -1, false);
		testDirection(line, column, currentID, 1, 0, false);
		testDirection(line, column, currentID, 1, 1, false);

		// Reverting the coins
		for (Move m : reverseList) {
			int c = gameBoard[m.j][m.i];
			if (c != currentID) {
				gameBoard[m.j][m.i] = currentID;
			}
		}

	}

	/***
	 * Check if coins have to be reverted (only one direction)
	 * @param line
	 * @param column
	 * @param currentID
	 * @param dLine
	 * @param dColumn
	 * @param ennemyFound
	 */
	private void testDirection(int line, int column, int currentID, int dLine,
			int dColumn, boolean ennemyFound) {
		tempReverseList.clear();

		// Adding eventual reverses to tempReverseList
		boolean moveTest = reverseDirection(line, column, currentID, dLine,
				dColumn, ennemyFound);

		// If the tempReverses have to be done
		if (moveTest) {

			// Add the tempReverses to the reverseList
			reverseList.addAll(tempReverseList);
		}
	}

	/***
	 * Reverse coins on this direction (are added to tempReverseList)
	 * 
	 * @param line
	 * @param column
	 * @param currentID
	 * @param dLine
	 * @param dColumn
	 * @param ennemyFound
	 * @return
	 */
	private boolean reverseDirection(int line, int column, int currentID,
			int dLine, int dColumn, boolean ennemyFound) {
		// Check if next square is out of board
		if (line + dLine >= BOARD_SIZE || column + dColumn >= BOARD_SIZE
				|| line + dLine < 0 || column + dColumn < 0) {
			return false;
		}

		// Get playerID of the next cell
		int idPlayerAtPos = getPlayerIDAtPos(line + dLine, column + dColumn);

		// Not player --> return false
		if (idPlayerAtPos == -1) {
			return false;
		}

		if (idPlayerAtPos != currentID) {

			// Add position to temp reverseList
			tempReverseList.add(new Move(column + dColumn, line + dLine));
			return reverseDirection(line + dLine, column + dColumn, currentID,
					dLine, dColumn, true);
		} else if (ennemyFound) {
			return true;
		}
		return false;
	}

	/***
	 * Checking ig the given move is possible
	 * 
	 * @param line line to check
	 * @param column column to check
	 * @param playerID player id
	 * @return
	 */
	private boolean isMovePossible(int line, int column, int playerID) {

		if (gameBoard[line][column] != -1) {
			return false;
		}

		boolean moveTest = false;

		moveTest |= checkDirection(line, column, playerID, 0, 1, false);
		moveTest |= checkDirection(line, column, playerID, -1, 1, false);
		moveTest |= checkDirection(line, column, playerID, -1, 0, false);
		moveTest |= checkDirection(line, column, playerID, -1, -1, false);
		moveTest |= checkDirection(line, column, playerID, 0, -1, false);
		moveTest |= checkDirection(line, column, playerID, 1, -1, false);
		moveTest |= checkDirection(line, column, playerID, 1, 0, false);
		moveTest |= checkDirection(line, column, playerID, 1, 1, false);

		return moveTest;
	}

	/***
	 * Checking if move possible by testing only one direction
	 * 
	 * @param line line to check
	 * @param column column to check
	 * @param playerID player id
	 * @param dLine direction line
	 * @param dColumn direction column
	 * @param ennemyFound is enemy found
	 * @return
	 */
	private boolean checkDirection(int line, int column, int playerID,
			int dLine, int dColumn, boolean ennemyFound) {
		// Check if next square is out of board
		if (line + dLine >= BOARD_SIZE || column + dColumn >= BOARD_SIZE
				|| line + dLine < 0 || column + dColumn < 0) {
			return false;
        }

		// Get playerID of the next cell
		int idPlayerAtPos = getPlayerIDAtPos(line + dLine, column + dColumn);

		// Not player --> return false
		if (idPlayerAtPos == -1) {
			return false;
		}

		if (idPlayerAtPos != playerID) {

			return checkDirection(line + dLine, column + dColumn, playerID,
					dLine, dColumn, true);
		} else if (ennemyFound) {
			return true;
		}
		return false;
	}
}
