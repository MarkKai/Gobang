package algorithm_design.gobang;
import java.util.Random;

/**
 * @author kaimo
 * @author Quan Liu
 * @author Xiaoqian Xu
 * @author Tianqi Xie
 * @author Haochen Wei
 *
 */
public class Robot {
	
//	private static ChessBoard chess = ChessBoard();
	private static int SEARCH_DEPTH = 1;
	private static int ROBOT_COLOR = ChessFrame.WHITE;
	private static ChessFrame chessframe = new ChessFrame();
	
	// constructor
	public Robot() {};
	
	// alpha-beta pruning search
	public int alphaBeta(int depth, int alpha, int beta,
			int color, int currX, int currY) {
		// the condition to break recursion
		// search depth meet || game over
		if (depth >= SEARCH_DEPTH || chessframe.isEnd(currX, currY, -color)) {
			int score = chessframe.reckon(ROBOT_COLOR) - chessframe.reckon(-ROBOT_COLOR);
			if (depth % 2 == 0) {
				score = -score;
			}
			return score;
		}
		
		// recursively
		// but only in search boundary
		for (int x = ChessFrame.SEARCH[0]; x <= ChessFrame.SEARCH[2]; x++) {
			for (int y = ChessFrame.SEARCH[1]; y <= ChessFrame.SEARCH[3]; y++) {
				
				if (!chessframe.isEmpty(x, y)) {
					continue;
				}
				chessframe.makeMove(x, y, color);
				int score = -alphaBeta(depth+1, -beta, -alpha, -color, x, y);
				chessframe.unMove(x, y);
				
				// pruning
				if (score >= beta) {
					return beta;
				}
				// update alpha
				if (score > alpha) {
					alpha = score;
				}
				
			}
		}
		
		return alpha;
	}
	
	/**
	 *  get the next step for robot
	 */
	public int[] nextStep(int color) {
		
		int corr[] = new int[2];
		int scoreMax = -100000000;
		Random random = new Random();

		// we begin with search boundary
		for (int x = ChessFrame.SEARCH[0]; x <= ChessFrame.SEARCH[2]; x++) {
			for (int y = ChessFrame.SEARCH[1]; y <= ChessFrame.SEARCH[3]; y++) {
				
				if (!chessframe.isEmpty(x, y)) {
					continue;
				}
				chessframe.makeMove(x, y, color);
				int score = -alphaBeta(0, -100000000, 100000000, -color, x, y);
				int random_state = random.nextInt();
				if (score > scoreMax ||
						(score == scoreMax && random_state >= 50)) {
					scoreMax = score;
					corr[0] = x;
					corr[1] = y;
				}
				chessframe.unMove(x, y);
			}
		}
		
		return corr;
	}
}
