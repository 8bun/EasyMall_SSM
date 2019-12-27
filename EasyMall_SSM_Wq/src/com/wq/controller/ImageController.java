package com.wq.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.wq.domain.Product;
import com.wq.service.IProductService;
import com.wq.utils.VerifyCode;


@Controller
@RequestMapping("image")
public class ImageController {
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/valiImage")
	public void ValiImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//�����������Ҫ������֤��ͼƬ��	
		response.setDateHeader("Expires",-1);
		response.setHeader("Cache-Control", "no-cache");
				
		VerifyCode vc=new VerifyCode();		
		//��ͼƬ���浽response�������У�����Ӧ�������
		vc.drawImage(response.getOutputStream());	
		//��ȡͼƬ�ϵ���֤��
		String code=vc.getCode();
		//����֤���ı����浽session�С����ں��ڵ�У��
		request.getSession().setAttribute("code",code);
	}

}
