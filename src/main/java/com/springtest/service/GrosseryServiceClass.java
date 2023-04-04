package com.springtest.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtest.entiry.GrosseryEntity;
import com.springtest.repository.GrosseryRepoInterface;

@Service
public class GrosseryServiceClass implements GrosseryServiceInterface {

	@Autowired
	private GrosseryRepoInterface repo;
	
	@Override
	public GrosseryEntity addData(GrosseryEntity ge) {
		return repo.save(ge);
	}

//	this is traditional method ie. we extracted all data by findAll then iterate it to check id 
//	@Override
//	public GrosseryEntity getById(long id) {
//		GrosseryEntity ge=null;
//		
//		for(GrosseryEntity temp : repo.findAll()) {
//			if(temp.getId()==id) {
//				ge=temp;
//			}
//		}
//		return ge;
//	}
	
	@Override
	public List<GrosseryEntity> getAll() {
		return repo.findAll();
	}
	
	@Override
	public Optional<GrosseryEntity> getById(long id) {
		return repo.findById((int) id);
	}

	@Override
	public GrosseryEntity getByName(String name) {
		GrosseryEntity ge=null;
		for(GrosseryEntity temp : repo.findAll()) {
			if(temp.getName().equals(name)) {
				ge=temp;
			}
		}
		return ge;
	}

	@Override
	public GrosseryEntity update(GrosseryEntity ge) {
		return repo.save(ge);	
	}

	@Override
	public void deleteById(long id) {
//		here delete can take argument as object only but we only have id to delete object, so here we find object having specified id
//		findById only returns Optional<> interface so we collected in it and get() is method of Optional<>
		Optional<GrosseryEntity> ge=repo.findById((int)id);
		repo.delete(ge.get());
	}

	@Override
	public GrosseryEntity getByPid(int productId) {
		GrosseryEntity ge=null;
		List<GrosseryEntity> list=repo.findAll();
		for(GrosseryEntity temp : list) {
			if(temp.getProductId()==productId) {
				ge=temp;
			}
		}
		return ge;
	}

	@Override
	public List<GrosseryEntity> findByPrice(int price) {
		return repo.findByPrice(price);
	}
	
	@Override
	public List<GrosseryEntity> findByPriceBetween(int start, int end) {
		return repo.findByPriceBetween(start, end);
	}
	
	@Override
	public List<GrosseryEntity> findByQuery(String name, int price) {
		return repo.findByQuery(name, price);
	}
	
	@Override
	public List<GrosseryEntity> sortByPrice() {
		return repo.sortByPrice();
	}
}