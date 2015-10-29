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
	
	public Joueur(int depth, int playerID, double datparam, double slparam,double TRPparam,double datfact,double slcfact, double TRPfact)
	{
		super();
		this.datparam = datparam;
		this.slparam = slparam;
		this.TRPparam = TRPparam;
		this.datfact = datfact; 
		this.slcfact = slcfact;
		this.TRPfact = TRPfact;
		this.playerID = playerID;
		this.depth = depth;
		this.other =  (playerID == 1 ? 0 : 1);
		gameBoard = new GameBoard();
	}
	public Joueur(int depth, int playerID)
	{
		super();
		this.datparam = 0.187931500895811;
		this.slparam = 0.11876434572118046;
		this.TRPparam = 0.020995096480407253;
		this.datfact = 0.05623685343371456; 
		this.slcfact = 0.0510961331443993456;
		this.TRPfact = -0.04236989773845973;
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
	private double datparam;
	private double slparam ;
	private double TRPparam;
	private double datfact;
	private double slcfact ;
	private double TRPfact;
	Random CHOCOLATEWITHMUSHROOMSINITSHOULDNOTBEEATEN = new Random();
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever ever ever ever ever ever ever ever 
	{
		double NUMBEROFSQUIGLYROUNDY = gb.getCoinCount(playerID) + gb.getCoinCount(other);
		double b = (64 - NUMBEROFSQUIGLYROUNDY) / 64;
		double datt = -(b + datparam) * (b + datfact);
		double slct = -(b + slparam) * (b + slcfact);
		double TRPt = -(b + TRPparam) * (b + TRPfact);
		
		double nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor = gb.getPossibleMoves(other).size();
		
		double THEREALPURPOSEOFTHISGAME = gb.getCoinCount(playerID) - gb.getCoinCount(other);
		
		double salutlacompagnie = gb.getCoinCount(playerID) * 5  - gb.getCoinCount(other) * 4
								- gb.getEdgeCoinCount(playerID) * 2  + gb.getEdgeCoinCount(other)
								+ gb.getCornerCoinCount(playerID) * 50 - gb.getCoinCount(other) * 25;
		
		// double HARRYPOTTERBACKTOTHEFUTURE = datparam;
		// double ABRACADABRA = CHOCOLATEWITHMUSHROOMSINITSHOULDNOTBEEATEN.nextDouble();
		
		//double JanvierFevrierMardiMercredi = Math.sin(ABRACADABRA * HARRYPOTTERBACKTOTHEFUTURE);
		
		// double ANDTHEBESTFUNCTIONEVERRETUUUUUURNS =  
			// finalPUBLICSTATICLOLrate4nbPENEMYMEGAMOVES * Math.log(datfact) * Math.log(datparam)
			// + Math.log(slcfact) * salutlacompagnie * Math.log(slparam)
			// + THEREALPURPOSEOFTHISGAME * Math.log(TRPfact) * Math.log(TRPparam) + JanvierFevrierMardiMercredi;
			double ANDTHEBESTFUNCTIONEVERRETUUUUUURNS = nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor * datt 
				+ slct * salutlacompagnie + TRPt * THEREALPURPOSEOFTHISGAME;
		node.setEvaluation((int)ANDTHEBESTFUNCTIONEVERRETUUUUUURNS);
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
		Node optOp = new Node(root.getMove());
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