/**
 * 
 */
package com.sap.adaptermodule;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.sap.aii.af.lib.mp.module.Module;
import com.sap.aii.af.lib.mp.module.ModuleContext;
import com.sap.aii.af.lib.mp.module.ModuleData;
import com.sap.aii.af.lib.mp.module.ModuleException;
import com.sap.engine.interfaces.messaging.api.Message;
import com.sap.engine.interfaces.messaging.api.MessagePropertyKey;
import com.sap.engine.interfaces.messaging.api.exception.InvalidParamException;
import com.sap.tc.logging.Category;
import com.sap.test.LogWriterCategory;

/**
 * @author str_gjc
 * 
 */
public class GetFilePathBean implements SessionBean, Module {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170518182888863266L;

	@Override
	public ModuleData process(ModuleContext moduleContenxt, ModuleData inputModuleData) throws ModuleException {
		String path = (String) moduleContenxt.getContextData("pathPattern");

		LogWriterCategory log = null;
		log = new LogWriterCategory(Category.APPLICATIONS, this.getClass());
		Message msg = null;
		String dynamicpath = "";

		try {
			log.warning("Entra en el try");
			log.warning("llenamos el mensaje");
			msg = (Message) inputModuleData.getPrincipalData();
			MessagePropertyKey mpk = new MessagePropertyKey("Directory", "http://sap.com/xi/XI/System/File");
			log.warning("Leemos el  directory");
			dynamicpath = msg.getMessageProperty(mpk);
			log.warning("El directory es " + dynamicpath);

			dynamicpath = path + procesarFilePath(dynamicpath);
			log.warning("y ahora es " + dynamicpath);
			log.warning("Escribimos la propiedad" + dynamicpath);
			msg.setMessageProperty(mpk, dynamicpath);

		} catch (InvalidParamException e) {

			log.error("Invalido el parametro de exception");
		}
		inputModuleData.setPrincipalData(msg);
		return inputModuleData;
	}

	private static String procesarFilePath(String in) {
		String out = "no found";
		String pattern = ".*(/[HQ|Field]{2,5}/.*)";
		Pattern r = Pattern.compile(pattern);

		Matcher matc = r.matcher(in);
		if (matc.find())
			out = matc.group(1);

		return out;
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {

	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbCreate() throws javax.ejb.CreateException {

	}

}
