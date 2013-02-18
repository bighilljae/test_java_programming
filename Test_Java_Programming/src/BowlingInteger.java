
/**
 * 
 * @author Administrator
 * @see Integer
 */
public class BowlingInteger {
	public Integer Data;		/// 보유하는 데이터
	
	/**
	 * 
	 * @param param 초기화하고자하는 수치
	 */
	public BowlingInteger(Integer param) {
		Data = param;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if( Data == 10 ) return "X";
		if( Data == 0 ) return "-";
		return Data.toString();
	}
	
	/**
	 * 
	 * @param obj 비교하고자 하는 오브젝트
	 * @return 같은지 여부
	 */
	public boolean equals(Integer obj) {			// equals 메소드 오버로딩
		// TODO Auto-generated method stub
		return Data.equals(obj);
	}
	
	/**
	 * 
	 * @return 핀수가 10이어서 스트라이크를 만족하는지.
	 */
	public boolean isStrike(){			// Strike인지 여부 확인
		return Data == 10;
	}
	
	/**
	 * 
	 * @param obj 더해줄 BowlingInteger 오브젝트
	 * @return Integer 수치
	 */
	public Integer sum (BowlingInteger obj){		// 더해주는 메소드
		return new Integer(Data + obj.Data);
	}
	
	/**
	 * 
	 * @param obj 더해줄 Integer 오브젝트
	 * @return Integer 결과
	 */
	public Integer sum (Integer obj){				// 더해주는 메소드 오버로딩
		return new Integer(Data + obj);
	}
}
