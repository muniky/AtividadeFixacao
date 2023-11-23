package com.example.demo.repository;


import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Compromissos;

@Repository
public interface RepositoryCompromissos extends JpaRepository<Compromissos, Long>{
	    Optional<Compromissos> findById(Long id);
	    List<Compromissos> findByLocal(String local);
	    List<Compromissos> findByData(String data);
	    List<Compromissos> findByContato(String contatos);
	}


