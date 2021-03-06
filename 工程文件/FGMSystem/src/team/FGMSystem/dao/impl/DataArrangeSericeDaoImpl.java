/**
 * @author 	1.赖苗苗
 * 			2.Shebin Xu 
 *	数据分析接口实现类	DataArrangeSericeDaoImpl
 *
 */
package team.FGMSystem.dao.impl;

import team.FGMSystem.dao.BaseDao;
import team.FGMSystem.dao.DataArrangeServiceDao;
import team.FGMSystem.dao.*;
import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Package;
import team.FGMSystem.entity.Visitor;
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataArrangeSericeDaoImpl extends BaseDao implements DataArrangeServiceDao {

	private Connection conn = null;//保存数据库连接
    private PreparedStatement pstmt = null;//用于执行SQL语句
    private ResultSet rs = null;//用户保存查询结果集

	@Override
	public List<Employee> queryEmpAttendance(String sql,Object[]param) {
		try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
            rs = pstmt.executeQuery(); // 执行SQL语句
            List<Employee> vsList=new ArrayList<Employee>();
            while (rs.next()) {
            	Employee staff=new Employee();
            	staff.setEmpNum(rs.getString("employee_id"));
            	staff.setNowDate(rs.getString("date"));
				staff.setIn_time(rs.getString("clock_in_time"));
				staff.setOut_time(rs.getString("clock_out_time"));
				vsList.add(staff);
            }
            return vsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
		return null;
	}

	@Override
	public List<Employee> queryEmpWorkOverTime(String sql,Object[]param) {
		try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
            rs = pstmt.executeQuery(); // 执行SQL语句
            List<Employee> vsList=new ArrayList<Employee>();
            while (rs.next()) {
            	if(rs.getString("clock_out_time").compareTo("20:00:00") >= 0){
	            	Employee staff=new Employee();
	            	staff.setEmpNum(rs.getString("employee_id"));
	            	staff.setNowDate(rs.getString("date"));
					staff.setIn_time(rs.getString("clock_in_time"));
					staff.setOut_time(rs.getString("clock_out_time"));
					vsList.add(staff);
            	}
            }
            return vsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
		return null;
	}

	@Override
	public List<Employee> queryAttendance(String sql,Object[]param) {
		try{
			conn = getConn(); 					// 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
            rs = pstmt.executeQuery(); 			// 执行SQL语句
            List<Employee> empList = new ArrayList<Employee>();
            while (rs.next()) {
            	Employee emp = new Employee();
            	emp.setName(rs.getString("NAME"));
            	emp.setEmpNum(rs.getString("employee_id"));
            	emp.setDepartment(rs.getString("department"));
            	emp.setSign_in_sum(rs.getInt("sum"));
            	empList.add(emp);
            }
            return empList;
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return null;
	}

	@Override
	public List<Visitor> queryVisitInf(String sql,Object[]param) {
		try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            rs = pstmt.executeQuery(); // 执行SQL语句
            List<Visitor> vsList=new ArrayList<Visitor>();
            while (rs.next()) {
            	Visitor vs=new Visitor();
            	vs.setName(rs.getString("NAME"));
            	vs.setPhoneNum(rs.getString("phoneNum"));
            	vs.setVisitDate(rs.getString("visitDate"));
            	vs.setApitInf(rs.getString("apitImfo"));
            	vsList.add(vs);
            }
            return vsList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return null;
	
	}

	@Override
	public List<Employee> queryWorkOverTime(String sql, Object[] param) {
		try{
			conn = getConn(); 					// 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
            rs = pstmt.executeQuery(); 			// 执行SQL语句
            List<Employee> empList = new ArrayList<Employee>();
            while (rs.next()) {
            	if(rs.getInt("count_over")>0){
	            	Employee emp = new Employee();
	            	emp.setName(rs.getString("NAME"));
	            	emp.setEmpNum(rs.getString("employee_id"));
	            	emp.setDepartment(rs.getString("department"));
	            	emp.setOvertime_sum(rs.getInt("count_over"));
	            	emp.setSign_in_sum(rs.getInt("sum"));
	            	empList.add(emp);
            	}
            }
            return empList;
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return null;
	}
	
	  
}
