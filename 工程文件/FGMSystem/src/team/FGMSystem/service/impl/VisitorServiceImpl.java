/**
 * @author 
 * 			2.Shebin Xu
 *	访客服务类	VisitorServiceImpl
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
	 * 访客预约
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
            System.out.println("预约成功！预约时间为: " + apiTime);
        } else {
            System.out.println("预约失败！请重新预约");
        }
		
	}

	/**
	 * 访客来访
	 * @param emp
	 * @param vis
	 */
	@Override
	public void visit(Visitor vis,String str) {
		String sql = "select * from visit where NAME=? and phoneNum=? and apitDate=?";
        String[] param = {vis.getName(), vis.getPhoneNum(),str};
        VisitorServiceDaoImpl visitorServiceDao = new VisitorServiceDaoImpl();
        //先检查是否已预约
        Visitor vs=new Visitor();
        vs=visitorServiceDao.alterVisit(sql, param);
        if(vs==null){
        	System.out.println("抱歉!您无预约!");
        }
        else if(vs.isApit()==true){
        	System.out.println("抱歉!该预约已被来访!");
        }else{
        	SimpleDateFormat tempDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取当前时间
    		String visitime=tempDate.format(new Date());
        	String presql="update visit set visitDate=?,isApit=true where NAME=? and phoneNum=? and apitDate=?";
        	String[] preparam = {visitime,vis.getName(), vis.getPhoneNum(),str};
        	int count=visitorServiceDao.executeSQL(presql, preparam);
        	if(count!=0){
        		System.out.println("来访成功!来访时间为:"+visitime);
        	}
        }
		
	}


}
