package com.example.cadastroUsuario.services;


import com.example.cadastroUsuario.models.CadastroUsuarioModel;
import com.example.cadastroUsuario.repositories.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    CadastroUsuarioRepository cadastroUsuarioRepository;

    @Transactional
    public CadastroUsuarioModel save(CadastroUsuarioModel cadastroUsuarioModel) {
        return cadastroUsuarioRepository.save(cadastroUsuarioModel);
    }

    public Iterable<CadastroUsuarioModel> findAll() {
        return cadastroUsuarioRepository.findAll();
    }

    public Optional<CadastroUsuarioModel> findById(int id) {
        return cadastroUsuarioRepository.findById(id);
    }




    @Transactional
    public void delete(CadastroUsuarioModel cadastroUsuarioModel) {
        cadastroUsuarioRepository.delete(cadastroUsuarioModel);
    }
}