/**
 * @author 
 * 			2.Shebin Xu
 *	�ÿͷ�����	VisitorServiceImpl
 *
 */
package team.FGMSystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import team.FGMSystem.dao.impl.VisitorServiceDaoImpl;
import team.FGMSystem.entity.Visitor;
import team.FGMSystem.service.VisitorService;

public class VisitorServiceImpl implements VisitorService {

	/**
	 * �ÿ�ԤԼ
	 * @param emp
	 * @param vis
	 */
	@Override
	public void makeAppoint(Visitor vis,String apiTime) {
		String sql = "insert into visit values(?,?,false,?,?,null,?)";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		//Object[] param={vis.getName(),vis.getPhoneNum(),sdf.format(vis.getApitDate()),vis.getApitDuration(),vis.getApitInf()};
		Object[] param={vis.getName(),vis.getPhoneNum(),apiTime,vis.getApitDuration(),vis.getApitInf()};
		VisitorServiceDaoImpl visitorServiceDao = new VisitorServiceDaoImpl();
        int count = visitorServiceDao.insertMakeAppoint(sql,param);
        if (count != 0) {
            System.out.println("ԤԼ�ɹ���ԤԼʱ��Ϊ: " + apiTime);
        } else {
            System.out.println("ԤԼʧ�ܣ�������ԤԼ");
        }
		
	}

	/**
	 * �ÿ�����
	 * @param emp
	 * @param vis
	 */
	@Override
	public void visit(Visitor vis,String str) {
		String sql = "select * from visit where NAME=? and phoneNum=? and apitDate=?";
        String[] param = {vis.getName(), vis.getPhoneNum(),str};
        VisitorServiceDaoImpl visitorServiceDao = new VisitorServiceDaoImpl();
        //�ȼ���Ƿ���ԤԼ
        Visitor vs=new Visitor();
        vs=visitorServiceDao.alterVisit(sql, param);
        if(vs==null){
        	System.out.println("��Ǹ!����ԤԼ!");
        }
        else if(vs.isApit()==true){
        	System.out.println("��Ǹ!��ԤԼ�ѱ�����!");
        }else{
        	SimpleDateFormat tempDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ȡ��ǰʱ��
    		String visitime=tempDate.format(new Date());
        	String presql="update visit set visitDate=?,isApit=true where NAME=? and phoneNum=? and apitDate=?";
        	String[] preparam = {visitime,vis.getName(), vis.getPhoneNum(),str};
        	int count=visitorServiceDao.executeSQL(presql, preparam);
        	if(count!=0){
        		System.out.println("���óɹ�!����ʱ��Ϊ:"+visitime);
        	}
        }
		
	}


}
