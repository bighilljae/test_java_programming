
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

	/**
	 * 
	 * @param pins 쓰러뜨린 pin 수
	 * @return parmeter 그대로 리턴.
	 */
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

	/**
	 * 
	 * @return 현재 입력이 들어갈 Frame과 시행 횟수
	 * @throws Exception GameOverException
	 */
	public BowlingFrame getNowFrame() throws Exception {		// 작업중에 생각해보니 getNowFrame이 getter 함수 같아 보이네요...
		if( gameStatus.equals(Boolean.FALSE)) throw GameOverException;
		return gameFrame;
	}
	
	/**
	 * 
	 * @param Framenum 구하고자 하는 Frame number
	 * @return 결과 Frame
	 * @throws Exception GameOverException
	 */
	public ScoreFrame requireFrame(Integer Framenum) throws Exception {		// Frame 번호를 넣어주면 Frame 정보를 (5,"52",7) 형태로 보내줌.
		if( gameStatus.equals(Boolean.FALSE) ) throw GameOverException;
		HashMap<Integer, Integer> map = explore(Framenum);

		Integer Score = 0;
		BowlingInteger First = new BowlingInteger(map.get(1));			// BowlingInteger : 10 일경우 X를 , 0일경우는 -를 toString으로 넘겨줌
		BowlingInteger Second = new BowlingInteger(map.get(2));
		Integer lastIndex = map.get(0);
		
		String Result = "";
		try{									// First가 null이 아니라면 Result String에 First를 추가해줌.

			Result = First.toString();
			
			if( !(First.isStrike()) && First.sum(Second) == 10 ) Result += "/";		// 스페어 처리 성공시
			else Result += Second.toString();								// 스페어 처리 실패시 
			
			if( Framenum == 10 && (First.isStrike() || First.sum(Second) == 10) ){		// 10번 Frame에서 스트라이크나, 스페어 발생시 한번 더 roll 할 수 있음.
				
				BowlingInteger LastPin = new BowlingInteger(gameScoreBoard.get(lastIndex + 1)); 		// 마지막 데이터를 가져오는데 시도한다. 없으면 NullPointExcpetion으로 알아서 빠져나옴.
				if(First.isStrike() && !Second.isStrike() && Second.sum(LastPin) == 10 ) Result += "/"; // 10번 Frame에서 첫번째 스트라이크 치고 2,3번째 쳐서 스페어 처리시.
				else Result += LastPin.toString();							// 스트라이크도, 스페어도 아니면 숫자 새로 더해줌.
				
				Score = First.sum(Second.sum(LastPin));						// LastPin이 존재할 경우 점수 계산 바로 해서 Return. 아래 코드에서 계산하지 않음.
				return new ScoreFrame(Framenum, Result, Score);
			}
			
		}catch(Exception e){}

		try {
			if (First.isStrike()) {// 스트라이크 성공시 다음 두 숫자가 있는 경우 더한다. 만약 없으면 계산할 수 없음.
				Score = 10 + gameScoreBoard.get(lastIndex + 1) + gameScoreBoard.get(lastIndex + 2);
			}
			else if (First.sum(Second) == 10) { // 스페어 처리 성공시 다음 숫자 하나가 있는 경우더해준다.
				Score = 10 + gameScoreBoard.get(lastIndex + 1);
			} 
			else {	// 스페어 실패시 두 숫자만 더한다.
				Score = First.sum(Second); 
			}
		} catch (Exception e) {
			Score = 0;			// Exception 발생 : 숫자가 없음
		}
		return new ScoreFrame(Framenum, Result, Score); // Score는 게산 안되는 경우 항상
														// 0이고, Result 역시 중간에
														// 끊기면 중간에 끊기는 만큼만 결과값.
	}

	/**
	 * 
	 * @param framenum 탐색하고자 하는 frame 넘버
	 * @return key-value로 연결된 HaspMap 데이터
	 */
	private HashMap<Integer, Integer> explore(Integer framenum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int frame = 1, tried = 1;									// Frame 추적하는 변수 두개
		for( int i = 0 ; i < gameScoreBoard.size() ; i ++ ){
			if( frame == framenum ){
				map.put(tried, gameScoreBoard.get(i));
				map.put(0, i); 			// Key 0 : Endframe 스페어 발생 또는 스트라이크 발생시 다음 숫자를 찾기 위한 Frame의 끝 부분.
			}
			
			if( gameScoreBoard.get(i) == 10 && frame != 10 ){		// 10번쨰 Frame이 아니라면 스트라이크 발생시 시행 하나 건너뜀.
				frame++; tried = 1;
				continue;
			}
			frame += tried / 2; tried = tried % 2 + 1;			// 시행 한번 이후에 몇 번째 Frame이고, 몇번째 시행인지 10번째 Frame에서 tried가 3으로 증가하면 위쪽에서 처리가 쉬우나 적절한 계산식이 없음
		}
		return map;
	}
}

