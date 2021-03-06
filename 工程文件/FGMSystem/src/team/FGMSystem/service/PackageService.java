/**
 * @author  Shebin Xu
 *	包裹服务类	PackageService
 *
 */
package team.FGMSystem.service;
import team.FGMSystem.entity.Package;
import team.FGMSystem.entity.Employee;

public interface PackageService {
	/**
	 * 快递被签收
	 * @param emp
	 * @param packNum
	 */
	void ReceivePackage(Employee emp);
	/**
	 * 快递被寄出
	 * @param emp
	 */
	void SendPackage(Employee emp);
	/**
	 * 快递进入物流（被快递公司拉走）
	 * @param emp
	 */
	void expressTakePack(Employee emp);
	/**
	 * 前台录入新到快递信息
	 */
	void insertNewPackage(Package pk);
	/**
	 * 结算当月快递费用
	 * @param date
	 * @return cost
	 */
	public double queryPackCost(String date);
}
