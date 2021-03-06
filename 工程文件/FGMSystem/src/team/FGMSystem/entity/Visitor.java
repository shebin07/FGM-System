/**
 * @author Shebin Xu
 *	�ÿ���	Visitor
 *
 * 1.�ÿ�ԤԼʱ���ΪString ��ʽ��yyyy-mm-dd hh:mm
 */
package team.FGMSystem.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Visitor {
	private String name;			//�ÿ�����
	private String phoneNum;		//�ÿͺ���
	private boolean isApit;			//ԤԼ״̬(is appoint) true:������  false:δ����
	private Date apitDate;			//ԤԼʱ��(appoint date)
	private int apitDuration;		//ԤԼʱ��/����(appoint duration)
	private String apitInf;			//ԤԼ����������Ϣ
	private String visitDate;       //�ÿ�����ʱ��
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	//�вι��캯��
	public Visitor(String name,String phoneNum,boolean isApit,String apitDate,int apitDuration,String apitInf){
		this.name = name;
		this.phoneNum = phoneNum;
		this.isApit = isApit;
		this.apitDuration = apitDuration;
		this.apitInf = apitInf;
		
		//�ַ�����������ת��ΪDate���ͣ���ʼ��apitDate�����������ʽ(yyyy-mm-dd hh:mm)
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
			this.apitDate = sdf.parse(apitDate);
		} catch (ParseException e) {
			System.out.println("��������������������ʽ(yyyy-mm-dd hh:mm)");
			e.printStackTrace();
		}
	}
	//�޲ι��캯��
	public Visitor(){
		this.apitDate = new Date();
	}

	//�ÿ�����get&set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//�ÿͺ���get&set
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//�ÿ�ԤԼ״̬get&set
	public boolean isApit() {
		return isApit;
	}
	public void setApit(boolean isApit) {
		this.isApit = isApit;
	}
	//�ÿ�ԤԼʱ��get&set
	public Date getApitDate() {
		return apitDate;
	}
	public void setApitDate(Date apitDate) {
		this.apitDate = apitDate;
	}
	//�ÿ�ԤԼʱ��get&set
	public int getApitDuration() {
		return apitDuration;
	}
	public void setApitDuration(int apitDuration) {
		this.apitDuration = apitDuration;
	}
	//�ÿ�ԤԼ����/������Ϣget&set
	public String getApitInf() {
		return apitInf;
	}
	public void setApitInf(String apitInf) {
		this.apitInf = apitInf;
	}
	
	
	
}
