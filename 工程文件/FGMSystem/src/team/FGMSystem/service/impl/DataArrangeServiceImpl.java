/**
 * @author 1.
 * 		   2. Shebin queryAttendance()
 * 					 queryWorkOverTime()
 *	数据分析服务实现类	DataArrangeServiceImpl
 *
 */
package team.FGMSystem.service.impl;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Visitor;
import team.FGMSystem.service.DataArrangeService;
import team.FGMSystem.dao.impl.*;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import team.FGMSystem.dao.*;

public class DataArrangeServiceImpl implements DataArrangeService {
	private DataArrangeServiceDao dataArrangeServiceDao;

	/**
	 * 查询员工出勤情况
	 * @param emp
	 * @param date
	 */
	@Override
	public void queryEmpAttendance(String date) {
		String sql = "select* from clock where date=?";
		String[] param={date};
		DataArrangeSericeDaoImpl dataArrangeServiceDao=new DataArrangeSericeDaoImpl();
		List<Employee> empList=dataArrangeServiceDao.queryEmpAttendance(sql, param);
		Employee emp=new Employee();
		 if(empList!=null&&empList.size()!=0){
				System.out.println(date+"的公司出勤情况为：");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//String str1=emp.getVisitDate().substring(0,7);
				    System.out.println("员工编号："+emp.getEmpNum()+"\t"+"签到时间："+emp.getIn_time()+"\t签退时间:"+emp.getOut_time());
					}
				}
		 }
	
	

	/**
	 * 查询员工加班情况
	 * @param emp
	 * @param date
	 */
	@Override
	public void queryEmpWorkOverTime(String date) {
		String sql = "select* from clock where date=?";
		String[] param={date};
		DataArrangeSericeDaoImpl dataArrangeServiceDao=new DataArrangeSericeDaoImpl();
		List<Employee> empList=dataArrangeServiceDao.queryEmpWorkOverTime(sql, param);
		Employee emp=new Employee();
		 if(empList!=null&&empList.size()!=0){
				System.out.println(date+"的公司加班情况为：");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//String str1=emp.getVisitDate().substring(0,7);
				    System.out.println("员工编号："+emp.getEmpNum()+"\t"+"签到时间："+emp.getIn_time()+"\t签退时间:"+emp.getOut_time());
					}
				}
		
	}

	/**
	 * 查询全勤情况
	 * @param date
	 */
	@Override
	public void queryAttendance(String date) {
		String sql = "SELECT employee_id,NAME,department,count(date) as sum from clock natural join employee where (clock_in_time LIKE '07:%' OR clock_in_time='08:00:00') AND YEAR(DATE)=? AND MONTH(DATE)=? group by employee_id,NAME,department";
		
		//获取月份天数
		Calendar cal = Calendar.getInstance();   
		cal.set(Calendar.YEAR,Integer.parseInt(date.substring(0,4))); //设置年
		cal.set(Calendar.MONTH,Integer.parseInt(date.substring(5,7))-1);//月   
		int maxDate = cal.getActualMaximum(Calendar.DATE);
				
		String[] param={date.substring(0,4),date.substring(5,7)};
		DataArrangeServiceDao dataArrangeServiceDao = new DataArrangeSericeDaoImpl();
		Employee emp = new Employee();
		int cou_all=0,cou_part=0;
		List<Employee> empList = dataArrangeServiceDao.queryAttendance(sql,param);
		
		
		 if(empList!=null&&empList.size()!=0){
			 System.out.println("该月出勤情况：");
			 System.out.println("全勤名单：");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//计算人数
					if(emp.getSign_in_sum()==maxDate){
						cou_all++; 
						System.out.println(emp.getEmpNum()+"\t"+emp.getName()+"\t"+emp.getDepartment());
					}else cou_part++;
				}
		 }
		 NumberFormat nf = NumberFormat.getPercentInstance();
		 nf.setMinimumFractionDigits(2);
		 System.out.println("-全勤人数："+cou_all);
		 System.out.println("-非全勤人数："+cou_part);
		 System.out.println("-全勤人数占总出勤人数比："+nf.format(cou_all*1.0/(cou_part+cou_all)));
	}

	/**
	 * 查询访客信息
	 * @param vis
	 */
	@Override
	public void queryVisitInf(String time) {
		DataArrangeSericeDaoImpl dataArrangeServiceDao=new DataArrangeSericeDaoImpl();
		String sql="SELECT * FROM visit WHERE visitDate IS NOT NULL";
		List<Visitor> vsList=dataArrangeServiceDao.queryVisitInf(sql, null);
		Visitor vs=new Visitor();
		int count=0;
		 if(vsList!=null&&vsList.size()!=0){
				System.out.println("该月访客信息：");
				for(int i=0;i<vsList.size();i++){
					vs=vsList.get(i);
					String str1=vs.getVisitDate().substring(0,7);
					if(str1.equals(time)){
						System.out.println("访客姓名："+vs.getName()+"\t"+"来访时间："+vs.getVisitDate().substring(0,19)+"\t来访信息:"+vs.getApitInf());
						count=count+1;
					}
				}
		 }
		 System.out.println("该月来访总人数："+count);
	}



	@Override
	public void queryWorkOverTime(String date) {
		String sql = "SELECT employee_id,NAME,department,clock_out_time,COUNT(DATE) count_over,(SELECT COUNT(employee_id) FROM clock) AS SUM FROM clock NATURAL JOIN employee WHERE YEAR(DATE)=? AND MONTH(DATE)=?  AND clock_out_time >='20:00:00' GROUP BY employee_id,NAME,department,clock_out_time";

		String[] param={date.substring(0,4),date.substring(5,7)};
		DataArrangeServiceDao dataArrangeServiceDao = new DataArrangeSericeDaoImpl();
		Employee emp = new Employee();
		int cou_over=0,cou_total=0;
		List<Employee> empList = dataArrangeServiceDao.queryWorkOverTime(sql,param);
		
		 if(empList!=null&&empList.size()!=0){
			 System.out.println("该月加班情况：");
			 System.out.println("加班名单：");
				for(int i=0;i<empList.size();i++){
					cou_over++;
					emp=empList.get(i);
					System.out.println(emp.getEmpNum()+"\t"+emp.getName()+"\t"+emp.getDepartment()+"\t"+emp.getOvertime_sum()+"次");
				}
		 }
		 cou_total = emp.getSign_in_sum();
		 NumberFormat nf = NumberFormat.getPercentInstance();
		 nf.setMinimumFractionDigits(2);
		 System.out.println("-加班人数："+cou_over);
		 System.out.println("-非加班人数："+cou_total);
		 System.out.println("-加班人数占总加班人数比："+nf.format(cou_over*1.0/cou_total));
		
	}

}
