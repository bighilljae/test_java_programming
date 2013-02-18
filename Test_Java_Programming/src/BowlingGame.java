
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class BowlingGame {

	private static final Exception GameOverException = null;
	public Boolean gameStatus = Boolean.FALSE;
	BowlingFrame gameFrame = new BowlingFrame();
	ArrayList<Integer> gameScoreBoard = new ArrayList<Integer>();
	
	public BowlingGame() {
		gameStatus = Boolean.TRUE;
	}

	public Integer roll(Integer pins) {
		gameScoreBoard.add(pins);
		gameFrame.FrameAddTry(pins);		// 생각 변경. Request 받을때 계산하는 것이 더 낫겠음.
		return pins;
	}

	public BowlingFrame getNowFrame() {		// 작업중에 생각해보니 getNowFrame이 getter 함수 같아 보이네요...
		// TODO Auto-generated method stub
		return gameFrame;
	}

	public ScoreFrame requireFrame(Integer Framenum) throws Exception {
		if( gameStatus.equals(Boolean.FALSE) ) throw GameOverException;
		HashMap<Integer, Integer> map = explore(Framenum);

		Integer Score = 0;
		Integer First = map.get(1);
		Integer Second = map.get(2);
		Integer lastIndex = map.get(0);
		String Result = "";
		try{
			Result = First.toString();
			if( First == 10 ) Result = "X";
			Result += Second.toString();
			if( First + Second == 10 ) Result = First.toString() + "/";
			
		}catch(Exception e){
		}
		

		try {
			if (First == 10) {
				if (gameScoreBoard.size() - 1 >= lastIndex + 2) {
					Score = 10 + gameScoreBoard.get(lastIndex + 1)
							+ gameScoreBoard.get(lastIndex + 2);
				}
			} else if (First + Second == 10) {
				if (gameScoreBoard.size() - 1 >= lastIndex + 1) {
					Score = 10 + gameScoreBoard.get(lastIndex + 1);
				}
			} else {
				Score = First + Second;
			}
		} catch (Exception e) {

		}
		return new ScoreFrame(Framenum, Result, Score);
	}

	private HashMap<Integer, Integer> explore(Integer framenum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 1, tried = 1;
		for( int i = 0 ; i < gameScoreBoard.size() ; i ++ ){
			if( count == framenum ){
				map.put(tried, gameScoreBoard.get(i));
				map.put(0, i); 			// Key 0 : EndCount
			}
			
			if( gameScoreBoard.get(i) == 10 ){
				count++; tried = 1;
				continue;
			}
			count += tried / 2;
			tried = tried % 2 + 1;
		}
		return map;
	}
}

