/**
 * @author Shebin Xu
 *	访客类	Visitor
 *
 * 1.访客预约时间点为String 格式：yyyy-mm-dd hh:mm
 */
package team.FGMSystem.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Visitor {
	private String name;			//访客姓名
	private String phoneNum;		//访客号码
	private boolean isApit;			//预约状态(is appoint) true:已来访  false:未来访
	private Date apitDate;			//预约时间(appoint date)
	private int apitDuration;		//预约时长/分钟(appoint duration)
	private String apitInf;			//预约对象或具体信息
	private String visitDate;       //访客来访时间
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	//有参构造函数
	public Visitor(String name,String phoneNum,boolean isApit,String apitDate,int apitDuration,String apitInf){
		this.name = name;
		this.phoneNum = phoneNum;
		this.isApit = isApit;
		this.apitDuration = apitDuration;
		this.apitInf = apitInf;
		
		//字符串日期输入转化为Date类型，初始化apitDate变量，输入格式(yyyy-mm-dd hh:mm)
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
			this.apitDate = sdf.parse(apitDate);
		} catch (ParseException e) {
			System.out.println("日期输入错误，请检查输入格式(yyyy-mm-dd hh:mm)");
			e.printStackTrace();
		}
	}
	//无参构造函数
	public Visitor(){
		this.apitDate = new Date();
	}

	//访客姓名get&set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//访客号码get&set
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//访客预约状态get&set
	public boolean isApit() {
		return isApit;
	}
	public void setApit(boolean isApit) {
		this.isApit = isApit;
	}
	//访客预约时间get&set
	public Date getApitDate() {
		return apitDate;
	}
	public void setApitDate(Date apitDate) {
		this.apitDate = apitDate;
	}
	//访客预约时长get&set
	public int getApitDuration() {
		return apitDuration;
	}
	public void setApitDuration(int apitDuration) {
		this.apitDuration = apitDuration;
	}
	//访客预约对象/具体信息get&set
	public String getApitInf() {
		return apitInf;
	}
	public void setApitInf(String apitInf) {
		this.apitInf = apitInf;
	}
	
	
	
}
