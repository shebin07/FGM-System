/**
 * @author  Shebin Xu
 *	�ÿͷ�����	PackageService
 *
 */
package team.FGMSystem.service;

import team.FGMSystem.entity.Employee;
import team.FGMSystem.entity.Visitor;

public interface VisitorService {
	/**
	 * �ÿ�ԤԼ
	 * @param emp
	 * @param vis
	 */
	void makeAppoint(Visitor vis,String apiTime);
	/**
	 * �ÿ�����
	 * @param emp
	 * @param vis
	 */
	void visit(Visitor vis,String str);
}
