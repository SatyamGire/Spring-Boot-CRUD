package com.springtest.service;

import java.util.*;

import com.springtest.entiry.GrosseryEntity;

public interface GrosseryServiceInterface {
	
	GrosseryEntity addData(GrosseryEntity ge);

	//	GrosseryEntity getById(long id);
	
	List<GrosseryEntity> getAll();
	
	Optional<GrosseryEntity> getById(long id);
	
	GrosseryEntity getByName(String name);
	
	GrosseryEntity update(GrosseryEntity ge);
	
	void deleteById(long id);
	
	GrosseryEntity getByPid(int productId);
	
	List<GrosseryEntity> findByPrice(int price);
	
	List<GrosseryEntity> findByPriceBetween(int start, int end);
	
	List<GrosseryEntity> findByQuery(String name, int price);
	
	List<GrosseryEntity> sortByPrice();
}