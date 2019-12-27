package com.wq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wq.dao.LogDao;
import com.wq.domain.SLog;
import com.wq.service.ILogService;

@Service("logService")
@Transactional
public class LogService implements ILogService {

	@Autowired
	private LogDao logDao;
	
	@Override
	public List<SLog> findAllSysLog(Integer page,Integer size) throws Exception {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, size);
		return logDao.findAllSysLog();
	}

	@Override
	public void save(SLog sLog) throws Exception {
		// TODO Auto-generated method stub
		logDao.save(sLog);
	}

	@Override
	@Transactional
	public void deleteLogById(String id) throws Exception {
		// TODO Auto-generated method stub
		logDao.deleteLogById(id);
	}

}
