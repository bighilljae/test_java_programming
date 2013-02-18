
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class BowlingGame {

	public static final Exception GameOverException = new Exception("GameOverException");
	public Boolean gameStatus = Boolean.FALSE;
	BowlingFrame gameFrame = new BowlingFrame();
	ArrayList<Integer> gameScoreBoard = new ArrayList<Integer>();
	
	public BowlingGame() {
		gameStatus = Boolean.TRUE;			// 게임 시작을 알림.
	}

	public Integer roll(Integer pins) {
		
		gameScoreBoard.add(pins);			// List에 쓰러뜨린 pin수 추가
		gameFrame.FrameAddTry(pins);		// 현재 Frame 위치 갱신
		if( gameFrame.Frame == 10 ){		// 10번째 frame에서
			Integer LastPrevious = gameScoreBoard.get(gameScoreBoard.size()-2);		// 마지막 쓰러뜨린 pin수와 그 전에 쓰러뜨린 pin수를 가져와서
			Integer Last = pins;
			if(gameFrame.Tried == 4 || gameFrame.Tried==3 && Last + LastPrevious < 10 ){ 		// 10번째 Frame에서 세번째 시도를 이미 시행했거나, 2번째 시행이 이루어졌고, 스트라이크나 스페어 처리가 안되면 게임 종료
				gameStatus = Boolean.FALSE;
			}
		}
		return pins;
	}

	public BowlingFrame getNowFrame() throws Exception {		// 작업중에 생각해보니 getNowFrame이 getter 함수 같아 보이네요...
		if( gameStatus.equals(Boolean.FALSE)) throw GameOverException;
		return gameFrame;
	}

	public ScoreFrame requireFrame(Integer Framenum) throws Exception {		// Frame 번호를 넣어주면 Frame 정보를 (5,"52",7) 형태로 보내줌.
		if( gameStatus.equals(Boolean.FALSE) ) throw GameOverException;
		HashMap<Integer, Integer> map = explore(Framenum);

		Integer Score = 0;
		Integer First = map.get(1);
		Integer Second = map.get(2);
		Integer lastIndex = map.get(0);
		String Result = "";
		try{									// First가 null이 아니라면 Result String에 First를 추가해줌.
			if( First == 10 ) Result = "X";		// 첫번째 시도가 10 이라면 스트라이크
			else Result = First.toString();		// 아니라면 숫자 보여줌.
			
			if( First != 10 && First + Second == 10 ) Result += "/";		// 스페어 처리 성공시
			else if( First == 10 && Second == 10 ) Result += "X";			// 10Frame에서만 발생 가능한 경우. First도 스트라이크, Second도 스트라이크
			else Result += Second.toString();								// 스페어 처리 실패시 
			
			if( Framenum == 10 && (First == 10 || First+Second == 10) ){		// 10번 Frame에서 스트라이크나, 스페어 발생시 한번 더 roll 할 수 있음.
				
				Integer LastPin = gameScoreBoard.get(lastIndex + 1); 		// 마지막 데이터를 가져오는데 시도한다. 없으면 NullPointExcpetion으로 알아서 빠져나옴.
				if( LastPin == 10 )	Result += "X";									// 마지막에 10을 치면 스트라이크			
				else if(First == 10 && Second != 10 && Second + LastPin == 10 ) Result += "/"; // 10번 Frame에서 첫번째 스트라이크 치고 2,3번째 쳐서 스페어 처리시.
				else Result += LastPin.toString();							// 스트라이크도, 스페어도 아니면 숫자 새로 더해줌.
				
				Score = First + Second + LastPin;						// LastPin이 존재할 경우 점수 계산 바로 해서 Return. 아래 코드에서 계산하지 않음.
				return new ScoreFrame(Framenum, Result, Score);
			}
			
		}catch(Exception e){
		}
		

		try {
			if (First == 10) {
				if (gameScoreBoard.size() - 1 >= lastIndex + 2) {		// 스트라이크 성공시 다음 두 숫자가 있는 경우 더한다. 만약 없으면 계산할 수 없음.
					Score = 10 + gameScoreBoard.get(lastIndex + 1)
							+ gameScoreBoard.get(lastIndex + 2);
				}
			} else if (First + Second == 10) {							// 스페어 처리 성공시 다음 숫자 하나가 있는 경우 더해준다.
				if (gameScoreBoard.size() - 1 >= lastIndex + 1) {
					Score = 10 + gameScoreBoard.get(lastIndex + 1);
				}
			} else {
				Score = First + Second;								// 스페어 실패시 두 숫자만 더한다.
			}
		} catch (Exception e) {
			Score = 0;
		}
		return new ScoreFrame(Framenum, Result, Score);			// Score는 게산 안되는 경우 항상 0이고, Result 역시 중간에 끊기면 
	}

	private HashMap<Integer, Integer> explore(Integer framenum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 1, tried = 1;
		for( int i = 0 ; i < gameScoreBoard.size() ; i ++ ){
			if( count == framenum ){
				map.put(tried, gameScoreBoard.get(i));
				map.put(0, i); 			// Key 0 : EndCount
			}
			
			if( gameScoreBoard.get(i) == 10 && count != 10 ){
				count++; tried = 1;
				continue;
			}
			count += tried / 2;
			tried = tried % 2 + 1;
		}
		return map;
	}
}

