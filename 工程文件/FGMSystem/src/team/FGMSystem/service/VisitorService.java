/**
 * @author  Shebin Xu
 *	访客服务类	PackageService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Visitor;

public interface VisitorService {
	/**
	 * 访客预约
	 * @param emp
	 * @param vis
	 */
	void makeAppoint(Visitor vis,String apiTime);
	/**
	 * 访客来访
	 * @param emp
	 * @param vis
	 */
	void visit(Visitor vis,String str);
}
