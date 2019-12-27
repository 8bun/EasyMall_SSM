package com.wq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.wq.domain.SLog;
import com.wq.service.ILogService;

@Controller
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private ILogService logService;
	
	@RequestMapping("/findAllSysLog")
	public ModelAndView FindAllSysLog(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "15")Integer size) throws Exception{
		
		ModelAndView  mv=new ModelAndView();
		List<SLog> logList=logService.findAllSysLog(page,size);

		PageInfo<SLog> pageInfo=new PageInfo<SLog>(logList);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("psize", size);
		mv.addObject("log",logList);
		mv.setViewName("merchant-logs");
		
		return mv;
	}
	@RequestMapping("/deleteLogById")
	public String DeleteLogById(String[] ids) throws Exception{
		for(String id:ids)
			logService.deleteLogById(id);	
		return "redirect:findAllSysLog";
	}
}
