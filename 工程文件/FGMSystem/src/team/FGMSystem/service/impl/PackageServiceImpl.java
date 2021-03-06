package team.FGMSystem.service.impl;
/**
 * @author 任欢
 *	快递服务类	PackageServiceImpl
 *
 */
import team.FGMSystem.entity.Employee;
import team.FGMSystem.service.PackageService;
import team.FGMSystem.dao.impl.*;
import team.FGMSystem.entity.Package;
import team.FGMSystem.dao.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PackageServiceImpl implements PackageService {

	/**
	 * 员工来前台签收快递
	 */
	@Override
	public void ReceivePackage(Employee emp) {
		System.out.println("员工"+emp.getEmpNum()+"进入签收快递服务！");
		String sql="select *from rpackage where employee_id=?";
		String[] param={emp.getEmpNum()};
		List<Package> packList=new ArrayList<Package>();
		Package pk=new Package();
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		packList=PackageServiceDao.alterReceivePackage(sql, param);//调用PackageServiceDaoImpl中的签收快递函数
		
		/*
		 * 先查询改员工的快递记录
		 */
		if(packList!=null&&packList.size()!=0){
			System.out.println("员工"+emp.getEmpNum()+"的快递记录：");
			//System.out.println(packList.size());
			for(int i=0;i<packList.size();i++){
				pk=packList.get(i);
				System.out.println("快递编号："+pk.getPackNum()+"\t"+"是否在前台（未签收）："+pk.isAtFG());
			}
			
			/*
			 * 进入签收快递服务
			 */
			while(true){
			System.out.print("是否签收快递？（y/n）:");
			Scanner scanner=new Scanner(System.in);
			String answer=scanner.next();
			if(answer.equals("y")){
				System.out.print("请输入要签收的快递编号:");
				Package prepk=new Package();
				String prepackNum=scanner.next();
				String preempNum=emp.getEmpNum();
				packList=PackageServiceDao.alterReceivePackage(sql, param);
				boolean isGet=false;   //用来判断这个快递是否已经被签收
				boolean isPack=false;   //用来判断输入快递单号是否正确
				for(int i=0;i<packList.size();i++){
					pk=packList.get(i);
					if(pk.getEmpNum().equals(preempNum)&& pk.getPackNum().equals(prepackNum )&&pk.isAtFG()==false){
						isGet=true;
						isPack=true;
						break;
					}else if(pk.getEmpNum().equals(preempNum) && pk.getPackNum().equals(prepackNum) &&pk.isAtFG()==true){
						isPack=true;
						break;
					}
				}
				if(isPack==false){
					System.out.println("输入快递单号错误，请重新输入!");
				}else if(isPack==true &&isGet==true){
					System.out.println("该快递已被签收!");
				}else if(isPack==true&&isGet==false){
					String presql="update rpackage set isAtFG=0  where packNum=? and employee_id=?";
					String[] preparam={pk.getPackNum(),emp.getEmpNum()};
					int count=PackageServiceDao.executeSQL(presql, preparam);
					if(count!=0){
						System.out.println("签收成功!");
					}else{
						System.out.println("签收失败!");
					}
				}
				
			}
			else if(answer.equals("n")){
				System.out.println("暂不签收任何快递！");
				return;
			}
			else{
				System.out.println("输入错误，请重新输入！");
			}
		}
		}else{
			System.out.println("员工"+emp.getEmpNum()+"无快递记录");
			return;
		}
	}

	/**
	 * 快递被寄出
	 */
	@Override
	public void SendPackage(Employee emp) {
		System.out.print("请输入快递重量(kg):");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		double weight=scanner.nextDouble(); 
		double cost=0.75*weight;//快递费用
		SimpleDateFormat tempDate=new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间
		String datatime=tempDate.format(new Date());
		String packNum=datatime;//将当前时间转换为快递单号
		String sql="insert into spackage values(?,?,1,?,?)";//增加快递信息的预编译SQL语句
		Object[] param={packNum,emp.getEmpNum(),weight,cost};//SQL语句中的?参数数组
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		int count=PackageServiceDao.insertSendPackage(sql,param);
		if(count!=0){
			System.out.println("快递已成功寄出!\n快递单号:"+packNum+"员工编号:"+emp.getEmpNum()+"快递重量(kg):"+weight+"所花费用:"+cost);
		}else{
			System.out.println("快递寄出失败!");
		}
	}

	/**
	 * 快递送入物流
	 */
	@Override
	public void expressTakePack(Employee emp) {
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		System.out.println("快递公司来取走快递");
		String sql="update spackage set isAtFG=0 where isAtFG=1";//将快递是否寄出状态的false更改为true
		int count=PackageServiceDao.alterExpressTakePack(sql,null);
		if(count!=0){
			System.out.println("物流公司成功取走快递!");
		}else{
			System.out.println("待寄出总数为0，物流公司取走失败!");
		}
	}

	/*
	 * 前台录入新到快递信息
	 */
	@Override
	public void insertNewPackage(Package pk) {
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		String sql="insert into rpackage values(?,?,1)";
		Object[] param={pk.getPackNum(),pk.getEmpNum()};
		int count=PackageServiceDao.executeSQL(sql, param);
		if(count!=0){
			System.out.println("录入成功!");
		}else{
			System.out.println("录入失败!");
		}
	}
	@Override
	/**
	 * 结算当月快递费用,分割字段，把同一年同一月的费用简单相加
	 * @param date
	 * @return cost
	 */
	public double queryPackCost(String date) {
		 PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		 List<Package> packList=new ArrayList<Package>();
		 String sql="select * from spackage";
		 packList=PackageServiceDao.queryPackCost(sql, null);
		 Package pk=new Package();
		 double sum=0;
		 if(packList!=null&&packList.size()!=0){
				System.out.println("该月寄出的快递记录：");
				for(int i=0;i<packList.size();i++){
					pk=packList.get(i);
					String str1=pk.getPackNum().substring(0, 6);
					if(str1.equals(date)){
						System.out.println("快递编号："+pk.getPackNum()+"\t"+"快递重量："+pk.getWeight()+"\t快递费用:"+pk.getCost());
						sum=sum+pk.getCost();
					}
				}
		 }
		 System.out.println("该月快递总费用为:"+sum);
		 return sum;
	}


}
