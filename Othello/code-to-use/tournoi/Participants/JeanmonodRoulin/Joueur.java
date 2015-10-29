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
		this.other =  (playerID == 1 ? 0 : 1);
		gameBoard = new GameBoard();
	}
	public Move nextPlay(Move move)
	{
		gameBoard.addCoin(move, other);
		if (gameBoard.getPossibleMoves(playerID).size() == 0)
			return null; //well played, i concede
		Node origin = new Node(move);
		Node datMove = new Node(move);
		int v = alphaBeta(origin, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, playerID, gameBoard, datMove);
		
		gameBoard.addCoin(datMove.getMove(), playerID);
	    return datMove.getMove();
	}
	private int datparam = 30;
	private int slparam = 1;
	private int TRPparam = 3;
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever
	{
		int NUMBEROFSQUIGLYROUNDY = gb.getCoinCount(playerID) + gb.getCoinCount(other);
		int MEGATURBORATE4nPMEBsMSfFIsaLnzor = 64 - NUMBEROFSQUIGLYROUNDY;
		int ineedashortname = MEGATURBORATE4nPMEBsMSfFIsaLnzor;
		int thisbetter = ineedashortname;
		int best = thisbetter;
		int b = best; //better best
		
		int nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor = gb.getPossibleMoves(other).size();
		int finalPUBLICSTATICLOLrate4nbPENEMYMEGAMOVES = MEGATURBORATE4nPMEBsMSfFIsaLnzor * nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor;
		
		int THEREALPURPOSEOFTHISGAME = gb.getCoinCount(playerID);
		
		int salutlacompagnie = gb.getCoinCount(playerID) * 5 // - gb.getCoinCount(other) * 4
								+ gb.getEdgeCoinCount(playerID) * 2 // - gb.getEdgeCoinCount(other)
								+ gb.getCornerCoinCount(playerID) * 20;// - gb.getCoinCount(other) * 10;
		
		int datfact = b - 64;
		int slcfact = (64 - b) * b / 32;
		int TRPfact = b;
				
		int ANDTHEBESTFUNCTIONEVERRETUUUUUURNS =  finalPUBLICSTATICLOLrate4nbPENEMYMEGAMOVES * datfact * datparam
												+ salutlacompagnie * slcfact * slparam
												+ THEREALPURPOSEOFTHISGAME * TRPfact * TRPparam;
		node.setEvaluation(ANDTHEBESTFUNCTIONEVERRETUUUUUURNS);
		
		Random random = new Random();
		node.setEvaluation(random.nextInt(100));
	}
	
	//TODO check that node, not sure about that after all
	private int alphaBeta(Node node, int d, int alpha, int beta, int player, GameBoard gb, Node datMove)
	{
		for(Move checkDemMoves : gb.getPossibleMoves(player))
			node.addChildNode(new Node(checkDemMoves));
		if (d == 0 || node.isLeaf())
		{
			evaluate(node, gb);
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
				if(v >= alpha)
				{
					System.out.println(v + " " + alpha);
					alpha = v;
					datMove.setMove(child.getMove());
				}
				// alpha = Math.max(alpha, v);
				if (beta <= alpha)
					break;
			}
			return alpha; // v
		}
		else
		{
			int v = Integer.MAX_VALUE;
			for(Node child: node.getChildNodeList())
			{
				GameBoard clone = gb.clone();
				clone.addCoin(child.getMove(), player);
				v = Math.min(v, alphaBeta(child, d - 1, alpha, beta, playerID, clone, datMove));
				beta = Math.min(beta, v);
				if (beta <= alpha)
					break;
			}
			return beta; // v
		}
	}
}