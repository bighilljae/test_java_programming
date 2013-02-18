
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

	public ScoreFrame requireFrame(Integer Framenum) {
		Integer Score;
		Integer FirstTry = gameScoreBoard.get(BowlingFrame.getInstanceof(Framenum, 1));
		Integer SecondTry = gameScoreBoard.get(BowlingFrame.getInstanceof(Framenum, 2));
		// 항상 유효한 수치만 준다고 가정할 수 없으므로 NullPointException에 대응해야함.
		String Result = null;
		try{
			// else 분기문이 사용되었음. 어떻게 하면 더 깨끗한 방법으로 처리할 수 있을까?
			// 명확한 exception 처리로 더 가독성을 높이고 더 안전한 코드를 짤 수 없을까?
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

