package com.bpm.service;

import java.util.HashMap;
import java.util.List;

public interface CatalogServiceInf {

	/**
	 * This service gets dashboard details based on user id
	 * @param userId
	 * @return
	 */
	public List getDashBoardDetails();

	public List createCatalog();

	public List editCatalog();

	public List deleteCatalog();

	public List findCatalog();

	public List findAllCatalog();

	public List getCatalogDetails();

	public HashMap saveToCart(String cartItemList);
}
