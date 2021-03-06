/**
 * @author 1.
 * 		   2. Shebin queryAttendance()
 * 					 queryWorkOverTime()
 *	���ݷ�������ʵ����	DataArrangeServiceImpl
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
	 * ��ѯԱ���������
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
				System.out.println(date+"�Ĺ�˾�������Ϊ��");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//String str1=emp.getVisitDate().substring(0,7);
				    System.out.println("Ա����ţ�"+emp.getEmpNum()+"\t"+"ǩ��ʱ�䣺"+emp.getIn_time()+"\tǩ��ʱ��:"+emp.getOut_time());
					}
				}
		 }
	
	

	/**
	 * ��ѯԱ���Ӱ����
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
				System.out.println(date+"�Ĺ�˾�Ӱ����Ϊ��");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//String str1=emp.getVisitDate().substring(0,7);
				    System.out.println("Ա����ţ�"+emp.getEmpNum()+"\t"+"ǩ��ʱ�䣺"+emp.getIn_time()+"\tǩ��ʱ��:"+emp.getOut_time());
					}
				}
		
	}

	/**
	 * ��ѯȫ�����
	 * @param date
	 */
	@Override
	public void queryAttendance(String date) {
		String sql = "SELECT employee_id,NAME,department,count(date) as sum from clock natural join employee where (clock_in_time LIKE '07:%' OR clock_in_time='08:00:00') AND YEAR(DATE)=? AND MONTH(DATE)=? group by employee_id,NAME,department";
		
		//��ȡ�·�����
		Calendar cal = Calendar.getInstance();   
		cal.set(Calendar.YEAR,Integer.parseInt(date.substring(0,4))); //������
		cal.set(Calendar.MONTH,Integer.parseInt(date.substring(5,7))-1);//��   
		int maxDate = cal.getActualMaximum(Calendar.DATE);
				
		String[] param={date.substring(0,4),date.substring(5,7)};
		DataArrangeServiceDao dataArrangeServiceDao = new DataArrangeSericeDaoImpl();
		Employee emp = new Employee();
		int cou_all=0,cou_part=0;
		List<Employee> empList = dataArrangeServiceDao.queryAttendance(sql,param);
		
		
		 if(empList!=null&&empList.size()!=0){
			 System.out.println("���³��������");
			 System.out.println("ȫ��������");
				for(int i=0;i<empList.size();i++){
					emp=empList.get(i);
					//��������
					if(emp.getSign_in_sum()==maxDate){
						cou_all++; 
						System.out.println(emp.getEmpNum()+"\t"+emp.getName()+"\t"+emp.getDepartment());
					}else cou_part++;
				}
		 }
		 NumberFormat nf = NumberFormat.getPercentInstance();
		 nf.setMinimumFractionDigits(2);
		 System.out.println("-ȫ��������"+cou_all);
		 System.out.println("-��ȫ��������"+cou_part);
		 System.out.println("-ȫ������ռ�ܳ��������ȣ�"+nf.format(cou_all*1.0/(cou_part+cou_all)));
	}

	/**
	 * ��ѯ�ÿ���Ϣ
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
				System.out.println("���·ÿ���Ϣ��");
				for(int i=0;i<vsList.size();i++){
					vs=vsList.get(i);
					String str1=vs.getVisitDate().substring(0,7);
					if(str1.equals(time)){
						System.out.println("�ÿ�������"+vs.getName()+"\t"+"����ʱ�䣺"+vs.getVisitDate().substring(0,19)+"\t������Ϣ:"+vs.getApitInf());
						count=count+1;
					}
				}
		 }
		 System.out.println("����������������"+count);
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
			 System.out.println("���¼Ӱ������");
			 System.out.println("�Ӱ�������");
				for(int i=0;i<empList.size();i++){
					cou_over++;
					emp=empList.get(i);
					System.out.println(emp.getEmpNum()+"\t"+emp.getName()+"\t"+emp.getDepartment()+"\t"+emp.getOvertime_sum()+"��");
				}
		 }
		 cou_total = emp.getSign_in_sum();
		 NumberFormat nf = NumberFormat.getPercentInstance();
		 nf.setMinimumFractionDigits(2);
		 System.out.println("-�Ӱ�������"+cou_over);
		 System.out.println("-�ǼӰ�������"+cou_total);
		 System.out.println("-�Ӱ�����ռ�ܼӰ������ȣ�"+nf.format(cou_over*1.0/cou_total));
		
	}

}
