package team.FGMSystem.service.impl;
/**
 * @author �λ�
 *	��ݷ�����	PackageServiceImpl
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
	 * Ա����ǰ̨ǩ�տ��
	 */
	@Override
	public void ReceivePackage(Employee emp) {
		System.out.println("Ա��"+emp.getEmpNum()+"����ǩ�տ�ݷ���");
		String sql="select *from rpackage where employee_id=?";
		String[] param={emp.getEmpNum()};
		List<Package> packList=new ArrayList<Package>();
		Package pk=new Package();
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		packList=PackageServiceDao.alterReceivePackage(sql, param);//����PackageServiceDaoImpl�е�ǩ�տ�ݺ���
		
		/*
		 * �Ȳ�ѯ��Ա���Ŀ�ݼ�¼
		 */
		if(packList!=null&&packList.size()!=0){
			System.out.println("Ա��"+emp.getEmpNum()+"�Ŀ�ݼ�¼��");
			//System.out.println(packList.size());
			for(int i=0;i<packList.size();i++){
				pk=packList.get(i);
				System.out.println("��ݱ�ţ�"+pk.getPackNum()+"\t"+"�Ƿ���ǰ̨��δǩ�գ���"+pk.isAtFG());
			}
			
			/*
			 * ����ǩ�տ�ݷ���
			 */
			while(true){
			System.out.print("�Ƿ�ǩ�տ�ݣ���y/n��:");
			Scanner scanner=new Scanner(System.in);
			String answer=scanner.next();
			if(answer.equals("y")){
				System.out.print("������Ҫǩ�յĿ�ݱ��:");
				Package prepk=new Package();
				String prepackNum=scanner.next();
				String preempNum=emp.getEmpNum();
				packList=PackageServiceDao.alterReceivePackage(sql, param);
				boolean isGet=false;   //�����ж��������Ƿ��Ѿ���ǩ��
				boolean isPack=false;   //�����ж������ݵ����Ƿ���ȷ
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
					System.out.println("�����ݵ��Ŵ�������������!");
				}else if(isPack==true &&isGet==true){
					System.out.println("�ÿ���ѱ�ǩ��!");
				}else if(isPack==true&&isGet==false){
					String presql="update rpackage set isAtFG=0  where packNum=? and employee_id=?";
					String[] preparam={pk.getPackNum(),emp.getEmpNum()};
					int count=PackageServiceDao.executeSQL(presql, preparam);
					if(count!=0){
						System.out.println("ǩ�ճɹ�!");
					}else{
						System.out.println("ǩ��ʧ��!");
					}
				}
				
			}
			else if(answer.equals("n")){
				System.out.println("�ݲ�ǩ���κο�ݣ�");
				return;
			}
			else{
				System.out.println("����������������룡");
			}
		}
		}else{
			System.out.println("Ա��"+emp.getEmpNum()+"�޿�ݼ�¼");
			return;
		}
	}

	/**
	 * ��ݱ��ĳ�
	 */
	@Override
	public void SendPackage(Employee emp) {
		System.out.print("������������(kg):");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		double weight=scanner.nextDouble(); 
		double cost=0.75*weight;//��ݷ���
		SimpleDateFormat tempDate=new SimpleDateFormat("yyyyMMddHHmmss");//��ȡ��ǰʱ��
		String datatime=tempDate.format(new Date());
		String packNum=datatime;//����ǰʱ��ת��Ϊ��ݵ���
		String sql="insert into spackage values(?,?,1,?,?)";//���ӿ����Ϣ��Ԥ����SQL���
		Object[] param={packNum,emp.getEmpNum(),weight,cost};//SQL����е�?��������
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		int count=PackageServiceDao.insertSendPackage(sql,param);
		if(count!=0){
			System.out.println("����ѳɹ��ĳ�!\n��ݵ���:"+packNum+"Ա�����:"+emp.getEmpNum()+"�������(kg):"+weight+"��������:"+cost);
		}else{
			System.out.println("��ݼĳ�ʧ��!");
		}
	}

	/**
	 * �����������
	 */
	@Override
	public void expressTakePack(Employee emp) {
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		System.out.println("��ݹ�˾��ȡ�߿��");
		String sql="update spackage set isAtFG=0 where isAtFG=1";//������Ƿ�ĳ�״̬��false����Ϊtrue
		int count=PackageServiceDao.alterExpressTakePack(sql,null);
		if(count!=0){
			System.out.println("������˾�ɹ�ȡ�߿��!");
		}else{
			System.out.println("���ĳ�����Ϊ0��������˾ȡ��ʧ��!");
		}
	}

	/*
	 * ǰ̨¼���µ������Ϣ
	 */
	@Override
	public void insertNewPackage(Package pk) {
		PackageServiceDaoImpl PackageServiceDao=new PackageServiceDaoImpl();
		String sql="insert into rpackage values(?,?,1)";
		Object[] param={pk.getPackNum(),pk.getEmpNum()};
		int count=PackageServiceDao.executeSQL(sql, param);
		if(count!=0){
			System.out.println("¼��ɹ�!");
		}else{
			System.out.println("¼��ʧ��!");
		}
	}
	@Override
	/**
	 * ���㵱�¿�ݷ���,�ָ��ֶΣ���ͬһ��ͬһ�µķ��ü����
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
				System.out.println("���¼ĳ��Ŀ�ݼ�¼��");
				for(int i=0;i<packList.size();i++){
					pk=packList.get(i);
					String str1=pk.getPackNum().substring(0, 6);
					if(str1.equals(date)){
						System.out.println("��ݱ�ţ�"+pk.getPackNum()+"\t"+"���������"+pk.getWeight()+"\t��ݷ���:"+pk.getCost());
						sum=sum+pk.getCost();
					}
				}
		 }
		 System.out.println("���¿���ܷ���Ϊ:"+sum);
		 return sum;
	}


}
