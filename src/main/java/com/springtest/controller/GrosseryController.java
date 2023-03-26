package com.springtest.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springtest.entiry.GrosseryEntity;
import com.springtest.service.GrosseryServiceInterface;

@RestController
@RequestMapping("/api")
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
	@GetMapping("/grossery/getbyid/{id}")
//	pathvariable means variable ie. id passed in path that transfers value to parameter passed in front of annotation
	public Optional<GrosseryEntity> getById(@PathVariable long id) {
		return service.getById(id);
	}
	
//	to extract data from database by name
	@GetMapping("/grossery/getbyname/{name}")
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
}
