import java.util.HashMap;


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

	public BowlingFrame getNowFrame() {		// �۾��߿� �����غ��� getNowFrame�� getter �Լ� ���� ���̳׿�...
		// TODO Auto-generated method stub
		return gameFrame;
	}
}

