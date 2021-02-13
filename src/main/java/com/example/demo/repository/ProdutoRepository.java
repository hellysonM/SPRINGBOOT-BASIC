package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	  @Query(value = "SELECT * FROM Produto WHERE name like ?1",
			    countQuery = "SELECT count(*) FROM Produto WHERE name like ?1",
			    nativeQuery = true)
	Page<Produto> findByName(String name,Pageable pageable);
}
