package com.adventnet.zoho.crmplus.apps.action;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.adventnet.iam.IAMUtil;
import com.adventnet.iam.User;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.zoho.crmplus.common.CMActionSupport;
import com.adventnet.zoho.crmplus.common.CommonUtil;
import com.adventnet.zoho.crmplus.common.ZCConstants;
import com.adventnet.zoho.crmplus.iam.bean.ServiceMappingBean;
import com.adventnet.zoho.crmplus.iam.util.CrmplusIAMAPI;

public class CreateAccountForServices extends CMActionSupport{

	private static final Logger LOGGER  = Logger.getLogger(CreateAccountForServices.class.getName());
	@Override
	public String execute() {
		
		
		String zgid = (String)request.getAttribute("sharedBy");
		Long zuid = null;
		
		if(request.getAttribute("USERZUID") != null){
			zuid = (Long) (request.getAttribute("USERZUID"));
		}
		try{
			if (CrmplusIAMAPI.canCreateServices(zgid)){
			
				/*	create account in services	 */
				ServiceMappingBean serviceMappingBean = ( ServiceMappingBean ) BeanUtil.lookup( "ServiceMappingBean", zgid ); //No I18N
				User iamUser = IAMUtil.getCurrentUser();
				String authToken = CommonUtil.generateAuthtoken(iamUser, "ZohoDirectory/directoryapi");//No I18N
				serviceMappingBean.pushServiceMappingToIAM(""+zuid, zgid, authToken, IAMUtil.getCurrentTicket()); //No I18N
				CommonUtil.pushStatsToMI(ZCConstants.MI_CRMPLUS_NEW_ACCOUNT_CREATION);
			}
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception in creating account for services zgid==>"+zgid,e); //No I18N
		}
		return null;
	}
	
}
