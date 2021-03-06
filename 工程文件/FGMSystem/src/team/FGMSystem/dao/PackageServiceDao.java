/**
 * @author  Shebin Xu
 *	包裹服务接口类	PackageServiceDao
 *
 */
package team.FGMSystem.dao;

import java.util.List;

import team.FGMSystem.entity.*;
import team.FGMSystem.entity.Package;

public interface PackageServiceDao {
	/**
	 * 签收快递
	 * @param empNum
	 * @param packNum
	 * @return count
	 */
	public  List<Package> alterReceivePackage(String sql, Object[] param);
	/**
	 * 寄出快递
	 * @param empNum
	 * @return count
	 */
	public int insertSendPackage(String sql, Object[] param);
	/**
	 * 快递公司取走快递
	 * @return count
	 */
	public int alterExpressTakePack(String sql, Object[] param);
	
	/**
	 * 结算当月快递费用
	 */
	public List<Package> queryPackCost(String sql,Object[]param);
}
