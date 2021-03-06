/**
 * @author 任欢 
 *快递服务接口实现类	PackageServiceDaoImpl
 *
 */
package team.FGMSystem.dao.impl;

import team.FGMSystem.dao.BaseDao;
import team.FGMSystem.dao.PackageServiceDao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import team.FGMSystem.entity.*;
import team.FGMSystem.entity.Package;

public class PackageServiceDaoImpl extends BaseDao implements PackageServiceDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	/**
	 * 员工来前台签收快递
	 */
	@Override
	public  List<Package> alterReceivePackage(String sql, Object[] param) {
		int count=0;
		List<Package> packList = new ArrayList<Package>();
		try{
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象

			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs=pstmt.executeQuery();//执行SQL语句
			while(rs.next()){
				Package pk=new Package();
				pk.setPackNum(rs.getString("packNum"));
				pk.setEmpNum(rs.getString("employee_id"));
				if(rs.getString("isAtFG").equals("0")){
					pk.setAtFG(false);
				}else{
					pk.setAtFG(true);
				}
				packList.add(pk);
			}
			return packList;
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		
		return null;//返回改变数据的条数
		
	}

	/**
	 * 寄出快递
	 */
	@Override
	public int insertSendPackage(String sql, Object[] param) {
		int count=super.executeSQL(sql, param);//执行SQL语句并返回修改数据的条数
		return count;
	}

	/**
	 * 快递公司取走快递
	 */
	@Override
	public int alterExpressTakePack(String sql, Object[] param) {
		int count=super.executeSQL(sql, param);
		return count;
	}

	@Override
	public List<Package> queryPackCost(String sql, Object[] param) {
		try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            rs = pstmt.executeQuery(); // 执行SQL语句
            List<Package> packList=new ArrayList<Package>();
            while (rs.next()) {
            	Package pk=new Package();
            	pk.setPackNum(rs.getString("packNum"));
            	pk.setEmpNum(rs.getString("employee_id"));
            	pk.setWeight(rs.getDouble("weight"));
            	pk.setCost(rs.getDouble("cost"));
            	if(rs.getInt("isAtFG")!=0){
            		pk.setAtFG(true);
            	}else{
            		pk.setAtFG(false);
            	}
            	packList.add(pk);
            }
            return packList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return null;
	
	}

}
