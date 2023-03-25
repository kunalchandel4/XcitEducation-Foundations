package com.XcitEducationFoundations.service;

import java.util.List;

import com.XcitEducationFoundations.Entity.Product;
import com.XcitEducationFoundations.Exceptions.NoSuchProductException;

public interface CartService {

	public List<Product> sendProductsData() throws NoSuchProductException;
}
