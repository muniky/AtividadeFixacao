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
import com.example.demo.entities.Contatos;
import com.example.demo.repository.RepositoryContatos;

@RestController
@RequestMapping("/contatos")
public class ControllerContatos {

	@Autowired
	RepositoryContatos repo;

	@GetMapping()
	public ResponseEntity<List<Contatos>> getContatos() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}

	@PostMapping()
	public ResponseEntity<Contatos> inserirContatos(@RequestBody Contatos contatos) {
		Contatos ct = repo.save(contatos);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}

	@PutMapping("/{idcontatos}")
	public ResponseEntity<Contatos> alterarContatos(@PathVariable("idcontatos") Long idcontatos,
			@RequestBody Contatos contatos) {
		Optional<Contatos> opContatos = repo.findById(idcontatos);
		try {
			Contatos ct = opContatos.get();
			ct.setNome(contatos.getNome());
			ct.setEmail(contatos.getEmail());
			repo.save(ct);
			return ResponseEntity.status(HttpStatus.OK).body(contatos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contatos> getUmContatos(@PathVariable("id") long id) {
		Optional<Contatos> opContatos = repo.findById(id);
		try {
			Contatos ct = opContatos.get();
			return ResponseEntity.status(HttpStatus.OK).body(ct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Contatos> excluirUmContatos(@PathVariable("id") long id) {
		Optional<Contatos> opContatos = repo.findById(id);
		try {
			Contatos ct = opContatos.get();
			repo.delete(ct);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
