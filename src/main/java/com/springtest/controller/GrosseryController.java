
package com.springtest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<GrosseryEntity> addData(@RequestBody GrosseryEntity ge) {
		try {
			GrosseryEntity temp=service.addData(ge);
			return ResponseEntity.ok(temp);
		}
		catch(Exception ex) {
			System.out.printf("\nException occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
//	@GetMapping("/grossery/{id}")
//	public GrosseryEntity getById(@PathVariable long id) {
//		return service.getById(id);
//	}

//	to extract all data from database
	@GetMapping("/grossery")
	public ResponseEntity<List<GrosseryEntity>> getAll() {
		try {
			List<GrosseryEntity> list=service.getAll();
			if(list.size()<=0) {
				System.out.printf("\ncontent not available in database");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(list); 			
		}
		catch(Exception ex) {
			System.out.println("Exception occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	to extract data from database by id, passing id like these mens we are passing values dynamically
	@GetMapping("/grossery/id/{id}")
//	pathvariable means variable ie. id passed in path that transfers value to parameter passed in front of annotation
	public ResponseEntity<Optional<GrosseryEntity>> getById(@PathVariable long id) {
		try {
			Optional<GrosseryEntity> ge = service.getById(id);
			if(ge.get()==null) {
				System.out.printf("\nno matching content available");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//				it will not print 201 status ie, No_content cause we are returning optional<>, so optional is used to handle NoSuchElement exception caues it only returns object or null value 
//				so it will show 500 status ie. internal server error
//				if we were returning simple object then it will show our mentioned status 
			}
			return ResponseEntity.ok(ge);
		}
		catch(Exception ex) {
			System.out.println("Exception occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	to extract data from database by name
	@GetMapping("/grossery/name/{name}")
	public ResponseEntity<GrosseryEntity> getByName(@PathVariable String name) {
		try {
			GrosseryEntity ge=service.getByName(name);
			return ResponseEntity.ok(ge);
		}
		catch(Exception ex) {
			System.out.printf("\nException occured : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	to updata database values
	@PutMapping("/grossery")
	public ResponseEntity<GrosseryEntity> update(@RequestBody GrosseryEntity ge) {
		try {
			GrosseryEntity temp = service.update(ge);
			return ResponseEntity.ok(temp);	
		}
		catch(Exception ex) {
			System.out.printf("\nException occured : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
//	in put mapping if data is already present then the changes made in perticular object gets save or updated
//	for to check we need to mention primary key at the time of updating data in raw body, as we used to add object as ignoring primary key cause it is auto increment so it will add automatically 
//	but at the time of put means updata data we need to mention primary key id so that spring will check if that id is present or not, if present then changes will be updated else new object gets store in database
//	or you can also mention id/primary key dynamically in the postman to differenciate which object is to update 
//	here in our case we didn't pass
	
//	to delete database record
//	delete method in JpaRepository returns void 
	@DeleteMapping("/grossery/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		try {
			service.deleteById(id);			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception ex) {
			System.out.printf("\nException occured : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();			
		}
	}
	
//	to get data by productId
	@GetMapping("/grossery/pid")
//	here at the time of providing url to postman we need to pass url as 'http://localhost:9191/grossery/pid?productId=102'
//	here from pid onwards we provided ? and then name of RequestParam and then = the parameter that we want to search
//	the name value "productId" is basically from form data ie. from input tag name value ie. name="email" like that
//	if our name and parameter passed in function is same then we dont need to specify name for RequestParam
//	in this case url will be as 'http://localhost:9191/grossery/pid?name=102'
//	we can do this by path variable also but we are doing this only for change
	public ResponseEntity<GrosseryEntity> getByPid(@RequestParam(name="productId") int productId) {
		try {
			GrosseryEntity ge = service.getByPid(productId);	
			if(ge==null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(ge);
		}
		catch(Exception ex) {
			System.out.printf("\nException occured : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();			
		}
	}
	
//	to get data by price
//	we have taken list<> here cause there may be multiple products of same price
	@GetMapping("/grossery/price")
	public ResponseEntity<List<GrosseryEntity>> findByPrice(@RequestParam(name="price") int price) {
		try {
			List<GrosseryEntity> list = service.findByPrice(price);
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(list);
		}
		catch(Exception ex) {
			System.out.printf("\nException occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	to get data by price in between ranges
	@GetMapping("/grossery/pricebetween")
	public ResponseEntity<List<GrosseryEntity>> findByPriceBetween(@RequestParam(name="start") int start, @RequestParam(name="end") int end) {
		try {
			List<GrosseryEntity> list = service.findByPriceBetween(start, end);
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(list);
		}
		catch(Exception ex) {
			System.out.printf("\nException occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
//	url : http://localhost:9191/grossery/pricebetween?start=0&end=100
//	this is how we can pass two parameters in url by using &
	
//	to get data by name by firing our own query
//	here query is to find by name && price so there can be only one object of same name and price if constraint applied to table for name is unique
//	but if constraint is not applied then there may be possibility of multiple objects with same content so we have taken list<> here 
	@GetMapping("/grossery/namequery")
	public ResponseEntity<List<GrosseryEntity>> findByQuery(@RequestParam(name="name") String name, @RequestParam(name="price") int price) {
		try {
			List<GrosseryEntity> list = service.findByQuery(name, price);
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(list);
		}
		catch(Exception ex) {
			System.out.printf("\nException occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	to get data in sorted manned by price in descending order
	@GetMapping("/grossery/sortbyprice")
	public ResponseEntity<List<GrosseryEntity>> sortByPrice() {
		try {
			List<GrosseryEntity> list = service.sortByPrice();
			if(list.size()<=0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(list);
		}
		catch(Exception ex) {
			System.out.printf("\nException occurred : "+ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}