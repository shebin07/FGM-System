/**
 * @author Shebin Xu
 *	��ݰ�����	Package
 *
 * 1.��packType�����յ��ĺͼĳ��Ŀ�� 	s:�����send   r:�տ��receive
 * 2.����s��r��ݰ�����ͬ���в�ͬ���캯����r��ݰ���û�������ͻ��ѣ���Ϊ-1��
 */
package team.FGMSystem.entity;

public class Package {
	private String packNum;		//��ݵ���
	private String empNum;		//����Ա�����(ǩ����/�ĳ���)
	private String packType;	//�������	 s:�����send   r:�տ��receive
	private boolean isAtFG;		//�����̨״̬(��ǰ̨)	true:δ����/δǩ��	false:������/��ǩ��
	private double weight;		//�������
	private double cost;		//��ݷ���
	
	
	//���캯��
	//�ĳ��������캯�������������뻨�ѣ�
	public Package(String packNum,String empNum,String packType,boolean isAtFG,double weight,double cost){
		this.packNum = packNum;
		this.empNum = empNum;
		this.packType = packType;
		this.isAtFG = isAtFG;
		this.weight = weight;
		this.cost = cost;
	}
	//�յ��������캯���������������뻨�ѣ�����Ϊ-1��
	public Package(String packNum,String empNum,String packType,boolean isAtFG){
		this.packNum = packNum;
		this.empNum = empNum;
		this.packType = packType;
		this.isAtFG = isAtFG;
		this.weight = -1;
		this.cost = -1;
	}
	
	public Package(){       //�޲ι��캯��
	
	}
	
	//��ݵ���get&set
	public String getPackNum() {
		return packNum;
	}
	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}
	//����Ա��get&set
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	//�������get&set
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	//�����̨get&set
	public boolean isAtFG() {
		return isAtFG;
	}
	public void setAtFG(boolean isAtFG) {
		this.isAtFG = isAtFG;
	}
	//�������get&set
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	//��ݻ���get&set
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
