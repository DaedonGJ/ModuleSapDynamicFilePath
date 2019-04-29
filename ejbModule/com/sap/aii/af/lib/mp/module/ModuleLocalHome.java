/**
 * 
 */
package com.sap.aii.af.lib.mp.module;

import javax.ejb.EJBLocalHome;

/**
 * @author str_gjc
 *
 */
public interface ModuleLocalHome extends EJBLocalHome {

	public com.sap.aii.af.lib.mp.module.ModuleLocal create() throws javax.ejb.CreateException;

}
