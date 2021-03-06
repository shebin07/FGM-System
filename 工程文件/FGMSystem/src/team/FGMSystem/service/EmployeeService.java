/**
 * @author  Shebin Xu
 *	员工服务类	EmployeeService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;

public interface EmployeeService {
	/**
	 * 员工登录
	 * @param account
	 * @param password
	 * @return
	 */
	Object login(String account,String password);
	/**
	 * 员工签到
	 * @param emp
	 */
	void signIn(Employee emp);
	/**
	 * 员工签退
	 * @param emp
	 */
	void signOut(Employee emp);
	/**
	 * 员工查询当月出勤情况
	 * @param emp
	 */
	void queryAttendance(Employee emp);
	/**
	 * 员工查询历史出勤情况
	 * @param emp
	 * @param date
	 */
	void queryAttendance(Employee emp,String date);
	/**
	 * 员工查询当月加班情况
	 * @param emp
	 */
	void queryWorkOverTime(Employee emp);
	/**
	 * 员工查询历史加班情况
	 * @param emp
	 * @param date
	 */
	void queryWorkOverTime(Employee emp,String date);
//	/**
//	 * 员工签收快递
//	 * @param emp
//	 * @param packNum
//	 */
//	void empReceivePackage(Employee emp,String packNum);
//	/**
//	 * 员工寄快递
//	 * 自动生成快递单号yyyymmddhhmmss
//	 * @param emp
//	 */
//	void empSendPackage(Employee emp);
}
