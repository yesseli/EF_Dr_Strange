package com.progra3.ef.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.ef.entities.PacientesEntity;
import com.progra3.ef.repositories.pacientesRepository;

@RestController
@RequestMapping (value= "/pacientes")
public class PacientesController {

	@Autowired
	pacientesRepository repository;

	// Mostrar lista de pacientes
	@GetMapping
	public Iterable<PacientesEntity> findAll() {
		Iterable<PacientesEntity> listaPacientes = repository.findAll();
		return listaPacientes;

	}
//buscar paciente especifico 
	@GetMapping(value = "/{id}")
	public PacientesEntity getPacientesEntity(@PathVariable(name = "id") Long id) {

		Optional<PacientesEntity> paciente = repository.findById(id);
		PacientesEntity busqueda = null;
		if (paciente.isPresent()) {
			busqueda = paciente.get();
		}
		return busqueda;
	}
//editar los datos del paciente
	@PutMapping(value = "/{id}")
	public PacientesEntity modificarDatosPaciente(@PathVariable(name = "id") Long id,
			@RequestBody PacientesEntity paciente) {

		Optional<PacientesEntity> pacienteModificado = repository.findById(id);

		if (pacienteModificado.isPresent()) {

			PacientesEntity pacienteActual = pacienteModificado.get();
			pacienteActual.setId(paciente.getId());
			pacienteActual.setNombre1(paciente.getNombre1());
			pacienteActual.setNombre2(paciente.getNombre2());
			pacienteActual.setApellido1(paciente.getApellido1());
			pacienteActual.setApellido2(paciente.getApellido2());
			pacienteActual.setEdad(paciente.getEdad());
			PacientesEntity pmodif = repository.save(pacienteActual);

			return pmodif;
		}
		return null;
	}
//borrar paciente
	@DeleteMapping(value = "/{id}")
	public void borrarPaciente(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}

}
