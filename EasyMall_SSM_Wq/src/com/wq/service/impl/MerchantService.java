package com.wq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.dao.MerchantDao;
import com.wq.domain.Merchant;
import com.wq.service.IMerchantService;

@Service("merchantService")
public class MerchantService implements IMerchantService{

	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public Merchant findUserByMerchantName(String username) throws Exception {
		// TODO Auto-generated method stub
		return merchantDao.findUserByMerchantName(username);
	}

	@Override
	public void addMerchant(Merchant merchant) throws Exception {
		// TODO Auto-generated method stub
		merchantDao.addMerchant(merchant);
	}

}
