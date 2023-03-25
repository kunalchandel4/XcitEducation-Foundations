package com.XcitEducationFoundations.Entity;

import java.util.List;

import lombok.Data;

@Data
public class Product {

	private Integer id;
	private String title;
	private String description;
	private Integer price;
	private Integer discountPercentage;
	private Integer rating;

	private Integer stock;
	private String brand;
	private String category;

	private String thumbnail;
	private List<String> images;

}