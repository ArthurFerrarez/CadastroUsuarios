package com.example.cadastroUsuario.repositories;

import com.example.cadastroUsuario.models.CadastroUsuarioModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CadastroUsuarioRepository extends PagingAndSortingRepository<CadastroUsuarioModel, Integer> {
}
