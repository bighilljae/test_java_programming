
/**
 * 
 * @author Administrator
 * @see Integer
 */
public class BowlingInteger {
	public Integer Data;		/// �����ϴ� ������
	
	/**
	 * 
	 * @param param �ʱ�ȭ�ϰ����ϴ� ��ġ
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
	 * @param obj ���ϰ��� �ϴ� ������Ʈ
	 * @return ������ ����
	 */
	public boolean equals(Integer obj) {			// equals �޼ҵ� �����ε�
		// TODO Auto-generated method stub
		return Data.equals(obj);
	}
	
	/**
	 * 
	 * @return �ɼ��� 10�̾ ��Ʈ����ũ�� �����ϴ���.
	 */
	public boolean isStrike(){			// Strike���� ���� Ȯ��
		return Data == 10;
	}
	
	/**
	 * 
	 * @param obj ������ BowlingInteger ������Ʈ
	 * @return Integer ��ġ
	 */
	public Integer sum (BowlingInteger obj){		// �����ִ� �޼ҵ�
		return new Integer(Data + obj.Data);
	}
	
	/**
	 * 
	 * @param obj ������ Integer ������Ʈ
	 * @return Integer ���
	 */
	public Integer sum (Integer obj){				// �����ִ� �޼ҵ� �����ε�
		return new Integer(Data + obj);
	}
}
