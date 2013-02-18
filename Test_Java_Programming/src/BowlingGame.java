
import java.util.HashMap;
import java.util.LinkedList;


public class BowlingGame {

	public Boolean gameStatus = Boolean.FALSE;
	BowlingFrame gameFrame = new BowlingFrame();
	HashMap<BowlingFrame, Integer> gameScoreBoard = new HashMap<>();
	
	public BowlingGame() {
		gameStatus = Boolean.TRUE;
	}

	public Integer roll(Integer pins) {
		gameFrame.FrameAddTry(pins);
		return pins;
	}

	public BowlingFrame getNowFrame() {		// 작업중에 생각해보니 getNowFrame이 getter 함수 같아 보이네요...
		// TODO Auto-generated method stub
		return gameFrame;
	}

	public LinkedList<ScoreFrame> getScoreBoard() {
		// TODO Auto-generated method stub
		return null;
	}
}

