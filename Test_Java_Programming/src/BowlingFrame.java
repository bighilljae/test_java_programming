
public class BowlingFrame {

	Integer Frame = 1, Tried= 1;
	
	public static BowlingFrame getInstanceof(Integer i, Integer j) {
		// TODO Auto-generated method stub
		BowlingFrame tempInstance = new BowlingFrame();
		tempInstance.Frame = i; tempInstance.Tried = j;
		return tempInstance;
	}
	
	public boolean equals(BowlingFrame obj) {
		// TODO Auto-generated method stub
		if( obj.Frame.equals(this.Frame) && obj.Tried.equals(this.Tried) ){ 
			return true;
		}
		return false;		// ���� return ������ else�� �ۼ� ȸ�� ����.
	}
	
	public void FrameAddTry(Integer pins){
		if( Frame == 10 && pins.equals(10) ){
			Tried++;
		}
		if( pins.equals(10) ){ 
			Frame++; 
			Tried = 1; 
			return;				// 1ȸ�� 0��, 2ȸ�� 10 ����, 1ȸ�� ��Ʈ����ũ ������, Frame �ѱ�� return���� else�� ȸ��.
		}
		
		Frame += (Tried / 2);
		Tried = Tried % 2 + 1;
		// 2ȸ�� Add�� ����Ǹ� Frame�� 1 ����    1ȸ�� ������ ������ ����
		// 2ȸ°�� Tried�� ����Ǹ� Tried�� 1�� ���ư�. 1ȸ°���� 2�� ������.
	}
}
