package com.wq.service;

import org.apache.ibatis.annotations.Insert;

import com.wq.domain.Merchant;

public interface IMerchantService {
	
	public Merchant findUserByMerchantName(String username) throws Exception;

	public void addMerchant(Merchant merchant) throws Exception;
}
