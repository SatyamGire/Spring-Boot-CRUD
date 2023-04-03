package com.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springtest.entiry.GrosseryEntity;
import java.util.*;

@Repository
public interface GrosseryRepoInterface extends JpaRepository<GrosseryEntity,Integer>{
	
//	now we want to find product from price, we can do this by our own logic in service but there is another way by declaring method in repository and JPA will handle its body and execution automatically
	public List<GrosseryEntity> findByPrice(int price);
	
//	now to print objects that are presnt in between shown price range
	public List<GrosseryEntity> findByPriceBetween(int start, int end);
	
//	now to execute our own query where we want to find data by name and price
	@Query(value="select *from grossery where name='name' && price=price", nativeQuery=true)
	public List<GrosseryEntity> findByQuery(@Param("name") String name,@Param("price") int price);
//	here @param is used to pass value inside query
//	values that we passed of name and price from service method that are catched by repo method paramters
//	then these values are passed in @Param variables, then param passes these values in query
//	in query name and price are column name and 'name' and price is value passed by param
	
//	now we want to sort data by price in descending order
	@Query(value="select *from grossery order by price desc", nativeQuery=true)
	public List<GrosseryEntity> sortByPrice();
}