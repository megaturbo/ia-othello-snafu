package Participants.....;

import Othello.Move;


/**
 * Managages the player
 * @author Ellenberger Patrick and Moll Adrian
 *
 */
public class Joueur extends Othello.Joueur{

	private int depth;
	private int enemyID;
	private GameBoard gameBoard;
	
	
	public Joueur(int depth, int playerID) {
		super();
		this.depth = depth;
		this.playerID = playerID;
		this.enemyID = 1-playerID;
		this.gameBoard = new GameBoard();
	}


	/**
	 * Method called every time the player has to play
	 */
	public Move nextPlay(Move move) {
		
		//Add enemy coin to the gameboard
		gameBoard.addCoin(move, enemyID);
		
		//Get the best move (null if no move possible)
		Move bestMove = AI.getBestMove(gameBoard, depth, playerID);
		
		//Add player coin to the gameboard
		gameBoard.addCoin(bestMove, playerID);
		return bestMove;
	}

}