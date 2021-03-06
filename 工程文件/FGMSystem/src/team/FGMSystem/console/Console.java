/**
 * @author	1.�λ�
 * 		   	2.л���
 * 			3.Shebin Xu
 *	����̨��	Console
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
	//������
	
	public static void main(String[] args) {
		System.out.println("----------------------��ӭʹ��Moonǰ̨ϵͳ��-----------------");
		System.out.println("----------------------------���ȵ�¼-------------------------");
		Object isLogin=null;
		Employee staff=new Employee();
		int count=1;
		while(true){
			Scanner input=new Scanner(System.in);
			System.out.println("������Ա����ţ�");
			String account=input.next();
			System.out.println("����������:");
			String password=input.next();
			EmployeeService es=new EmployeeServiceImpl();	
			isLogin=es.login(account, password); //��¼
			if(isLogin!=null){
				System.out.println("��¼�ɹ���");
				staff.setEmpNum(account);
				break;
			}else{
				System.out.println("��¼ʧ�ܣ���ȷ�������û����������Ƿ���ȷ,���µ�¼��");
				if(count==3){
					System.out.println("��¼�����Ѵﵽ3�Σ���¼ʧ�ܣ��˳�ϵͳ��");
					return;
				}
			}
			count=count+1;
			input.close();
		}
		mainMenu(isLogin,staff);
		
	}
	//һ��˵�����(������),����̨ҳ��͹���ѡ��
	public static void mainMenu(Object isLogin,Employee staff) {
		while(true){
			if(isLogin.equals("normal")){
				System.out.println("*******************************************************************");
				
				System.out.println("��ѡ����Ӧҵ��");
				System.out.println("1.Ա��ǩ��ǩ������	2.��ݷ���	3.��ѯ����	4.�˳�ϵͳ");
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
					System.out.println("���˳�ϵͳ��");
					System.out.println("*******************************************************************");
					return;
					default:
						return;
				}
			}else if(isLogin.equals("manager")){
				System.out.println("*******************************************************************");
				System.out.println("��ѡ����Ӧҵ��");
				System.out.println("1.Ա��ǩ��ǩ������	2.��ݷ���	3.�ͻ�����	4.��ѯ����	5.�˳�ϵͳ");
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
					System.out.println("���˳�ϵͳ��");
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
	 * ��ͨԱ���͹���Ա��ͬ��ǩ��ǩ��
	 * @param staff
	 */
	public static void sign(Employee staff){
		while(true){
			System.out.println("*******************************************************************");
			System.out.println("��ѡ�����ҵ��");
			System.out.println("1.ǩ��	2.ǩ��	3.�����ϼ��˵�");
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

	//����˵��������ʵ�֣����ã�
	/**
	 * ��ͨԱ���Ķ����˵�
	 * @param index
	 * @param staff
	 */
	public static void nomalMenu(int index,Employee staff){
		EmployeeService es=new EmployeeServiceImpl();
		Scanner input=new Scanner(System.in);
		if(index==2){    //caseΪ2�Ŀ��ҵ��
			PackageServiceImpl ps=new PackageServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("��ѡ�����ҵ��");
				System.out.println("1.ǩ�տ��	2.�ĳ����	3.�����ϼ��˵�");
				int k=input.nextInt();
				if(k==1){
					ps.ReceivePackage(staff);
				}else if(k==2){
					ps.SendPackage(staff);
				}else{
					return;
				}
			}
			
		}else if(index==3){    //caseΪ3�Ĳ�ѯҵ��
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("��ѡ�����ҵ��");
				System.out.println("1.��ѯ�������	2.��ѯ�Ӱ����	3.�����ϼ��˵�");
				int k=input.nextInt();
				if(k==1){
					System.out.println("��ѡ���ѯ��Χ��1.����    2.��ʷ");
					int j = input.nextInt();
					if(j==2){
						es.queryAttendance(staff);
					}else if(j==1){
						System.out.println("�밴��ʽ�������ʱ�䣺(yyyy-MM��Ӧ��-��)");
						String time=input.next();
						es.queryAttendance(staff,time);
					}
				}else if(k==2){
					System.out.println("��ѡ���ѯ��Χ��1.����    2.��ʷ");
					int j = input.nextInt();
					if(j==2){
						es.queryWorkOverTime(staff);
					}else if(j==1){
						System.out.println("�밴��ʽ�������ʱ�䣺(yyyy-MM��Ӧ��-��)");
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
	 * ǰ̨����Ա�����˵�
	 * @param index
	 * @param staff
	 */
	public static void managerMenu(int index,Employee staff) {
		Scanner input=new Scanner(System.in);
		if(index==2){    //caseΪ2�Ŀ��ҵ��
			PackageServiceImpl ps=new PackageServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("��ѡ�����ҵ��");
				System.out.println("1.���˿�ݷ���      2.�տ��¼����Ϣ    	3.��ݹ�˾���߿��	4.���㵱�¿�ݷ���	5.�����ϼ��˵�");
				int k=input.nextInt();
				if(k==1){
					nomalMenu(2,staff);
				}else if(k==2){
					Package pk=new Package();
					while(true){
						System.out.print("�������ݵ���:");
						pk.setPackNum(input.next());
						System.out.print("������Ա�����:");
						pk.setEmpNum(input.next());
						ps.insertNewPackage(pk);
						System.out.print("�Ƿ�Ҫ����¼�룿(y/n)");
						String m=input.next();
						if(m.equals("n")){
							break;
						}
					}
				}else if(k==3){
					ps.expressTakePack(staff);
				}else if(k==4){
					System.out.println("�밴��ʽ�������ʱ�䣺(yyyyMM��Ӧ����)");
					String time=input.next();
					ps.queryPackCost(time);
				}else{
					return;
				}
			}
			
		}else if(index==3){  //caseΪ3�Ŀͻ�����
			VisitorService vs = new VisitorServiceImpl();
			while(true){     
				System.out.println("*******************************************************************");
				System.out.println("��ѡ�����ҵ��");
				System.out.println("1.�ͻ�ԤԼ	2.�ͻ�����	3.�����ϼ��˵�");
				int k=input.nextInt();
				if(k==1){                 //k=1�ͻ�ԤԼ
					Visitor temp = new Visitor();
                    System.out.println("�ͻ�������");
                    temp.setName(input.next());
                    System.out.println("�ÿͺ��룺");
                    temp.setPhoneNum(input.next());
                    System.out.println("�밴��ȷ��ʽ����ԤԼ����(yyyy-MM-dd��Ӧ��-��-��)��");
                    String strdate = input.next();
                    System.out.println("�밴��ȷ��ʽ�������ԤԼʱ��(hh:mm��Ӧʱ:��,24Сʱ��)��");
                    String strtime = input.next();
                    String str=strdate+" "+strtime;
                    try {
            			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
            			temp.setApitDate(sdf.parse(str));
            		} catch (ParseException e) {
            			System.out.println("��������������������ʽ(yyyy-mm-dd hh:mm)");
            			e.printStackTrace();
            		}
                    System.out.println("ԤԼʱ��(����)��");
                    temp.setApitDuration(input.nextInt());
                    System.out.println("ԤԼ����������Ϣ��");
                    temp.setApitInf(input.next());                   
                    vs.makeAppoint(temp,str);
				}else if(k==2){          //k=2�ͻ�����
					Visitor temp = new Visitor();    
                    System.out.println("�ͻ�������");
                    temp.setName(input.next());
                    System.out.println("�ÿͺ��룺");
                    temp.setPhoneNum(input.next());
                    System.out.println("�밴��ȷ��ʽ������ԤԼ������(yyyy-MM-dd��Ӧ��-��-��)��");
                    String strdate = input.next();
                    System.out.println("�밴��ȷ��ʽ����������ԤԼ��ʱ��(hh:mm��Ӧʱ:��,24Сʱ��)��");
                    String strtime = input.next();
                    String str=strdate+" "+strtime;
                    vs.visit(temp,str);
					
				}else{
					return;
				}
			}
		}else if(index==4){  //caseΪ4�Ĳ�ѯҵ��
			DataArrangeService das = new DataArrangeServiceImpl();
			EmployeeService es=new EmployeeServiceImpl();
			while(true){
				System.out.println("*******************************************************************");
				System.out.println("��ѡ�����ҵ��");
				System.out.println("1.���˲�ѯ     2.��ѯȫ��Ա���������       3.��ѯȫ��Ա���Ӱ����	  4.��ѯĳ����������	5.�����ϼ��˵�");
				int k=input.nextInt();
				if(k==1){
//					es.queryAttendance(staff);
					nomalMenu(3,staff);
				}else if(k==2){
					System.out.println("�밴��ȷ��ʽ����Ա����������(yyyy-MM ��Ӧ��-��)�� ");
                    String date = input.next();
                    das.queryAttendance(date);
				}else if(k==3){
					System.out.println("�밴��ȷ��ʽ����Ա���Ӱ�����(yyyy-MM ��Ӧ��-��)�� ");
                    String date = input.next();
                    //das.queryEmpWorkOverTime(date);
                    das.queryWorkOverTime(date);
				}else if(k==4){
					System.out.println("�������밴��ʽ�����ѯʱ�䣺(yyyy-MM��Ӧ����)");
					String time=input.next();	
					das.queryVisitInf(time);
				}else{
					return;
				}
			}
		}
	}

}
