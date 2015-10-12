package Participants.....;

import java.util.ArrayList;

import Othello.Move;
/***
 * TreeNode containing a Game state with his evaluation and his child nodes (possible actions at the current state)
 * @author Ellenberger Patrick and Moll Adrian
 *
 */
public class Node {
	
	private ArrayList<Node> childNodeList;
	private int evaluation;
	private Move move;
	public String name;
	/**
	 * 
	 * @param evaluation evaluation of this state
	 * @param move movement achieved to reach this state
	 */

	private static int cmpt = 0;
	public Node(Move move)
	{
		this.evaluation = 0;
		this.move = move;
		childNodeList = new ArrayList<Node>();
		
		
		this.name = "e"+(++cmpt);
		// Calculate initial evaluation
	}
	
	public void setEvaluation(int evaluation)
	{
		this.evaluation = evaluation;
	}	
	public int getEvaluation()
	{
		return evaluation;
	}
	
	public Move getMove()
	{
		return move;
	}
	
	public void setMove(Move move)
	{
		this.move = move;
	}
	
	public ArrayList<Node> getChildNodeList()
	{
		return childNodeList;
	}
	
	/***
	 * Adding a movement to the current state
	 * @param childNode possible movement at the current state
	 */
	public void addChildNode(Node childNode)
	{
		this.childNodeList.add(childNode);
	}
	
	/**
	 * Test if Node is a leaf node
	 * @return
	 */
	public boolean isLeaf()
	{
		return childNodeList.size()==0;
	}

}
