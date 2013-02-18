
public class BowlingFrame {

	Integer Frame = 1, Tried= 1;
	
	/**
	 * 
	 * @param frame frame 넘버
	 * @param tried 시행횟수
	 * @return BowlingFrame에 frame 에 시행횟수 적용된 인스턴스
	 */
	public static BowlingFrame getInstanceof(Integer frame, Integer tried) {
		// TODO Auto-generated method stub
		BowlingFrame tempInstance = new BowlingFrame();
		tempInstance.Frame = frame; tempInstance.Tried = tried;
		return tempInstance;
	}
	
	/**
	 * 
	 * @param obj 비교하고자 하는 오브젝트
	 * @return 같은지 여부.
	 */
	public boolean equals(BowlingFrame obj) {
		// TODO Auto-generated method stub
		if( obj.Frame.equals(this.Frame) && obj.Tried.equals(this.Tried) ){ 
			return true;
		}
		return false;		// 빠른 return 문으로 else문 작성 회피 성공.
	}
	
	/**
	 * 
	 * @param pins 핀 하나가 추가 되어서 상태 변화.
	 */
	public void FrameAddTry(Integer pins){
		if( Frame == 10 && pins.equals(10) ){
			Tried++;
		}
		if( pins.equals(10) ){ 
			Frame++; 
			Tried = 1; 
			return;				// 1회에 0번, 2회에 10 스페어나, 1회에 스트라이크 성공시, Frame 넘기고 return으로 else문 회피.
		}
		
		Frame += (Tried / 2);
		Tried = Tried % 2 + 1;
		// 2회에 Add가 시행되면 Frame이 1 증가    1회에 증가시 증가량 없음
		// 2회째에 Tried가 시행되면 Tried는 1로 돌아감. 1회째에는 2로 증가함.
	}
}
