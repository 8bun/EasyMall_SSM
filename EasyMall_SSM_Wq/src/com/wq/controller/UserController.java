package com.wq.controller;

import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wq.domain.User;
import com.wq.service.IUserService;
import com.wq.service.impl.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public void login(String username,String password,String remname,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//У������
		if(username == null || "".equals(username)){
			request.setAttribute("LoginMsg","�û�������Ϊ�գ�");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(password == null || "".equals(password)){
			request.setAttribute("LoginMsg","���벻��Ϊ�գ�");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}	
		if(password.length()<6||password.length()>16){
			request.setAttribute("LoginMsg","���볤��Ӧ��6~16֮�䣡");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//��ס�û���
		if("true".equals(remname)){
			Cookie cookie=new Cookie("remname",URLEncoder.encode(username,"utf-8"));
			cookie.setMaxAge(60*60*24*30);   //����һ������Ч��
			cookie.setPath(request.getContextPath()+"/");  //���÷���·��
			response.addCookie(cookie);
		}else{
			Cookie cookie=new Cookie("remname",URLEncoder.encode(username,"utf-8"));
			cookie.setMaxAge(0); 
			cookie.setPath(request.getContextPath()+"/");  //���÷���·��
			response.addCookie(cookie);
		}	
		User user = userService.findUserByUserName(username);
		if(user!=null){
			String corr_password=user.getPassword();
			if(corr_password.equals(password)){
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		}
		else{
			request.setAttribute("LoginMsg","�û������������,�����µ�¼��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}
	@RequestMapping("/register")
	public void register(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//�����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String password2 = request.getParameter("password2").trim();
		String nickname = request.getParameter("nickname").trim();
		String email = request.getParameter("email").trim();
		String valistr = request.getParameter("valistr").trim();
		
		//У������
		if(username == null || "".equals(username)){
			request.setAttribute("msg","�û�������Ϊ�գ�");
			//ת����ע��ҳ�������ʾ
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if(password == null || "".equals(password)){
			request.setAttribute("msg","���벻��Ϊ�գ�");
			//ת����ע��ҳ�������ʾ
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if(password2 == null || "".equals(password2)){
			request.setAttribute("msg","���ٴ��������룡");
			//ת����ע��ҳ�������ʾ
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if(!password.equals(password2)){
			request.setAttribute("msg", "�����������벻һ��");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if(email == null || "".equals(email)){
			request.setAttribute("msg","�����ַ����Ϊ�գ�");
			//ת����ע��ҳ�������ʾ
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			request.setAttribute("msg", "�����ַ��ʽ����");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		if( valistr== null || "".equals(valistr)){
			request.setAttribute("msg","��֤�벻��Ϊ�գ�");
			//ת����ע��ҳ�������ʾ
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		// ��֤���Ƿ���ȷУ��
		String code=(String)request.getSession().getAttribute("code");
		if(!valistr.equalsIgnoreCase(code)){//�����ִ�Сд
			request.setAttribute("msg","��֤�벻��ȷ��");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		//����Ƿ��ظ��ύ
		String token1=request.getSession().getAttribute("token").toString();
		String token2=request.getParameter("token");
		if(token1==null||token2==null||!token1.equals(token2)){
			throw new RuntimeException("��Ҫ�ظ��ύ���ݣ�");
		}else{
			//��ȷ������
			//���session��洢��tokenֵ�������request����token����ֵ����ʱ
			//session��洢��tokenֵ��request����token����ֵ��ͬ�����֮����ʾ�ظ��ύ
			request.getSession().removeAttribute("token");  
		}
		User user=userService.findUserByUserName(username);
		
		if(user!=null){
			request.setAttribute("msg","���û����ѱ�ע�ᣡ");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		else{	
			User newUser=new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setEmail(email);
			newUser.setNickname(nickname);
			userService.addUser(newUser);
			//ע��ɹ�������ʾ��Ϣ, 3��֮����ת����ҳ
			response.getWriter().write("<h1 style='text-align:center; color:red;'>��ϲ��, ע��ɹ�! 3��֮����ת����ҳ...</h1>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		}
	}

}
