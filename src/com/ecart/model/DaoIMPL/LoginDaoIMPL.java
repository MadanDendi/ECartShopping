package com.ecart.model.DaoIMPL;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ecart.model.Cartitems;
import com.ecart.model.LoginDetails;
import com.ecart.model.dao.LoginDao;

public class LoginDaoIMPL extends HibernateDaoSupport implements LoginDao{

	@Override
	public LoginDetails validateLogin(String name, String password) {
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select loginId,fullName from LoginDetails where loginId=? and loginPassword=?",name,password);
		LoginDetails login = new LoginDetails();
		for(Object[] obj:list){
			login.setLoginId((obj[0].toString()));
			login.setFullName((obj[1].toString()));
		}
		return login;
	}

	@Override
	public Boolean registerUser(String uid, String pwd, String fname, String phn) {
		boolean updated = false;
		LoginDetails updateDetail = new LoginDetails();
		if(validateUid(uid)){
			updateDetail.setLoginId(uid);
			updateDetail.setLoginPassword(pwd);
			updateDetail.setFullName(fname);
			updateDetail.setContact(Integer.parseInt(phn));
			getHibernateTemplate().save(updateDetail);
			updated = true;
		}
		
		return updated;
	}
	
	public Boolean validateUid(String uid){
		boolean valid = false;
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find("select loginId,fullName from LoginDetails where loginId=?",uid);
		if(list.size()==0)
			valid = true;
		return valid;
		
	}

}
