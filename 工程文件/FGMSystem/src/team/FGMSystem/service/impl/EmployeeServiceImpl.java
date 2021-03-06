/**
 * @author 
 *	xx类	xxx
 *
 */
package team.FGMSystem.service.impl;
import java.util.*;
import team.FGMSystem.entity.*;
import team.FGMSystem.service.EmployeeService;
import team.FGMSystem.dao.*;
import team.FGMSystem.dao.impl.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService {
	

	/**
	 * 员工登录服务
	 */
	@Override
	public Object login(String account, String password) {
		String sql="select * from signin where empNum=? and password=?";
		String[] param={account,password};
		EmployeeServiceDao employeeDao=new EmployeeServiceDaoImpl();
		Object isLogin=employeeDao.login(sql, param);
		return isLogin;
	}

	/**
	 * 员工签到服务
	 */
	@Override
	public void signIn(Employee emp) {
		String sql="insert into clock values(?,?,?,null)";
		String [] param={emp.getEmpNum(),emp.getNowDate(),emp.getIn_time()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		boolean is_signIn=employeeDao.insertSignIn(sql, param);
		if(is_signIn==true){
			System.out.println("签到成功！签到时间为:"+emp.getNowDate()+"\t"+emp.getIn_time());
			sql="update employee set sign_in_sum=sign_in_sum+1 where employee_id=?";
			String[] sparam={emp.getEmpNum()};
			employeeDao.executeSQL(sql,sparam);
		}else{
			System.out.println("签到失败！");
			return;
		}
	}

	/**
	 * 员工签退服务，超过20点以后视为加班
	 */
	@Override
	public void signOut(Employee emp) {
		String sql="update clock set clock_out_time=? where employee_id=? and date=?";
		String[] param={emp.getOut_time(),emp.getEmpNum(),emp.getNowDate()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		boolean is_signOut=employeeDao.insertSignOut(sql, param);
		if(is_signOut==true){
			System.out.println("签退成功！签退时间为:"+emp.getNowDate()+"\t"+emp.getOut_time());
			String temp="20:00:00";           //加班
			if(emp.getOut_time().compareTo(temp)>=0){
				sql="update employee set overtime_sum=overtime_sum+1 where employee_id=?";
				String[] sparam={emp.getEmpNum()};
				employeeDao.executeSQL(sql,sparam);
			}
		
		}else{
			System.out.println("签退失败！");
			return;
		}
	}
	
	/**
	 * 员工查询当月出勤情况
	 * @param emp
	 */
	@Override
	public void queryAttendance(Employee emp) {
		String sql="select * from clock where employee_id=?";
		String[] param={emp.getEmpNum()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		List<Employee> staffList=new ArrayList<Employee>();
		Employee staff=new Employee();
		staffList=employeeDao.queryAttendance(sql, param,1);   //index为1查询表clock
		if(staffList!=null){
			System.out.println("当月出勤情况为:");
			for(int i=0;i<staffList.size();i++){
				staff=staffList.get(i);
				System.out.println(staff.getNowDate()+"\t"+staff.getIn_time()+"\t"+staff.getOut_time());
				
			}
		}
		
	}

	/**
	 * 员工查询历史出勤情况
	 * @param emp
	 * @param date
	 */
	@Override
	public void queryAttendance(Employee emp, String time) {
		String sql="select * from employee natural join clock where employee_id=?";
		String[] param={emp.getEmpNum()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		List<Employee> staffList=new ArrayList<Employee>();
		Employee staff=new Employee();
		staffList=employeeDao.queryAttendance(sql, param,1);   //index为1查询表clock
		if(staffList!=null){
			System.out.println("该月出勤情况为：");
			for(int i=0;i<staffList.size();i++){
				staff=staffList.get(i);
				String str1 = staff.getNowDate().substring(0,7);
				if(str1.equals(time)){
					System.out.println(staff.getNowDate()+"\t"+staff.getIn_time()+"\t"+staff.getOut_time());
				}
				//System.out.println("历史出勤情况为:\n"+staff.getName()+"\t"+staff.getEmpNum()+"\t"+staff.getDepartment()
				//+staff.getJob()+staff.getEntryDate()+staff.getSign_in_sum());
				
			}
		}
		
	}

	/**
	 * 员工查询当月加班情况
	 * @param emp
	 */
	@Override
	public void queryWorkOverTime(Employee emp) {
		String sql="select * from clock where employee_id=?";
		String[] param={emp.getEmpNum()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		List<Employee> staffList=new ArrayList<Employee>();
		Employee staff=new Employee();
		staffList=employeeDao.queryAttendance(sql, param,1);   //index为1查询表clock
		if(staffList!=null){
			System.out.println("当月加班情况为:");
			for(int i=0;i<staffList.size();i++){
				staff=staffList.get(i);
				if(staff.getOut_time().compareTo("20:00:00")>=0){
					System.out.println(staff.getNowDate()+"\t"+staff.getIn_time()+"\t"+staff.getOut_time());
				}
			}
		}else{
			System.out.println("当月无加班记录");
		}
		
	}

	/**
	 * 员工查询历史加班情况
	 * @param emp
	 * @param date
	 */
	@Override
	public void queryWorkOverTime(Employee emp, String time) {
		String sql="select * from clock where employee_id=?";
		String[] param={emp.getEmpNum()};
		EmployeeServiceDaoImpl employeeDao=new EmployeeServiceDaoImpl();
		List<Employee> staffList=new ArrayList<Employee>();
		Employee staff=new Employee();
		staffList=employeeDao.queryAttendance(sql, param,1);   //index为1查询表clock
		if(staffList!=null){
			System.out.println("当月加班情况为：");
			for(int i=0;i<staffList.size();i++){
				staff=staffList.get(i);
				String str1 = staff.getNowDate().substring(0,7);
				
				if(staff.getOut_time()!=null && staff.getOut_time().compareTo("20:00:00")>=0 &&str1.equals(time)){
					//System.out.println("历史加班情况为:\n"+staff.getName()+"\t"+staff.getEmpNum()+"\t"+staff.getDepartment()
					//+staff.getJob()+staff.getEntryDate()+staff.getOvertime_sum());
					System.out.println(staff.getNowDate()+"\t"+staff.getIn_time()+"\t"+staff.getOut_time());
				}
			}
		}else{
			System.out.println("当月无加班记录");
		}
		
	}

	/**
	 * 员工签收快递
	 * @param emp
	 * @param packNum
	 */
//	@Override
//	public void empReceivePackage(Employee emp, String packNum) {
//		// TODO Auto-generated method stub
//		PackageServiceImpl packageService=new PackageServiceImpl();
//		packageService.ReceivePackage(emp, packNum);//调用PackageServiceImpl中的签收快递函数
//	}

	/**
	 * 员工寄快递
	 * 自动生成快递单号yyyymmddhhmmss
	 * @param emp
	 */
//	@Override
//	public void empSendPackage(Employee emp) {
//		// TODO Auto-generated method stub
//		PackageServiceImpl packageService=new PackageServiceImpl();
//		packageService.expressTakePack(emp);//调用PackageServiceImpl中的寄快递函数
//	}

}
