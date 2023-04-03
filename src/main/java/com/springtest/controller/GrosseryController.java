
package com.springtest.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springtest.entiry.GrosseryEntity;
import com.springtest.service.GrosseryServiceInterface;

@RestController
//@RequestMapping("/api")
public class GrosseryController {
	
	@Autowired
	private GrosseryServiceInterface service;
	
//	to add data in database
	@PostMapping("/grossery")
	public String addData(@RequestBody GrosseryEntity ge) {
		service.addData(ge);
		return "added.....";
	}
	
	
//	@GetMapping("/grossery/{id}")
//	public GrosseryEntity getById(@PathVariable long id) {
//		return service.getById(id);
//	}

//	to extract all data from database
	@GetMapping("/grossery")
	public List<GrosseryEntity> getAll() {
		return service.getAll();
	}
	
//	to extract data from database by id
	@GetMapping("/grossery/id/{id}")
//	pathvariable means variable ie. id passed in path that transfers value to parameter passed in front of annotation
	public Optional<GrosseryEntity> getById(@PathVariable long id) {
		return service.getById(id);
	}
	
//	to extract data from database by name
	@GetMapping("/grossery/name/{name}")
	public GrosseryEntity getByName(@PathVariable String name) {
		return service.getByName(name);
	}
	
//	to updata database values
	@PutMapping("/grossery")
	public String update(@RequestBody GrosseryEntity ge) {
		service.update(ge);
		return "updated.....";
	}
	
//	to delete database record
	@DeleteMapping("/grossery/delete/{id}")
	public String deleteById(@PathVariable long id) {
		service.deleteById(id);
		return "deleted.....";
	}
	
//	to get data by productId
	@GetMapping("/grossery/pid")
//	here at the time of providing url to postman we need to pass url as 'http://localhost:9191/grossery/pid?productId=102'
//	here from pid onwards we provided ? and then name of RequestParam and then = the parameter that we want to search
//	the name value "productId" is basically from form data ie. from input tag name value ie. name="email" like that
//	if our name and parameter passed in function is same then we dont need to specify name for RequestParam
//	in this case url will be as 'http://localhost:9191/grossery/pid?name=102'
//	we can do this by path variable also but we are doing this only for change
	public GrosseryEntity getByPid(@RequestParam(name="productId") int productId) {
		return service.getByPid(productId);
	}
	
//	to get data by price
	@GetMapping("/grossery/price")
	public List<GrosseryEntity> findByPrice(@RequestParam(name="price") int price) {
		return service.findByPrice(price);
	}
	
//	to get data by price in between ranges
	@GetMapping("/grossery/pricebetween")
	public List<GrosseryEntity> findByPriceBetween(@RequestParam(name="start") int start, @RequestParam(name="end") int end) {
		return service.findByPriceBetween(start, end);
	}
//	url : http://localhost:9191/grossery/pricebetween?start=0&end=100
//	this is how we can pass two parameters in url by using &
	
//	to get data by name by firing our own query
	@GetMapping("/grossery/namequery")
	public List<GrosseryEntity> findByQuery(@RequestParam(name="name") String name, @RequestParam(name="price") int price) {
		return service.findByQuery(name, price);
	}
	
//	to get data in sorted manned by price in descending order
	@GetMapping("/grossery/sortbyprice")
	public List<GrosseryEntity> sortByPrice() {
		return service.sortByPrice();
	}
}