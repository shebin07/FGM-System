/**
 * @author Shebin Xu
 *	快递包裹类	Package
 *
 * 1.用packType区别收到的和寄出的快递 	s:发快递send   r:收快递receive
 * 2.根据s和r快递包裹不同，有不同构造函数，r快递包裹没有重量和花费（设为-1）
 */
package team.FGMSystem.entity;

public class Package {
	private String packNum;		//快递单号
	private String empNum;		//经手员工编号(签收人/寄出人)
	private String packType;	//快递类型	 s:发快递send   r:收快递receive
	private boolean isAtFG;		//快递在台状态(在前台)	true:未运输/未签收	false:已运输/已签收
	private double weight;		//快递重量
	private double cost;		//快递费用
	
	
	//构造函数
	//寄出包裹构造函数（包含重量与花费）
	public Package(String packNum,String empNum,String packType,boolean isAtFG,double weight,double cost){
		this.packNum = packNum;
		this.empNum = empNum;
		this.packType = packType;
		this.isAtFG = isAtFG;
		this.weight = weight;
		this.cost = cost;
	}
	//收到包裹构造函数（不包含重量与花费，均设为-1）
	public Package(String packNum,String empNum,String packType,boolean isAtFG){
		this.packNum = packNum;
		this.empNum = empNum;
		this.packType = packType;
		this.isAtFG = isAtFG;
		this.weight = -1;
		this.cost = -1;
	}
	
	public Package(){       //无参构造函数
	
	}
	
	//快递单号get&set
	public String getPackNum() {
		return packNum;
	}
	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}
	//经手员工get&set
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	//快递类型get&set
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	//快递在台get&set
	public boolean isAtFG() {
		return isAtFG;
	}
	public void setAtFG(boolean isAtFG) {
		this.isAtFG = isAtFG;
	}
	//快递重量get&set
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	//快递花费get&set
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
