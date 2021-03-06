package com.hospital.medico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hospital.medico.entities.Usuario;
import com.hospital.medico.repository.IUsuarioRepository;
import com.hospital.medico.request.LoginPostRequest;
import com.hospital.medico.security.MD5Cryptography;
import com.hospital.medico.security.TokenSecurity;

import io.swagger.annotations.ApiOperation;

@Controller

public class LoginController {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	private static final String ENDPOINT = "/api/login";

	@ApiOperation("Serviço para autenticação de Usuário.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	 public ResponseEntity<String> post(@RequestBody LoginPostRequest request){
        try {
            Usuario usuario = usuarioRepository.findByLoginAndSenha(request.getLogin(), MD5Cryptography.encrypt(request.getSenha()));

            //verificar se usuario foi encontrado
            if(usuario != null) {
                return ResponseEntity.status(HttpStatus.OK).body(TokenSecurity.generateToken(usuario.getLogin()));
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}