package com.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funcionario.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
