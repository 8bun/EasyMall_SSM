package com.wq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wq.dao.ProductDao;
import com.wq.domain.Product;
import com.wq.service.IProductService;

@Service("productService")
public class ProductService implements IProductService{
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> findAllProducts()throws Exception{
		
		return productDao.findAllProducts();
	}

	@Override
	public List<Product> findProductsByCondition(String name, String category, double minPrice, double maxPrice)
			throws Exception {
		// TODO Auto-generated method stub
		return productDao.findProductsByCondition(name, category, minPrice, maxPrice);
	}

	@Override
	public Product findProductById(String id) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findProductById(id);
	}
	
	@Override
	public void updateProductNumById(String id,Integer pnum) throws Exception{
		productDao.updateProductNumById(id,pnum);
	}

	@Override
	public List<Product> findAllProductsByPage(Integer page,Integer size) throws Exception {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, size);
		return productDao.findAllProducts();
	}
	
	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.addProduct(product);
	}

	@Override
	public Integer findAllProductsNum() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findAllProductsNum();
	}

	@Override
	@Transactional
	public void deleteProductsByProductId(String id) throws Exception {
		// TODO Auto-generated method stub
		productDao.deleteProductsByProductId(id);
	}
}
