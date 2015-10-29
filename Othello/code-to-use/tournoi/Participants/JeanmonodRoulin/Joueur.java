package Participants.JeanmonodRoulin;

import Othello.*;
import java.lang.Math;
import java.util.ArrayList;
import Participants.JeanmonodRoulin.*;
import java.util.Random;

public class Joueur extends Othello.Joueur
{// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
	private GameBoard gameBoard;
	private int other;
	
	public Joueur(int depth, int playerID)
	{// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
		super();
		
		this.OVERLORDZ_JEEZ_MYGAD_STEELMANOFTHEDYINGKAYAK = -0.4596994100811267;
		this.FsfF48sfa86asf111fa_fa421 = -1.4370191187157024;
		this.fuf$faos888fafefeafilhof$faoutteoiseau$2$1410fgentiloiseau$2$1410f = -0.15125205405091097;
		this.BonjourMadameCetteVariableEstTresUtileALaConceptionDeCetAlgorithmeDeTypeAligator = -0.880909081790645; 
		this.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = -0.030204036176675872;
		this.abcdefghijklmnopqrstuvwxyzAlphabetahahahahahaha = -0.18623545378707648;
		this.playerID = playerID;
		this.depth = depth;
		this.other =  (playerID == 1 ? 0 : 1);
		gameBoard = new GameBoard();
	}
	public Move nextPlay(Move move)
	{
		// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
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
	private double OVERLORDZ_JEEZ_MYGAD_STEELMANOFTHEDYINGKAYAK;
	private double FsfF48sfa86asf111fa_fa421 ;
	private double fuf$faos888fafefeafilhof$faoutteoiseau$2$1410fgentiloiseau$2$1410f;
	private double BonjourMadameCetteVariableEstTresUtileALaConceptionDeCetAlgorithmeDeTypeAligator;
	private double aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ;
	private double abcdefghijklmnopqrstuvwxyzAlphabetahahahahahaha;
	private void evaluate(Node node, GameBoard gb) // lol best algorithm ever ever ever ever ever ever ever ever 
	{
		// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
		double NUMBEROFSQUIGLYROUNDY = gb.getCoinCount(playerID) + gb.getCoinCount(other);
		double b = (64 - NUMBEROFSQUIGLYROUNDY) / 64;
		double apriorisonnesaitpluscequilsepassedanscecode = -(b + OVERLORDZ_JEEZ_MYGAD_STEELMANOFTHEDYINGKAYAK) * (b + BonjourMadameCetteVariableEstTresUtileALaConceptionDeCetAlgorithmeDeTypeAligator);
		double slct = -(b + FsfF48sfa86asf111fa_fa421) * (b + aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa);
		double HarryPotterAndTheReturnOfTheWhiteGoddessJamesBrownPortal2 = -(b + fuf$faos888fafefeafilhof$faoutteoiseau$2$1410fgentiloiseau$2$1410f) * (b + abcdefghijklmnopqrstuvwxyzAlphabetahahahahahaha);	
		
		double nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor = gb.getPossibleMoves(other).size();
		
		double THEREALPURPOSEOFTHISGAME = gb.getCoinCount(playerID) - gb.getCoinCount(other);
		
		double salutlacompagnie = gb.getCoinCount(playerID) * 5  - gb.getCoinCount(other) * 4
								- gb.getEdgeCoinCount(playerID) * 2  + gb.getEdgeCoinCount(other)
								+ gb.getCornerCoinCount(playerID) * 50 - gb.getCoinCount(other) * 25;
	
			double ANDTHEBESTFUNCTIONEVERRETUUUUUURNS = nbPossibleMEGAENEMYBOSSsexyMovesStreetfightFuckITsaLongnamezor * apriorisonnesaitpluscequilsepassedanscecode 
				+ slct * salutlacompagnie + HarryPotterAndTheReturnOfTheWhiteGoddessJamesBrownPortal2 * THEREALPURPOSEOFTHISGAME;
		node.setEvaluation((int)(ANDTHEBESTFUNCTIONEVERRETUUUUUURNS * 5000));
	}
	
	private int alphaBeta(Node root, int d, int minOrMax, int parentValue, GameBoard gb, Node datMove)
	{
		// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
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

// _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
		   
		   
		   // > be me 22 +-
		   // > playing algorithm
		   // > making crocodiles
		   // > wat.jpg
		   
		   
		   //everybody walking the dinosaur