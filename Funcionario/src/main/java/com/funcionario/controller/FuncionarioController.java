package com.funcionario.controller;

import java.util.List;

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

import com.funcionario.entities.Funcionario;
import com.funcionario.service.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Funcioario", description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	private final FuncionarioService funcionarioService;

	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario por ID")
	public ResponseEntity<Funcionario> buscarId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.buscarId(id);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuarios")
	public ResponseEntity<List<Funcionario>> buscartodos() {
		List<Funcionario> funcionario = funcionarioService.buscarTodos();
		return ResponseEntity.ok(funcionario);
	}

	@PostMapping("/")
	@Operation(summary = "Inserindo Dados")
	public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario) {
		Funcionario salvar = funcionarioService.salvar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
	}

	@PutMapping("/")
	@Operation(summary = "Aterando Dados")
	public ResponseEntity<Funcionario> alterar(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
		Funcionario alterar = funcionarioService.alterar(id, funcionario);
		if (alterar != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletando Dados")
	public ResponseEntity<Funcionario> apagar(@PathVariable Long id) {
		boolean apagar = funcionarioService.apagar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}