package Participants.JeanmonodRoulin;

import Othello.*;
import java.lang.Math;
import java.util.ArrayList;
import Participants.JeanmonodRoulin.*;
import java.util.Random;

public class Joueur extends Othello.Joueur
{
	private GameBoard gameBoard;
	private int other;
	public Joueur(){
		super();
	}
	
	public Joueur(int depth, int playerID)
	{
		super();
		this.playerID = playerID;
		this.depth = depth;
		this.other =  playerID == 1 ? 0 : 1;
		gameBoard = new GameBoard();
	}
	public Move nextPlay(Move move)
	{
		gameBoard.addCoin(move, other);
		Node origin = new Node(move);
		evaluate(origin, gameBoard);
		Node datMove = new Node(move);
		int v = alphaBeta(origin, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, 1, gameBoard, datMove);
		
		gameBoard.addCoin(datMove.getMove(), playerID);
	    return datMove.getMove();
	}
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever
	{
		//Random random = new Random();
		int salutlacompagnie = gb.getCoinCount(playerID) * 2 - gb.getCoinCount(other)
								+ gb.getEdgeCoinCount(playerID) * 5 - gb.getEdgeCoinCount(other) * 4
								+ gb.getCornerCoinCount(playerID) * 10 - gb.getCoinCount(other) * 8;
		node.setEvaluation(salutlacompagnie);
		//node.setEvaluation(random.nextInt(100));
	}
	private int alphaBeta(Node node, int d, int alpha, int beta, int player, GameBoard gb, Node datMove)
	{
		for(Move checkDatMoves : gb.getPossibleMoves(player))
			node.addChildNode(new Node(checkDatMoves));
		if (d == 0 || node.isLeaf())
		{
			evaluate(node, gb);
			System.out.println("EVAL " + node.getEvaluation());
			return node.getEvaluation();
		}
		if(player == playerID)
		{
			int v = -Integer.MAX_VALUE;
			for(Node child: node.getChildNodeList())
			{
				GameBoard clone = gb.clone();
				clone.addCoin(child.getMove(), player);
				v = Math.max(v, alphaBeta(child, d - 1, alpha, beta, other, clone, datMove));
				if(v > alpha)
				{
					datMove.setMove(child.getMove());
						System.out.println("SALUT ENCULE");
				}
				alpha = Math.max(alpha, v);
				if (beta >= alpha)
					break;
			}
			return v;
		}
		else
		{
			int v = Integer.MAX_VALUE;
			for(Node child: node.getChildNodeList())
			{
				GameBoard clone = gb.clone();
				clone.addCoin(child.getMove(), other);
				v = Math.min(v, alphaBeta(child, d - 1, alpha, beta, playerID, clone, datMove));
				beta = Math.min(beta, v);
				if(v < beta)
				{
					datMove.setMove(child.getMove());
						System.out.println("SALUT TROUDUCL");
				}
				if (beta <= alpha)
					break;
			}
			return v;
		}
	}
}