package com.hospital.medico.controllers;

import javax.transaction.Transactional;

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
import com.hospital.medico.request.AccountPostRequest;
import com.hospital.medico.security.MD5Cryptography;

import io.swagger.annotations.ApiOperation;

@Controller

@Transactional

public class AccoutController {

	@Autowired

	private IUsuarioRepository usuarioRepository;

	private static final String ENDPOINT = "/api/account";

	@ApiOperation("Serviço de criação de conta de usuário.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody AccountPostRequest request) {

		try {

			// verificar se o login informado existe no banco de dados

			if (usuarioRepository.findbyLogin(request.getLogin()) != null) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST)

						.body("O login informado ja esta cadastrado no sistema, tente outro.");

			}

			// cadastrando o usuario

			Usuario usuario = new Usuario();

			usuario.setNome(request.getNome());

			usuario.setLogin(request.getLogin());

			usuario.setSenha(MD5Cryptography.encrypt(request.getSenha()));

			usuarioRepository.save(usuario);

			return ResponseEntity.status(HttpStatus.OK)

					.body("Conta de usuario criada com sucesso!");

		}

		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)

					.body(e.getMessage());

		}

	}

}