package com.progra3.ef.repositories;

import org.springframework.data.repository.CrudRepository;

import com.progra3.ef.entities.PacientesEntity;

public interface pacientesRepository extends CrudRepository<PacientesEntity, Long> {

}
