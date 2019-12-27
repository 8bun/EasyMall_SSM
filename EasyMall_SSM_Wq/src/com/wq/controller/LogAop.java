package com.wq.controller;

import com.wq.domain.Merchant;
import com.wq.domain.SLog;
import com.wq.domain.User;
import com.wq.service.ILogService;

import org.aspectj.lang.reflect.MethodSignature;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.awt.Robot;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ILogService logService;

    private Date visitTime; 
    private Class clazz; 
    private Method method;

    @Before("execution(* com.wq.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
        	Signature sig=jp.getSignature();
        	MethodSignature  msig=null;
        	if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
            }
        	msig=(MethodSignature) sig;
        	Class[] parameterTypes = msig.getMethod().getParameterTypes();
        	method = jp.getTarget().getClass().getMethod(methodName, parameterTypes);
        }
    }
    //����֪ͨ
    @After("execution(* com.wq.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //��ȡ���ʵ�ʱ��
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class&&clazz!=LogController.class&&clazz!=UserController.class) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
         
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                    String ip = request.getRemoteAddr();
                    Merchant merchant=(Merchant)request.getSession().getAttribute("merchant");
                    User user=(User)request.getSession().getAttribute("user");  
                    if(merchant!=null&&user==null){
	                    String username = merchant.getUsername();
	                    SLog log = new SLog();
	                    log.setId(UUID.randomUUID().toString());
	                    log.setExecutionTime((int)time); 
	                   
	                    log.setIp(ip);
	                    log.setMethod("[��] " + clazz.getName() + "[����] " + method.getName());
	                    log.setUrl(url);
	                    log.setUsername(username);
	                    log.setVisitTime(visitTime);
	                    logService.save(log);
                    }
                }
            }
        }

    }
}
