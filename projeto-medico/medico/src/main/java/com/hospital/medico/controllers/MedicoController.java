package com.hospital.medico.controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hospital.medico.entities.Medico;
import com.hospital.medico.repository.IMedicoRepository;
import com.hospital.medico.request.MedicoGetResponse;
import com.hospital.medico.request.MedicoPostRequest;
import com.hospital.medico.request.MedicoPutRequest;

import io.swagger.annotations.ApiOperation;

@Controller
public class MedicoController {

	@Autowired
	IMedicoRepository repository;

	private static final String ENDPOINT = "api/medico";
	
	@ApiOperation("Serviço para Cadastrar")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> insert(@RequestBody MedicoPostRequest request) {
		try {
			Medico medico = new Medico();

			medico.setNome(request.getNome());
			medico.setCrm(request.getCrm());
			medico.setTelefone(request.getTelefone());
			medico.setTipo(request.getTipo());

			repository.save(medico);

			return ResponseEntity.status(HttpStatus.OK).body("Médico Cadastrado");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro" + e.getMessage());
		}

	}
	@ApiOperation("Serviço para Buscar")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<MedicoGetResponse>> search() {

		List<MedicoGetResponse> list = new ArrayList<MedicoGetResponse>();

		for (Medico medico : repository.findAll()) {
			MedicoGetResponse item = new MedicoGetResponse();

			item.setIdMedico(medico.getIdMedico());
			item.setNome(medico.getNome());
			item.setCrm(medico.getCrm());
			item.setTelefone(medico.getTelefone());
			item.setTipo(medico.getTipo());

			list.add(item);
		}

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

	@ApiOperation("Serviço para Deletar")
	@RequestMapping(value = ENDPOINT + "/{idMedico}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<String> delete(@PathVariable("idMedico") Integer idMedico) {
		try {
			Optional<Medico> item = repository.findById(idMedico);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medico não encontrado");
			} else {
				Medico medico = item.get();
				repository.delete(medico);
				return ResponseEntity.status(HttpStatus.OK).body("Médico excluido");

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}
	
	@ApiOperation("Serviço de busca de dados ordenados por id")
    @RequestMapping(value = ENDPOINT+"/{idMedico}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<MedicoGetResponse> getById(@PathVariable("idMedico") Integer idMedico) { 

        Optional<Medico> itemMedico = repository.findById(idMedico);

        if (itemMedico.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            MedicoGetResponse response = new MedicoGetResponse();
            Medico medico = itemMedico.get();

            response.setIdMedico(medico.getIdMedico());
            response.setNome(medico.getNome());
            response.setCrm(medico.getCrm());
            response.setTelefone(medico.getTelefone());
            response.setTipo(medico.getTipo());

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

	@ApiOperation("Serviço para Atualizar")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@CrossOrigin
	public ResponseEntity<String> update(@RequestBody MedicoPutRequest request) {
		try {
			Optional<Medico> item = repository.findById(request.getIdMedico());

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Médico não encontrado");
			} else {
				Medico medico = item.get();

				medico.setNome(request.getNome());
				medico.setCrm(request.getCrm());
				medico.setTelefone(request.getTelefone());
				medico.setTipo(request.getTipo());

				repository.save(medico);

				return ResponseEntity.status(HttpStatus.OK).body("Médico Editado");

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

}