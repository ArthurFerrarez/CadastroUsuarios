package com.example.cadastroUsuario.controllers;

import com.example.cadastroUsuario.dtos.CadastroUsuarioDto;
import com.example.cadastroUsuario.models.CadastroUsuarioModel;
import com.example.cadastroUsuario.services.CadastroUsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CadastroUsuarioController {

    @Autowired
    CadastroUsuarioService cadastroUsuarioService;

    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid CadastroUsuarioDto cadastroUsuarioDto){
        var cadastroUsuarioModel = new CadastroUsuarioModel();
        BeanUtils.copyProperties(cadastroUsuarioDto, cadastroUsuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroUsuarioService.save(cadastroUsuarioModel));
    }

    @GetMapping
    public ResponseEntity<Iterable<CadastroUsuarioModel>> getAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable(value = "id")int id){
        Optional<CadastroUsuarioModel> cadastroUsuarioModelOptional = cadastroUsuarioService.findById(id);
        if(!cadastroUsuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cadastroUsuarioModelOptional.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") int id,
                                                @RequestBody @Valid CadastroUsuarioDto cadastroUsuarioDto){
        Optional<CadastroUsuarioModel> cadastroUsuarioModelOptional = cadastroUsuarioService.findById(id);
        if(!cadastroUsuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var cadastroUsuarioModel = new CadastroUsuarioModel();
        BeanUtils.copyProperties(cadastroUsuarioDto, cadastroUsuarioModel);
        cadastroUsuarioModel.setId(cadastroUsuarioModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(cadastroUsuarioService.save(cadastroUsuarioModel));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuarioById(@PathVariable(value = "id") int id){
        Optional<CadastroUsuarioModel> cadastroUsuarioModelOptional =cadastroUsuarioService.findById(id);
        if(!cadastroUsuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        cadastroUsuarioService.delete(cadastroUsuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }


}
