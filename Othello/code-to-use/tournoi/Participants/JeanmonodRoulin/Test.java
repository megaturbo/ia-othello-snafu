package Participants.JeanmonodRoulin;
import Othello.*;
import java.lang.Math;
import java.util.ArrayList;
import Participants.JeanmonodRoulin.*;
import java.util.Random;

class Test{
	 static Random RANDOMIZEME = new Random();
	
  public static void main(String[] args)
	{
		double p1[] = new double[6];
		double p2[] = new double[6];
		for(int i = 0; i < 6; i++)
		{
			p1[i] = 1.0;
			p2[i] = 1.0;
		}
		System.out.println("starting");
		LETSDOTHISSHIT(p1, p2);
	}
	
	public static void LETSDOTHISSHIT(double[] p1, double[] p2)
	{
		SHOWMEWHATYOUGOT(p1);
		int DISQUALIFIED = -1;
		for(int i = 0; i < 1000; i++)
		{
			System.out.println("SHOWMEWHATYOUGOOOT " + i);
			DISQUALIFIED = FIGHT(p1, p2);
			if(DISQUALIFIED == 0)
			{
				System.out.println("P2 DISQUALIFED !!!");
				
				SHOWMEWHATYOUGOT(p2);
			}
			else
			{
				System.out.println("P1 DISQUALIFED !!!");
				SHOWMEWHATYOUGOT(p1);
			}
		}
		System.out.println(DISQUALIFIED);
		ROSETTASTONED(p1);
		ROSETTASTONED(p2);
	}
	
	public static void ROSETTASTONED(double[] p)
	{
		System.out.println("datparam = " + p[0]);
		System.out.println("slparam = " + p[1]);
		System.out.println("TRPparam = "+  p[2]);
		System.out.println("datfact = "+  p[3]);
		System.out.println("slcfact = "+ p[4]);
		System.out.println("TRPfact = "+ p[5]);
	}

	public static int FIGHT(double[] p1, double[] p2)
	{
		GameBoard gb = new GameBoard();
		Move m = null;
		Joueur j1 = new Joueur(5, 0, p1[0], p1[1], p1[2], p1[3], p1[4], p1[5]);
		Joueur j2 = new Joueur(5, 1, p2[0], p2[1], p2[2], p2[3], p2[4], p2[5]);
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
		double THEWINNINGNUMBER = RANDOMIZEME.nextDouble() * 6.0 - 3.0;
		int THEWINNINGPARAM = RANDOMIZEME.nextInt(6);
		p[THEWINNINGPARAM] *= THEWINNINGNUMBER;
		System.out.println("new params");
		ROSETTASTONED(p);
	}
}