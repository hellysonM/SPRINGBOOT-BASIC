package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ProdutoRepository;
import com.example.demo.model.Produto;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired 
	ProdutoRepository pr;
	
	@GetMapping
	public Page<Produto> getAll(@RequestParam("page") int page,@RequestParam("rows") int rows,
			@RequestParam(name = "name",defaultValue = "%",required = false) String name,@RequestParam("sortField") String sortField,
			@RequestParam("sortOrder") int sortOrder){
		
		Pageable pageable;
		
		if(sortOrder==1)
			
		pageable = PageRequest.of(page, rows,Sort.by(sortField).ascending());
		
		else
			
		pageable = PageRequest.of(page, rows,Sort.by(sortField).descending());	
		
		
	
			return pr.findByName(name, pageable);
	}

	
	
}
