package com.XcitEducationFoundations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.XcitEducationFoundations.Entity.JSONObject;
import com.XcitEducationFoundations.Entity.Product;
import com.XcitEducationFoundations.Exceptions.NoSuchProductException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate rest;

	@Override
	public List<Product> sendProductsData() throws NoSuchProductException {
		// TODO Auto-generated method stub
		JSONObject data = rest.getForObject("https://dummyjson.com/products", JSONObject.class);
		if(data == null || data.getProducts() == null) {
			 throw new NoSuchProductException("No Such Product FOund");
		}
		return data.getProducts();
	}

}
