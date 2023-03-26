package com.springtest.service;

import java.util.*;
import com.springtest.entiry.GrosseryEntity;

public interface GrosseryServiceInterface {
	
	void addData(GrosseryEntity ge);

	//	GrosseryEntity getById(long id);
	
	List<GrosseryEntity> getAll();
	
	Optional<GrosseryEntity> getById(long id);
	
	GrosseryEntity getByName(String name);
	
	void update(GrosseryEntity ge);
	
	void deleteById(long id);
}