package Participants.JeanmonodRoulin;
import Othello.*;
import java.lang.Math;
import java.util.ArrayList;
import Participants.JeanmonodRoulin.*;
import java.util.Random;

//CROCODILE MAKER

               // _.'^^'.    
    // _      _.-' ((@)) '.   ./\/\/\/\/\/\,.---.__
 // ..'o'...-'      ~~~    '~/\/\/\/\/\/\__.---.   `-._
// :                          /\/\/\/\,-'              `-.__   jgs
// ^VvvvvvvvvvvVvVv                   |                     `-._
  // ;^^^^^^^^^^^`      /             `\        /               `-._
   // ```````````````'.`                `\     (                    `'-._
            // .-----'`   /\              `\    )--.______.______._______`/ 
           // (((------'``  `'--------'`(((----'
		   
//THIS CLASS CREATES THE PERFECT EVALUATION FUNCTION, WHEN IT STOPS EVOLVING, THEN THE CROCODILE IS MADE
class Test{
	 static Random RANDOMIZEME = new Random();
	
  public static void main(String[] args)
	{
		double p1[] = new double[6];
		double p2[] = new double[6];
		p1[0] = Math.exp(0);
		p1[1] = Math.exp(0);
		p1[2] = Math.exp(0);
		p1[3] = Math.exp(0); 
		p1[4] = Math.exp(0);//2
		p1[5] = Math.exp(0);
		p2 = copyOfACopy(p1);
		System.out.println("starting");
		LETSDOTHISSHIT(p1, p2);
	}
	
	public static void LETSDOTHISSHIT(double[] p1, double[] p2)
	{
		SHOWMEWHATYOUGOT(p1);
		int DISQUALIFIED = -1;
		int CROCODILEFACTOR = 0;
		f: for(int i = 0; i < 10000; i++)
		{
			System.out.println("SHOWMEWHATYOUGOOOT " + i);
			DISQUALIFIED = FIGHT(p1, p2);
			if(DISQUALIFIED == 0)
			{
				System.out.println("P2 DISQUALIFED !!!");
				p2 = copyOfACopy(p1);
				SHOWMEWHATYOUGOT(p2);
				CROCODILEFACTOR = 0;
			}
			else
			{
				System.out.println("P1 DISQUALIFED !!!");
				p1 = copyOfACopy(p2);
				SHOWMEWHATYOUGOT(p1);
				CROCODILEFACTOR = 0;
			}
			CROCODILEFACTOR++;
			if(CROCODILEFACTOR > 200)
			{
				System.out.println("CROCODILE ACHIEEVEEEED§§§§");
				break f;
			}
		}
		System.out.println(DISQUALIFIED);
		ROSETTASTONED(p1);
		ROSETTASTONED(p2);
	}
	
	public static double[] copyOfACopy(double [] p)
	{
		double r [] = new double[6];
		for(int i = 0; i < 6; i++)
		{
			r[i] = p[i];
		}
		return r;
	}
	
	public static void ROSETTASTONED(double[] p)
	{
		System.out.println("datparam = " + Math.log(p[0]));
		System.out.println("slparam = " + Math.log(p[1]));
		System.out.println("TRPparam = "+  Math.log(p[2]));
		System.out.println("datfact = "+  Math.log(p[3]));
		System.out.println("slcfact = "+ Math.log(p[4]));
		System.out.println("TRPfact = "+ Math.log(p[5]));
	}

	public static int FIGHT(double[] p1, double[] p2)
	{
		GameBoard gb = new GameBoard();
		Move m = null;
		Joueur j1 = new Joueur(3, 0, Math.log(p1[0]), Math.log(p1[1]),Math.log(p1[2]), Math.log(p1[3]),Math.log( p1[4]), Math.log(p1[5]));
		Joueur j2 = new Joueur(3, 1, Math.log(p2[0]),Math.log( p2[1]), Math.log(p2[2]), Math.log(p2[3]), Math.log(p2[4]), Math.log(p2[5]));
		while(gb.getPossibleMoves(0).size() + gb.getPossibleMoves(1).size() > 0)
		{
			m = j1.nextPlay(m);
			if (m != null)
				gb.addCoin(m, 0);
			m = j2.nextPlay(m);
			if(m != null)
				gb.addCoin(m, 1);
		}
		return gb.getCoinCount(0) > gb.getCoinCount(1) ? 0 : 1;
	}

	public static void SHOWMEWHATYOUGOT(double[] p)
	{
		double THEWINNINGNUMBER = RANDOMIZEME.nextDouble() *2.0;
		int THEWINNINGPARAM = RANDOMIZEME.nextInt(6);
		p[THEWINNINGPARAM] *= THEWINNINGNUMBER;
		System.out.println("new params");
		ROSETTASTONED(p);
	}
}