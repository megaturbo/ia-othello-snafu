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
	
	public Joueur(int depth, int playerID, double rAA, double rAE, double rCA, double rCE, double rE, double rA, double rAm, double rEm)
	{
		super();
		// this.datparam = datparam;
		// this.slparam = slparam;
		// this.TRPparam = TRPparam;
		// this.datfact = datfact; 
		// this.slcfact = slcfact;
		// this.TRPfact = TRPfact;
		// this.playerID = playerID;
		
	this.rAA = rAA;
	this.rAE = rAE;
	this.rCA = rCA;
	this.rCE = rCE;
	this.rE = rE;
	this.rA = rA;
	this.rAm = rAm;
	this.rEm = rEm;
		this.depth = depth;
		this.other =  (playerID == 1 ? 0 : 1);
		gameBoard = new GameBoard();
	}
	public Joueur(int depth, int playerID)
	{
		super();
		
		// this.datparam = -0.9307259935485904;
		// this.slparam = -0.4398137270049671;
		// this.TRPparam = -0.7595574422895612;
		// this.datfact = -0.565870863857436; 
		// this.slcfact = -1.57858270749288;
		// this.TRPfact = -1.6657634658915654;
		// this.datparam = -0.4596994100811267;
		// this.slparam = -1.4370191187157024;
		// this.TRPparam = -0.15125205405091097;
		// this.datfact = -0.880909081790645; 
		// this.slcfact = -0.030204036176675872;
		// this.TRPfact = -0.18623545378707648;
		// this.datparam = -1.1053228583399208;
		// this.slparam = -0.36970919544546776;
		// this.TRPparam = -0.05030395739700148;
		// this.datfact = 0.6918336650304722; 
		// this.slcfact = -1.1004668992500286;
		// this.TRPfact = -1.0432547897554543;
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
	private double rAA;
	private double rAE;
	private double rCA;
	private double rCE;
	private double rE;
	private double rA;
	private double rAm;
	private double rEm;
	//Random CHOCOLATEWITHMUSHROOMSINITSHOULDNOTBEEATEN = new Random();
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever ever ever ever ever ever ever ever 
	{
		node.setEvaluation((int)(gb.getEdgeCoinCount(playerID) * rAA+		gb.getEdgeCoinCount(other) * rAE+
		 gb.getCornerCoinCount(playerID) * rCA+
		 gb.getCornerCoinCount(other) * rCE+
		 gb.getCoinCount(playerID) * rE+
		gb.getCoinCount(other) * rA+
		 gb.getPossibleMoves(playerID).size() * rAm+
		 gb.getPossibleMoves(other).size() * rEm));
	}
	private double datparam;
	private double slparam ;
	private double TRPparam;
	private double datfact;
	private double slcfact ;
	private double TRPfact;
	private void evaluateOld(Node node, GameBoard gb) // lol best algorithm ever ever ever ever ever ever ever ever 
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
	
			double ANDTHEBESTFUNCTIONEVERRETUUUUUURNS = nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor * datt 
				+ slct * salutlacompagnie + TRPt * THEREALPURPOSEOFTHISGAME;
		node.setEvaluation((int)(ANDTHEBESTFUNCTIONEVERRETUUUUUURNS * 5000));
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