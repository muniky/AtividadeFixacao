package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Compromissos;
import com.example.demo.repository.RepositoryCompromissos;


@RestController
@RequestMapping("/compromissos")
public class ControllerCompromissos {
	@Autowired
	RepositoryCompromissos repo;
	
	@GetMapping()
	public ResponseEntity<List<Compromissos>>getCompromissos(){
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Compromissos> inserirCompromissos(@RequestBody Compromissos compromissos){
		Compromissos ct = repo.save(compromissos);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Compromissos> alterarCompromissos(@PathVariable("id") Long idcompromissos,
			@RequestBody Compromissos compromissos){
		Optional<Compromissos> opCompromissos = repo.findById(idcompromissos);
				try {
					Compromissos ct = opCompromissos.get();
					ct.setLocal(compromissos.getLocal());
					ct.setContatos(compromissos.getContato());
					ct.setData(compromissos.getData());
					ct.setHora(compromissos.getHora());
					ct.setStatus(compromissos.getStatus());
					ct.setIdcontatos(compromissos.getIdcontatos());
					repo.save(ct);
					return ResponseEntity.status(HttpStatus.OK).body(ct);
				}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Compromissos>getUmCompromissos(@PathVariable("id")Long id){
		Optional<Compromissos> opCompromissos = repo.findById(id);
		try {
			Compromissos ct = opCompromissos.get();
			return ResponseEntity.status(HttpStatus.OK).body(ct);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
		
	@GetMapping("/local/{local}")
	public ResponseEntity<List<Compromissos>> getCompromissosPorLocal(@PathVariable("local") String local) {
	    List<Compromissos> compromissos = repo.findByLocal(local);
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}
	
	@GetMapping("/data/{datacomp}")
	public ResponseEntity<List<Compromissos>> getCompromissosPorData(@PathVariable("datacomp") String data) {
	    List<Compromissos> compromissos = repo.findByData(data);
	    
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}
	
	@GetMapping("/contatos/{contatos}")
	public ResponseEntity<List<Compromissos>> getCompromissosContatos(@PathVariable("contatos") String contatos) {
	    List<Compromissos> compromissos = repo.findByContato(contatos);
	    
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}

		
	@DeleteMapping("/{id}")
	public ResponseEntity<Compromissos>DeletarUmCompromissos(@PathVariable("id")Long id){
		Optional<Compromissos> opCompromissos = repo.findById(id);
		try {
			Compromissos ct = opCompromissos.get();
			repo.delete(ct);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ct);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
}
