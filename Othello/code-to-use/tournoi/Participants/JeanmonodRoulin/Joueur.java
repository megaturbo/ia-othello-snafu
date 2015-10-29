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
	
	public Joueur(int depth, int playerID, double q, double w, double e, double r, double s, double t)
	{
		super();
		this.datparam = q;
		this.slparam = w;
		this.TRPparam = e;
		this.datfact = r; 
		this.slcfact = s;
		this.TRPfact = t;
		this.playerID = playerID;
		
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
	private double datparam;
	private double slparam ;
	private double TRPparam;
	private double datfact;
	private double slcfact ;
	private double TRPfact;
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
}