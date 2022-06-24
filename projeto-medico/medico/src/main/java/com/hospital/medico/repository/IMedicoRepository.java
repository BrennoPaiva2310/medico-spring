package com.hospital.medico.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hospital.medico.entities.Medico;



@Repository
public interface IMedicoRepository extends CrudRepository<Medico, Integer>{

}
