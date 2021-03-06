/**
 * @author Shebin Xu
 *	数据分析接口类	DataArrangeServiceDao 
 *
 */
package team.FGMSystem.dao;

import java.util.List;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Visitor;

public interface DataArrangeServiceDao {
	
//	/**
//	 * 计算某月访客人数
//	 * @return sum
//	 */
//	public int queryCountVisitor(String date);
	/**
	 * 查询全勤员工
	 * @return List<Employee>
	 */
	
	/**
     * 查询员工出勤情况
     *
     * @param emp
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:46
     */
	List<Employee> queryEmpAttendance(String sql,Object[]param);
    
    /**
     * 查询员工加班情况
     *
     * @param emp
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:48
     */
	List<Employee> queryEmpWorkOverTime(String sql,Object[]param);

    /**
     * 查询全勤情况
     *
     * @param date
     * @return null
     * @author Tebby
     * @date 2020-08-27 15:48
     */
	List<Employee> queryAttendance(String sql,Object[]param);
	
	/**
	 * 查询加班情况
	 * @param sql
	 * @param param
	 * @return
	 * @author shebin
	 */
	List<Employee> queryWorkOverTime(String sql,Object[]param);

    /**
	 * 查询访客信息
     * @param
     * @return
     * @author Tebby
     * @date 2020-08-27 15:49
     */
    List<Visitor> queryVisitInf(String sql,Object[] param);

}
