package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.config.UserRoles;
import com.example.demo.model.Produto;
import com.example.demo.model.User;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class Demo1Application {
	
	@Autowired
    private ProdutoRepository repository;

	
	/*
    @PostConstruct
    public void initUsers() {
       for(;;)
    	   repository.save(new Produto("DATA DATA", 231232));
    }
    */

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
