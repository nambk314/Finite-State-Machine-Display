package cs260;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author cassa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class addCircle {
	private Vector<Vector<Integer>> board;
	private char turn;
	private Vector<addCircleListener> listeners;
	
	public addCircle()
	{
		board = new Vector<Vector<Integer>>();
		
		listeners = new Vector<addCircleListener>();
	}
	
	// public void placePiece(char player, int x, int y)
	// 	throws IllegalMoveException
	// {
	// 	if (player != 'X' && player != 'O') {
	// 		throw new IllegalMoveException("Unknown player " 
	// 				+ player);	
	// 	} 
	// 	if (turn != player) {
	// 		throw new IllegalMoveException("Player " 
	// 				+ player + " tried to move on player "
	// 				+ otherPlayer(player) + "'s turn.");
	// 	}
	// 	int index = getIndex(x, y);
	// 	if (board[index] != ' ') {
	// 		throw new IllegalMoveException("Tried to place a "
	// 				+ "piece in a non-empty square."); 
	// 	}
		
	// 	board[index] = player;
	// 	turn = otherPlayer(player);
		
	// 	notifyListeners();
	// }

	public void placeCircle(int xInt, int yInt) {
		Vector index = new Vector<>();

		index.add(new Integer(xInt));
		index.add(new Integer (yInt));
		board.add(index);

		notifyListeners();
	}

	public void move(int pos) {

	}
	
	public void addListener(addCircleListener l)
	{
		listeners.add(l);
	}
	
	public void removeListener(addCircleListener l)
	{
		listeners.remove(l);
	}
	
	private void notifyListeners()
	{
		for (addCircleListener l : listeners) {
			l.update();
		}
	}

	public int length() {
		return board.size();
	}

	public Vector<Integer> getPosition(int pos) {
		return board.get(pos);
	}
	
}
