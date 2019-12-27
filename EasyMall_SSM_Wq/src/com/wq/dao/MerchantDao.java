package com.wq.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wq.domain.Merchant;

@Mapper
@Repository("merchantDao")
public interface MerchantDao {

	@Select("select * from merchant where username=#{username}")
	public Merchant findUserByMerchantName(String username) throws Exception;

	@Insert("insert into merchant(username,password,email) values(#{username},#{password},#{email})")
	public void addMerchant(Merchant merchant) throws Exception;
}
