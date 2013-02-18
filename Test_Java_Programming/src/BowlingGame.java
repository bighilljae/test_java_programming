
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

	public BowlingFrame getNowFrame() {		// �۾��߿� �����غ��� getNowFrame�� getter �Լ� ���� ���̳׿�...
		// TODO Auto-generated method stub
		return gameFrame;
	}

	public ScoreFrame requireFrame(Integer Framenum) {
		Integer Score;
		Integer FirstTry = gameScoreBoard.get(BowlingFrame.getInstanceof(Framenum, 1));
		Integer SecondTry = gameScoreBoard.get(BowlingFrame.getInstanceof(Framenum, 2));
		// �׻� ��ȿ�� ��ġ�� �شٰ� ������ �� �����Ƿ� NullPointException�� �����ؾ���.
		String Result = null;
		try{
			// else �б⹮�� ���Ǿ���. ��� �ϸ� �� ������ ������� ó���� �� ������?
			// ��Ȯ�� exception ó���� �� �������� ���̰� �� ������ �ڵ带 © �� ������?
			if( FirstTry == 10 ){
				Result = "X";
			}
			else if( FirstTry + SecondTry == 10 ){
				Result = FirstTry.toString() + "/";
			}
			else{
				Result = FirstTry.toString() + SecondTry.toString();
			}
		}
		catch(NullPointerException exception){	
		}
		
		
		if( Result == null ){
			Score = -1;
			return new ScoreFrame(Framenum, Result, Score);
		}
		
		if( Result.charAt(0) == 'X' ){
			
		}
		
		return new ScoreFrame(Framenum, Result, Score);
	}
}

