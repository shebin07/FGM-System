/**
 * @author Shebin Xu
 *	Ա����	Employee
 *
 * 1.���캯����entryDateΪString,��ʽΪyyyy-mm-dd
 */
package team.FGMSystem.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Employee {
	private String empNum;			//Ա�����
	private String name;			//Ա������
	private String phoneNum;		//Ա������
	private String job;				//Ա��ְλ
	private String department;		//Ա������
	private String entryDate;	    //Ա����ְ����
	private String nowDate;         //����Ա��ǩ������
	private String in_time;          //����Ա��ǩ��ʱ��
	private String out_time;        //����Ա��ǩ��ʱ��
	
	private int sign_in_sum;       //ǩ���ܴ���
	private int overtime_sum;    //�Ӱ��ܴ���
	
	
	
	public int getSign_in_sum() {
		return sign_in_sum;
	}
	public void setSign_in_sum(int sign_in_sum) {
		this.sign_in_sum = sign_in_sum;
	}
	public int getOvertime_sum() {
		return overtime_sum;
	}
	public void setOvertime_sum(int overtime_sum) {
		this.overtime_sum = overtime_sum;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getOut_time() {
		return out_time;
	}
	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}
	//�вι��캯��
	public Employee(String empNum,String name,String phoneNum,String job,String department,String entryDate){
		this.empNum	= empNum;
		this.name = name;
		this.phoneNum = phoneNum;
		this.job = job;
		this.department = department;
		this.entryDate = entryDate;
//		//�ַ�����������ת��ΪDate���ͣ���ʼ��entryDate�����������ʽ(yyyy-mm-dd)
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//			this.entryDate = sdf.parse(entryDate);
//		} catch (ParseException e) {
//			System.out.println("��������������������ʽ(yyyy-mm-dd)");
//			e.printStackTrace();
//		}
	}
	//�޲ι��캯��
	public Employee(){
		//this.entryDate = new Date();
	}
	
	//Ա�����get&set
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	//Ա������get&set
		public String getName() {
			return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//Ա������get&set
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//Ա��ְλget&set
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	//Ա������get&set
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	////Ա����ְ����get&set
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
