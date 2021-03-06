/**
 * @author Shebin Xu
 *	员工类	Employee
 *
 * 1.构造函数中entryDate为String,格式为yyyy-mm-dd
 */
package team.FGMSystem.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Employee {
	private String empNum;			//员工编号
	private String name;			//员工姓名
	private String phoneNum;		//员工号码
	private String job;				//员工职位
	private String department;		//员工部门
	private String entryDate;	    //员工入职日期
	private String nowDate;         //当天员工签到日期
	private String in_time;          //当天员工签到时间
	private String out_time;        //当天员工签退时间
	
	private int sign_in_sum;       //签到总次数
	private int overtime_sum;    //加班总次数
	
	
	
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
	//有参构造函数
	public Employee(String empNum,String name,String phoneNum,String job,String department,String entryDate){
		this.empNum	= empNum;
		this.name = name;
		this.phoneNum = phoneNum;
		this.job = job;
		this.department = department;
		this.entryDate = entryDate;
//		//字符串日期输入转化为Date类型，初始化entryDate变量，输入格式(yyyy-mm-dd)
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//			this.entryDate = sdf.parse(entryDate);
//		} catch (ParseException e) {
//			System.out.println("日期输入错误，请检查输入格式(yyyy-mm-dd)");
//			e.printStackTrace();
//		}
	}
	//无参构造函数
	public Employee(){
		//this.entryDate = new Date();
	}
	
	//员工编号get&set
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	//员工姓名get&set
		public String getName() {
			return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//员工号码get&set
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//员工职位get&set
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	//员工部门get&set
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	////员工入职日期get&set
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
