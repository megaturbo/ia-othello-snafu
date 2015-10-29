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
		evaluate(origin, gameBoard);
		Node datMove = new Node(move);
		datMove.setEvaluation(-Integer.MAX_VALUE);
		int v = alphaBeta(origin, depth, 1, origin.getEvaluation(), gameBoard, datMove);
		
		gameBoard.addCoin(datMove.getMove(), playerID);
	    return datMove.getMove();
	}
	private int datparam = 30;
	private int slparam = 1;
	private int TRPparam = 3;
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever ever ever ever ever ever ever ever 
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
		
		Random CHOCOLATEWITHMUSHROOMSINITSHOULDNOTBEEATEN = new Random();
		
		double HARRYPOTTERBACKTOTHEFUTURE = datparam;
		double ABRACADABRA = CHOCOLATEWITHMUSHROOMSINITSHOULDNOTBEEATEN.nextDouble();
		
		double JanvierFevrierMardiMercredi = (int)Math.sin(ABRACADABRA * HARRYPOTTERBACKTOTHEFUTURE);
		
		int salutlacompagnie = gb.getCoinCount(playerID) * 5 // - gb.getCoinCount(other) * 4
								+ gb.getEdgeCoinCount(playerID) * 2 // - gb.getEdgeCoinCount(other)
								+ gb.getCornerCoinCount(playerID) * 20;// - gb.getCoinCount(other) * 10;
		
		int datfact = b - 64;
		int slcfact = (64 - b) * b / 32;
		int TRPfact = b;
				
		int ANDTHEBESTFUNCTIONEVERRETUUUUUURNS =  finalPUBLICSTATICLOLrate4nbPENEMYMEGAMOVES * datfact * datparam
												+ salutlacompagnie * slcfact * slparam
												+ (int)(THEREALPURPOSEOFTHISGAME * TRPfact * TRPparam * JanvierFevrierMardiMercredi);
		node.setEvaluation(ANDTHEBESTFUNCTIONEVERRETUUUUUURNS);
	}
	
	private int alphaBeta(Node root, int d, int minOrMax, int parentValue, GameBoard gb, Node datMove)
	{
		int player = minOrMax == 1 ? playerID : other;
		for(Move checkDemMoves : gb.getPossibleMoves(player))
			root.addChildNode(new Node(checkDemMoves));
		if (d == 0 || root.isLeaf())
		{
			evaluate(root, gb);
			return root.getEvaluation();			
		}
		int optVal = minOrMax * -Integer.MAX_VALUE;
		Node optOp = null;
		f: for(Node child: root.getChildNodeList())
		{
			GameBoard clone = gb.clone();
			clone.addCoin(child.getMove(), player);
			int val = alphaBeta(child, d - 1, -minOrMax, optVal, clone, datMove);
			if (val * minOrMax > optVal * minOrMax)
			{
				optVal = val;
				optOp = child;
				if(optVal * minOrMax > parentValue * minOrMax)
					break f;
			}
		}
		if (depth == d)datMove.setMove(optOp.getMove());
		return optVal;
	}
	
	//TODO check that node, not sure about that after all
	private int fuckthismethod(Node node, int d, int alpha, int beta, int player, GameBoard gb, Node datMove)
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
			f: for(Node child: node.getChildNodeList())
			{
				GameBoard clone = gb.clone();
				clone.addCoin(child.getMove(), player);
				v = Math.max(v, fuckthismethod(child, d - 1, alpha, beta, other, clone, datMove));
				if(d == depth && v >= datMove.getEvaluation())
				{
					// alpha = v;
					datMove.setMove(child.getMove());
					datMove.setEvaluation(v);
				}
				alpha = Math.max(alpha, v);
				if (beta <= alpha)
					break f;
			}
			return alpha; // v
		}
		else
		{
			int v = Integer.MAX_VALUE;
			f: for(Node child: node.getChildNodeList())
			{
				GameBoard clone = gb.clone();
				clone.addCoin(child.getMove(), player);
				v = Math.min(v, fuckthismethod(child, d - 1, alpha, beta, playerID, clone, datMove));
				beta = Math.min(beta, v);
				if (beta <= alpha)
					break f;
			}
			return beta; // v
		}
	}
}