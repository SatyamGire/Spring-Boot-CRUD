package com.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springtest.entiry.GrosseryEntity;

@Repository
public interface GrosseryRepoInterface extends JpaRepository<GrosseryEntity,Integer>{
	
}