package com.bpm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpm.dao.inf.CurrencyDao;
import com.bpm.dao.inf.CatalogDao;
import com.bpm.model.CatalogItem;
import com.bpm.model.Currency;
import com.google.gson.Gson;



@Service("catalogService")
@Transactional
public class CatalogServiceImpl implements CatalogServiceInf{

	@Autowired
	CurrencyDao currencyDao;

	@Autowired
	CatalogDao catalogDao;

	
	@Override
	public List getDashBoardDetails() {
		System.out.println("CatalogServiceImpl.getDashBoardDetails()");
		List catalogList = catalogDao.getDashBoardDetails();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		List<Currency> currencyList = currencyDao.getAllCurrency();
		System.out.println("\t currencyList.size() : " + currencyList.size());
		
		return catalogList;
	}
	
	@Override
	public List getCatalogDetails() {
		System.out.println("CatalogServiceImpl.getCatalogDetails()");
		List catalogList = catalogDao.getCatalogDetails();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		return catalogList;
		
	}


	@Override
	public List createCatalog() {
		System.out.println("CatalogServiceImpl.createCatalog()");
		List<CatalogItem> catalogList = catalogDao.createCatalog();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		List<Currency> currencyList = currencyDao.getAllCurrency();
		System.out.println("\t currencyList.size() : " + currencyList.size());
		
		return catalogList;
	}

	@Override
	public List editCatalog() {
		System.out.println("DashboardServiceImpl.editCatalog()");
		List<CatalogItem> catalogList = catalogDao.editCatalog();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		List<Currency> currencyList = currencyDao.getAllCurrency();
		System.out.println("\t currencyList.size() : " + currencyList.size());
		
		return catalogList;
	}

	@Override
	public List deleteCatalog() {
		System.out.println("DashboardServiceImpl.deleteCatalog()");
		List<CatalogItem> catalogList = catalogDao.deleteCatalog();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		List<Currency> currencyList = currencyDao.getAllCurrency();
		System.out.println("\t currencyList.size() : " + currencyList.size());
		
		return catalogList;
	}

	@Override
	public List findCatalog() {
		System.out.println("DashboardServiceImpl.findCatalog()");
		List<CatalogItem> catalogList = catalogDao.findCatalog();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		List<Currency> currencyList = currencyDao.getAllCurrency();
		System.out.println("\t currencyList.size() : " + currencyList.size());
		
		return catalogList;
	}

	@Override
	public List findAllCatalog() {
		System.out.println("DashboardServiceImpl.findAllCatalog()");

		List<CatalogItem> catalogList = catalogDao.findAllCatalog();
		System.out.println("\t catalogList.size() : " + catalogList.size());
		
		return catalogList;
	}

	@Override
	public HashMap saveToCart(String cartItemList) {
		System.out.println("\t CatalogServiceImpl.java : saveToCart ");
		System.out.println("\t cartItemList : " + cartItemList);
        Gson gsonObj = new Gson();

        HashMap cartMap = gsonObj.fromJson(cartItemList, HashMap.class);
		System.out.println("\t cartMap : " + cartMap.toString()); 

		HashMap cartMapResponse = catalogDao.saveToCart(cartMap);
		System.out.println("\t cartMapResponse.size() : " + cartMapResponse.size());
		
		return cartMapResponse;
	}

}
