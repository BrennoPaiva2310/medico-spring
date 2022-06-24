package com.hospital.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.medico.entities.Medico;

@Repository
public interface IMedicoJasper extends JpaRepository<Medico, Integer> {

}
