/**
 * @author	1.任欢
 * 		   	2.谢琼颉
 * 			3.Shebin Xu
 *	控制台类	Console
 *
 */
package team.FGMSystem.console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Package;
import team.FGMSystem.entity.Visitor;
import team.FGMSystem.service.DataArrangeService;
import team.FGMSystem.service.EmployeeService;
import team.FGMSystem.service.VisitorService;
import team.FGMSystem.service.impl.DataArrangeServiceImpl;
import team.FGMSystem.service.impl.EmployeeServiceImpl;
import team.FGMSystem.service.impl.PackageServiceImpl;
import team.FGMSystem.service.impl.VisitorServiceImpl;

public class Console {
	//主函数
	
	public static void main(String[] args) {
		System.out.println("----------------------欢迎使用Moon前台系统！-----------------");
		System.out.println("----------------------------请先登录-------------------------");
		Object isLogin=null;
		Employee staff=new Employee();
		int count=1;
		while(true){
			Scanner input=new Scanner(System.in);
			System.out.println("请输入员工编号：");
			String account=input.next();
			System.out.println("请输入密码:");
			String password=input.next();
			EmployeeService es=new EmployeeServiceImpl();	
			isLogin=es.login(account, password); //登录
			if(isLogin!=null){
				System.out.println("登录成功！");
				staff.setEmpNum(account);
				break;
			}else{
				System.out.println("登录失败！请确认您的用户名和密码是否正确,重新登录。");
				if(count==3){
					System.out.println("登录次数已达到3次，登录失败！退出系统。");
					return;
				}
			}
			count=count+1;
			input.close();
		}
		mainMenu(isLogin,staff);
		
	}
	//一层菜单函数(主流程),控制台页面和功能选择
	public static void mainMenu(Object isLogin,Employee staff) {
		while(true){
			if(isLogin.equals("normal")){
				System.out.println("*******************************************************************");
				
				System.out.println("请选择相应业务：");
				System.out.println("1.员工签到签退任务	2.快递服务	3.查询服务	4.退出系统");
				Scanner input=new Scanner(System.in);
				int index=input.nextInt();
				switch(index){
				case 1:
					sign(staff);
					break;
				case 2:
				case 3:
					nomalMenu(index,staff);
					break;
				case 4:
					System.out.println("已退出系统！");
					System.out.println("*******************************************************************");
					return;
					default:
						return;
				}
			}else if(isLogin.equals("manager")){
				System.out.println("*******************************************************************");
				System.out.println("请选择相应业务：");
				System.out.println("1.员工签到签退任务	2.快递服务	3.客户服务	4.查询服务	5.退出系统");
				Scanner input=new Scanner(System.in);
				int index=input.nextInt();
				switch(index){
				case 1:
					sign(staff);
					break;
				case 2:
				case 3:
				case 4:
					managerMenu(index,staff);
					break;
				case 5:
					System.out.println("已退出系统！");
					System.out.println("*******************************************************************");
					return;
					default:
						return;
				}
			}else{
				break;
			}
		}
		
	}
	
	/**
	 * 普通员工和管理员共同的签到签退
	 * @param staff
	 */
	public static void sign(Employee staff){
		while(true){
			System.out.println("*******************************************************************");
			System.out.println("请选择具体业务：");
			System.out.println("1.签到	2.签退	3.返回上级菜单");
			Scanner input=new Scanner(System.in);
			int index=input.nextInt();
			EmployeeService es=new EmployeeServiceImpl();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date(System.currentTimeMillis());
			if(index==1){
				staff.setNowDate(formatter.format(nowDate).substring(0, 10));
				staff.setIn_time(formatter.format(nowDate).substring(11,19));
				es.signIn(staff);
			}else if(index==2){
				staff.setNowDate(formatter.format(nowDate).substring(0, 10));
				staff.setOut_time(formatter.format(nowDate).substring(11,19));
				es.signOut(staff);
			}else{
				return;
			}
		}
	}

	//二层菜单多个功能实现（调用）
	/**
	 * 普通员工的二级菜单
	 * @param index
	 * @param staff
	 */
	public static void nomalMenu(int index,Employee staff){
		EmployeeService es=new EmployeeServiceImpl();
		Scanner input=new Scanner(System.in);
		if(index==2){    //case为2的快递业务
			PackageServiceImpl ps=new PackageServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("请选择具体业务：");
				System.out.println("1.签收快递	2.寄出快递	3.返回上级菜单");
				int k=input.nextInt();
				if(k==1){
					ps.ReceivePackage(staff);
				}else if(k==2){
					ps.SendPackage(staff);
				}else{
					return;
				}
			}
			
		}else if(index==3){    //case为3的查询业务
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("请选择具体业务：");
				System.out.println("1.查询出勤情况	2.查询加班情况	3.返回上级菜单");
				int k=input.nextInt();
				if(k==1){
					System.out.println("请选择查询范围：1.单月    2.历史");
					int j = input.nextInt();
					if(j==2){
						es.queryAttendance(staff);
					}else if(j==1){
						System.out.println("请按格式输入结算时间：(yyyy-MM对应年-月)");
						String time=input.next();
						es.queryAttendance(staff,time);
					}
				}else if(k==2){
					System.out.println("请选择查询范围：1.单月    2.历史");
					int j = input.nextInt();
					if(j==2){
						es.queryWorkOverTime(staff);
					}else if(j==1){
						System.out.println("请按格式输入结算时间：(yyyy-MM对应年-月)");
						String time=input.next();
						es.queryWorkOverTime(staff,time);
					}
				}else{
					return;
				}
			}
		}

	}
	
	/**
	 * 前台管理员二级菜单
	 * @param index
	 * @param staff
	 */
	public static void managerMenu(int index,Employee staff) {
		Scanner input=new Scanner(System.in);
		if(index==2){    //case为2的快递业务
			PackageServiceImpl ps=new PackageServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("请选择具体业务：");
				System.out.println("1.个人快递服务      2.收快递录入信息    	3.快递公司拿走快递	4.结算当月快递费用	5.返回上级菜单");
				int k=input.nextInt();
				if(k==1){
					nomalMenu(2,staff);
				}else if(k==2){
					Package pk=new Package();
					while(true){
						System.out.print("请输入快递单号:");
						pk.setPackNum(input.next());
						System.out.print("请输入员工编号:");
						pk.setEmpNum(input.next());
						ps.insertNewPackage(pk);
						System.out.print("是否还要继续录入？(y/n)");
						String m=input.next();
						if(m.equals("n")){
							break;
						}
					}
				}else if(k==3){
					ps.expressTakePack(staff);
				}else if(k==4){
					System.out.println("请按格式输入结算时间：(yyyyMM对应年月)");
					String time=input.next();
					ps.queryPackCost(time);
				}else{
					return;
				}
			}
			
		}else if(index==3){  //case为3的客户服务
			VisitorService vs = new VisitorServiceImpl();
			while(true){     
				System.out.println("*******************************************************************");
				System.out.println("请选择具体业务：");
				System.out.println("1.客户预约	2.客户来访	3.返回上级菜单");
				int k=input.nextInt();
				if(k==1){                 //k=1客户预约
					Visitor temp = new Visitor();
                    System.out.println("客户姓名：");
                    temp.setName(input.next());
                    System.out.println("访客号码：");
                    temp.setPhoneNum(input.next());
                    System.out.println("请按正确格式输入预约日期(yyyy-MM-dd对应年-月-日)：");
                    String strdate = input.next();
                    System.out.println("请按正确格式输入具体预约时刻(hh:mm对应时:分,24小时制)：");
                    String strtime = input.next();
                    String str=strdate+" "+strtime;
                    try {
            			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
            			temp.setApitDate(sdf.parse(str));
            		} catch (ParseException e) {
            			System.out.println("日期输入错误，请检查输入格式(yyyy-mm-dd hh:mm)");
            			e.printStackTrace();
            		}
                    System.out.println("预约时长(分钟)：");
                    temp.setApitDuration(input.nextInt());
                    System.out.println("预约对象或具体信息：");
                    temp.setApitInf(input.next());                   
                    vs.makeAppoint(temp,str);
				}else if(k==2){          //k=2客户来访
					Visitor temp = new Visitor();    
                    System.out.println("客户姓名：");
                    temp.setName(input.next());
                    System.out.println("访客号码：");
                    temp.setPhoneNum(input.next());
                    System.out.println("请按正确格式输入您预约的日期(yyyy-MM-dd对应年-月-日)：");
                    String strdate = input.next();
                    System.out.println("请按正确格式输入您具体预约的时刻(hh:mm对应时:分,24小时制)：");
                    String strtime = input.next();
                    String str=strdate+" "+strtime;
                    vs.visit(temp,str);
					
				}else{
					return;
				}
			}
		}else if(index==4){  //case为4的查询业务
			DataArrangeService das = new DataArrangeServiceImpl();
			EmployeeService es=new EmployeeServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("请选择具体业务：");
				System.out.println("1.个人查询     2.查询全体员工考勤情况       3.查询全体员工加班情况	  4.查询某月来访人数	5.返回上级菜单");
				int k=input.nextInt();
				if(k==1){
//					es.queryAttendance(staff);
					nomalMenu(3,staff);
				}else if(k==2){
					System.out.println("请按正确格式输入员工考勤日期(yyyy-MM 对应年-月)： ");
                    String date = input.next();
                    das.queryAttendance(date);
				}else if(k==3){
					System.out.println("请按正确格式输入员工加班日期(yyyy-MM 对应年-月)： ");
                    String date = input.next();
                    //das.queryEmpWorkOverTime(date);
                    das.queryWorkOverTime(date);
				}else if(k==4){
					System.out.println("请输入请按格式输入查询时间：(yyyy-MM对应年月)");
					String time=input.next();	
					das.queryVisitInf(time);
				}else{
					return;
				}
			}
		}
	}

}
