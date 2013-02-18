
public class BowlingFrame {

	Integer Frame, Tried;
	
	public static BowlingFrame getInstanceof(Integer i, Integer j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(BowlingFrame obj) {
		// TODO Auto-generated method stub
		if( obj.Frame.equals(this.Frame) && obj.Tried.equals(this.Tried) ){ 
			return true;
		}
		return false;		// 빠른 return 문으로 else문 작성 회피 성공.
	}

}
