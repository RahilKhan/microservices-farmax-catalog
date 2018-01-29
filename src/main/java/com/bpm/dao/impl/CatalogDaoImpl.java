package com.bpm.dao.impl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bpm.dao.inf.AbstractDao;
import com.bpm.dao.inf.CatalogDao;
import com.bpm.model.CartItem;
import com.bpm.model.CatalogItem;


@Repository("catalogdDao")
@Transactional
//public class CatalogDaoImpl extends AbstractDao<Integer, DashboardDetails> implements CatalogDao{
public class CatalogDaoImpl extends AbstractDao<Integer, CatalogItem> implements CatalogDao{
	private static Logger log = Logger.getLogger(CatalogDaoImpl.class.getName()); 
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getDashBoardDetails() {
		log.info("CatalogDaoImpl");
		
		Session session = getEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("unchecked")
		List<Integer> totalDealsUploadedResultList = getEntityManager()
                .createQuery("SELECT count(dtd.dealUniqueId) FROM DealsTempDetails dtd")
                .getResultList();
		String totalDealsUploaded = ""+totalDealsUploadedResultList.get(0);
		 
		@SuppressWarnings("unchecked")
		List<Integer> totalDealsAcceptedResultList = getEntityManager()
                .createQuery("SELECT count(dad.dealUniqueId) FROM DealsAcceptedDetails dad")
                .getResultList();
		String totalDealsAccepted = ""+totalDealsAcceptedResultList.get(0);

		@SuppressWarnings("unchecked")
		List<Integer> totalDealsRejectedResultList = getEntityManager()
                .createQuery("SELECT count(drd.dealUniqueId) FROM DealsRejectedDetails drd")
                .getResultList();
		String totalDealsRejected =  "" + totalDealsRejectedResultList.get(0);
		

		@SuppressWarnings("unchecked")
		List<Integer> totalOrderCurrencyResultList = getEntityManager()
                .createQuery("SELECT count(distinct dad.orderCurrIso) FROM DealsAcceptedDetails dad")
                .getResultList();
		String totalOrderCurrency = "" + totalOrderCurrencyResultList.get(0);

		String dealsPerCurrencySql = "select dcia.order_curr_iso, count(dcia.order_curr_iso) "
				+ " from deals_csv_import_accepted dcia, currency curr " 
				+ " where curr.CURRENCY_ISO_CODE = dcia.order_curr_iso "
				+ " group by dcia.ORDER_CURR_ISO ";
		List<List<Object>> dealsPerCurrencyResult = session.createSQLQuery(dealsPerCurrencySql).setResultTransformer(Transformers.TO_LIST).list();
		
		Map<String,Integer> dealsPerCurrencyMap = new HashMap();
		for (List<Object> object : dealsPerCurrencyResult) {
			dealsPerCurrencyMap.put((String)object.get(0),((BigInteger) object.get(1)).intValue());
		}
		
		System.out.println("\t totalDealsUploadedResult : " + totalDealsUploadedResultList.get(0)
		+"\n\t totalDealsAcceptedResult : " + totalDealsAcceptedResultList.get(0)
		+"\n\t totalDealsRejectedResult : " + totalDealsRejectedResultList.get(0)
		+ "\n\t totalOrderCurrencyResult : " + totalOrderCurrencyResultList.get(0)
		+ "\n\t dealsPerCurrencyMap : " + dealsPerCurrencyMap.toString()
		);
		
		List dashboardList = null;
		/*
		DashboardDetails dashboardBean = new DashboardDetails();
		dashboardBean.setPageName("DashBoard");
		dashboardBean.setSavingDetails("Total Deals Uploaded");
		dashboardBean.setSavingAmount(totalDealsUploaded);
		dashboardBean.setEarningDetails("Total Deals Accepted");
		dashboardBean.setEarningAmount(totalDealsAccepted);
		dashboardBean.setOwedDetails("Total Deals Rejected");
		dashboardBean.setOwedAmount(totalDealsRejected);
		dashboardBean.setExpensesDetails("Total no of dealing Currency");
		dashboardBean.setExpensesAmount(totalOrderCurrency);
		dashboardBean.setDealsPerCurrencyMap(dealsPerCurrencyMap);
		
		
		dashboardList = new ArrayList<DashboardDetails>();
		dashboardList.add(dashboardBean);
		*/
		return dashboardList;
	}

	@Override
	public List getCatalogDetails() {
		log.debug("CatalogDaoImpl.getCatalogDetails()");
		System.out.println("CatalogDaoImpl.java : getCatalogDetails()");
		
		List<CatalogItem> catalogList = new ArrayList<CatalogItem>();
		CatalogItem catalogItem = new CatalogItem();
		catalogItem.setId(1001);
		catalogItem.setName("Paracetamol O Tablet");
		catalogItem.setType("500 tablets");
		catalogItem.setManufacturer("Makers Laboratories Ltd");
		catalogItem.setCost("106.43");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("no");
		catalogList.add(catalogItem);
		
		catalogItem = new CatalogItem();
		catalogItem.setId(1002);
		catalogItem.setName("Solvin Cold Syrup");
		catalogItem.setType("60 ml Syrup");
		catalogItem.setManufacturer("Ipca Laboratories Ltd");
		catalogItem.setCost("44");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);

		catalogItem = new CatalogItem();
		catalogItem.setId(1003);
		catalogItem.setName("Corex DX Syrup");
		catalogItem.setType("100 ml syrup");
		catalogItem.setManufacturer("Pfizer Ltd");
		catalogItem.setCost("90.52");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);
		
		catalogItem = new CatalogItem();
		catalogItem.setId(1004);
		catalogItem.setName("Solvin Cold Tablet");
		catalogItem.setType("10 tablets");
		catalogItem.setManufacturer("Ipca Laboratories Ltd");
		catalogItem.setCost("38.50");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);
		

		catalogItem = new CatalogItem();
		catalogItem.setId(1005);
		catalogItem.setName("Brufen 400mg Tablet");
		catalogItem.setType("Strip of 15 tablets");
		catalogItem.setManufacturer("Abott");
		catalogItem.setCost("10.62");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);

		catalogItem = new CatalogItem();
		catalogItem.setId(1006);
		catalogItem.setName("Naxdom 250 Tablet");
		catalogItem.setType("5.05/Tablet");
		catalogItem.setManufacturer("Sun Pharmaceutical Industries Ltd");
		catalogItem.setCost("50.5");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);
		
		log.debug("\t catalogList.size() : " + catalogList.size());
		System.out.println("\t catalogList.size() :: " + catalogList.size() + "\n\t catalogList : " + catalogList.toString());
		return catalogList;
	}
	
	
	@Override
	public List<CatalogItem> createCatalog() {
		log.info("CatalogDaoImpl.createCatalog()");
		
		List catalogList = new ArrayList<CatalogItem>();
		CatalogItem catalogItem = new CatalogItem();
		catalogItem = new CatalogItem();
		catalogItem.setName("Naxdom 250 Tablet");
		catalogItem.setType("5.05/Tablet");
		catalogItem.setManufacturer("Sun Pharmaceutical Industries Ltd");
		catalogItem.setCost("50.5");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");

		catalogList.add(catalogItem);
		
		catalogItem = new CatalogItem();
		catalogItem.setName("Brufen 400mg Tablet");
		catalogItem.setType("Strip of 15 tablets");
		catalogItem.setManufacturer("Abott");
		catalogItem.setCost("10.62");
		catalogItem.setDiscount("0%");
		catalogItem.setAvailability("yes");
		catalogList.add(catalogItem);
		
		return catalogList;
	}

	@Override
	public List<CatalogItem> editCatalog() {
		log.info("CatalogDaoImpl.editCatalog()");
		return null;
	}

	@Override
	public List<CatalogItem> deleteCatalog() {
		log.info("CatalogDaoImpl.deleteCatalog()");
		return null;
	}

	@Override
	public List<CatalogItem> findCatalog() {
		log.info("CatalogDaoImpl.findCatalog()");
		return null;
	}

	@Override
	public List<CatalogItem> findAllCatalog() {
		log.info("CatalogDaoImpl.findCatalog()");
		return null;
	}

	@Override
	public HashMap saveToCart(HashMap cartMap) {
		log.info("CatalogDaoImpl.saveToCart()");
		System.out.println("CatalogDaoImpl.saveToCart()");
		System.out.println("\t cartMap : " + cartMap.toString());

		cartMap.put(1007, 1);
		return cartMap;
	}

}
