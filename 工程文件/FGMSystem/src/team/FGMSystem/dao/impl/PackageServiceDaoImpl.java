/**
 * @author �λ� 
 *��ݷ���ӿ�ʵ����	PackageServiceDaoImpl
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
	 * Ա����ǰ̨ǩ�տ��
	 */
	@Override
	public  List<Package> alterReceivePackage(String sql, Object[] param) {
		int count=0;
		List<Package> packList = new ArrayList<Package>();
		try{
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����

			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs=pstmt.executeQuery();//ִ��SQL���
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
		
		return null;//���ظı����ݵ�����
		
	}

	/**
	 * �ĳ����
	 */
	@Override
	public int insertSendPackage(String sql, Object[] param) {
		int count=super.executeSQL(sql, param);//ִ��SQL��䲢�����޸����ݵ�����
		return count;
	}

	/**
	 * ��ݹ�˾ȡ�߿��
	 */
	@Override
	public int alterExpressTakePack(String sql, Object[] param) {
		int count=super.executeSQL(sql, param);
		return count;
	}

	@Override
	public List<Package> queryPackCost(String sql, Object[] param) {
		try {
            conn = getConn(); // �õ����ݿ�����
            pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
            rs = pstmt.executeQuery(); // ִ��SQL���
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
