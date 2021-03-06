/**
 * @author  Shebin Xu
 *	数据分析服务类 	DataArrangeService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;

public interface DataArrangeService {
	/**
	 * 查询员工出勤情况
	 * @param emp
	 * @param date
	 */
	void queryEmpAttendance(String date);
	/**
	 * 查询员工加班情况
	 * @param emp
	 * @param date
	 */
	void queryEmpWorkOverTime(String date);
	/**
	 * 查询全勤情况
	 * @param date
	 */
	void queryAttendance(String date);
	/**
	 * 查询加班情况
	 * @param date
	 */
	void queryWorkOverTime(String date);
	/**
	 * 查询访客信息
	 * @param vis
	 */
	void queryVisitInf(String date);
	
	
}
