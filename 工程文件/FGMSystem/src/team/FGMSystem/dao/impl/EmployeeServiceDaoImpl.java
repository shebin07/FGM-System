/**
 * @author 1.л���
 *	Ա������ʵ����	EmployeeServiceDaoImpl
 *
 */
package team.FGMSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.FGMSystem.dao.BaseDao;
import team.FGMSystem.dao.EmployeeServiceDao;
import team.FGMSystem.entity.Employee;

public class EmployeeServiceDaoImpl extends BaseDao implements EmployeeServiceDao {

	private Connection conn=null;//�������ݿ�����
	private PreparedStatement pstmt=null;//����ִ��SQL���
	private ResultSet rs=null;//�û������ѯ�����
	
	/**
	 * Ա����¼
	 */
	@Override
	public Object login(String sql, Object[] param) {
			try {
				conn = getConn(); // �õ����ݿ�����
				pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
				if (param != null) {
					for (int i = 0; i < param.length; i++) {
						pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
					}
				}
				rs = pstmt.executeQuery(); // ִ��SQL���	
				
				if(rs.next()){
					return rs.getString(3);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				super.closeAll(conn, pstmt, rs);
			}
			return null;

	}

	/**
	 * Ա��ǩ����Ϣ����
	 */
	@Override
	public boolean insertSignIn(String sql,Object[] param) {
		try {
			conn = getConn(); // �õ����ݿ�����
			//�ȼ���Ƿ��Ѿ�ǩ��
			String presql="select* from clock where employee_id=? and date=?";
			pstmt = conn.prepareStatement(presql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length-1; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���	
			if(rs.next()){
				if(rs.getString("clock_in_time")!=null){
					System.out.print("����ǩ��!");
					return false;
				}else{
					executeSQL(sql,param);
					return true;
				}
			}else{
				executeSQL(sql,param);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}

	/**
	 * Ա��ǩ��
	 */
	@Override
	public boolean insertSignOut(String sql,Object[] param) {
		try {
			conn = getConn(); // �õ����ݿ�����
			//�ȼ���Ƿ��Ѿ�ǩ��
			String presql="select* from clock where employee_id=? and date=?";
			pstmt = conn.prepareStatement(presql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 1; i < param.length; i++) {
					pstmt.setObject(i, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���	
			if(rs.next()){
				if(rs.getString("clock_out_time")!=null){
					System.out.print("����ǩ��!");
					return false;
				}
				if(rs.getString("clock_in_time")==null){
					System.out.print("����δǩ��!");
					return false;
				}
				else{
					executeSQL(sql,param);
					return true;
				}
			}else{
				System.out.print("����δǩ��!");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return false;
	}
	
	/**
	 * ��ѯ�������
	 */
	@Override
	public List<Employee> queryAttendance(String sql,Object[] param,int index) {
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
			List<Employee> staffList = new ArrayList<Employee>();
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���	
			
			while(rs.next()){
				Employee staff=new Employee();
				if(index==1){   //��ѯclock��
					staff.setEmpNum(rs.getString("employee_id"));
					staff.setNowDate(rs.getString("date"));
					staff.setIn_time(rs.getString("clock_in_time"));
					staff.setOut_time(rs.getString("clock_out_time"));
					staffList.add(staff);
				}else if(index==2){  //��ѯemployee��
					staff.setName(rs.getString("name"));
					staff.setEmpNum(rs.getString("employee_id"));
					staff.setDepartment(rs.getString("department"));
					staff.setJob(rs.getString("position"));
					staff.setPhoneNum(rs.getString("phone"));
					staff.setEntryDate(rs.getString("entry_date"));
					staff.setSign_in_sum(Integer.valueOf(rs.getString("sign_in_sum")).intValue());
					staff.setOvertime_sum(Integer.valueOf(rs.getString("overtime_sum")).intValue());
					staffList.add(staff);
					}
			}
			return staffList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * ��ѯ�Ӱ����
	 */
	@Override
	public ResultSet queryWorkOvertime(String empNum, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
