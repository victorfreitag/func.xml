package com.funcionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcionario.entities.Funcionario;
import com.funcionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private FuncionarioRepository livroRepository;

	@Autowired
	public FuncionarioService(FuncionarioRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	public List<Funcionario> buscarTodos() {
		return livroRepository.findAll();
	}

	public Funcionario buscarId(Long id) {
		Optional<Funcionario> Pedido = livroRepository.findById(id);
		return Pedido.orElse(null);
	}

	public Funcionario salvar(Funcionario usuario) {
		return livroRepository.save(usuario);
	}

	public Funcionario alterar(Long id, Funcionario alterarprod) {
		Optional<Funcionario> existe = livroRepository.findById(id);
		if (existe.isPresent()) {
			alterarprod.setId(id);
			return livroRepository.save(alterarprod);
		}
		return null;
	}

	public boolean apagar(Long id) {
		Optional<Funcionario> existe = livroRepository.findById(id);
		if (existe.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		}

		return false;
	}

}
