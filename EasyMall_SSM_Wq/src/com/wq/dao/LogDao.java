package com.wq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wq.domain.SLog;

@Mapper
@Repository("logDao")
public interface LogDao {

	@Insert("insert into slog(id,visitTime,username,ip,url,executionTime,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
	void save(SLog sLog) throws Exception;
	
	@Select("select * from slog")
	List<SLog> findAllSysLog() throws Exception;
	
	@Delete("delete from slog where id=#{id}")
	public void deleteLogById(String id) throws Exception;
}
