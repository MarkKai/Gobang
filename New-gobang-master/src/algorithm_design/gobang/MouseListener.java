package algorithm_design.gobang;

import java.awt.event.MouseEvent;

/**
 * @author kaimo
 *
 */
public class MouseListener implements java.awt.event.MouseListener {

	// member variables
	private ChessFrame chessframe;
	// set robot
	public static Robot robot = new Robot();
	
	// constructor
	public MouseListener(ChessFrame chessframe) {
		this.chessframe = chessframe;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		// capture the mouse current coordinate
		int x = Math.round((float)
				(e.getX() - ChessFrame.x)/ChessFrame.CELL_SIZE);
		int y = Math.round((float)
				(e.getY() - ChessFrame.y)/ChessFrame.CELL_SIZE);
		// make move
		if (this.chessframe.isLegal(x, y) && ChessFrame.s[x][y] == ChessFrame.EMPTY) {
			System.out.println(x+","+y);
			this.chessframe.makeMove(x, y, ChessFrame.BLACK);
			this.chessframe.repaint();
			if (this.chessframe.isEnd(x, y, ChessFrame.BLACK)) {
				System.out.println("player wins!");
				this.chessframe.init();
				this.chessframe.repaint();
			}
		} 
		
		int robotXY[] = robot.nextStep(ChessFrame.WHITE);
		System.out.println(robotXY[0]+","+robotXY[1]);
		this.chessframe.makeMove(robotXY[0], robotXY[1], ChessFrame.WHITE);
		if (this.chessframe.isEnd(robotXY[0], robotXY[1], ChessFrame.WHITE)) {
			System.out.println("robot wins!");
			this.chessframe.init();
			this.chessframe.repaint();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
