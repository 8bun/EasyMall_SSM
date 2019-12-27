package com.wq.service;

import java.util.List;

import com.wq.domain.SLog;

public interface ILogService {
	
	void save(SLog sLog) throws Exception;
	
	public List<SLog> findAllSysLog(Integer page,Integer size) throws Exception;
	
	public void deleteLogById(String id) throws Exception;
}
