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

import com.progra3.ef.entities.CitasEntity;
import com.progra3.ef.repositories.citasRepository;

@RestController
@RequestMapping ("/citas")
public class CitasController {
	
	@Autowired
	citasRepository repository;

	// Mostrar Lista de citas
	@GetMapping
	public Iterable<CitasEntity> findAll() {
		Iterable<CitasEntity> listaCitas = repository.findAll();
		return listaCitas;
	}

	// buscar una cita 
	@GetMapping(value = "/{id}")
	public CitasEntity getCitasEntity(@PathVariable(name = "id") Long id) {

		Optional<CitasEntity> cita = repository.findById(id);
		CitasEntity buscar = null;
		if (cita.isPresent()) {
			buscar = cita.get();

		}
		return buscar;
	}

	// modificar informacion de la cita 

	@PutMapping(value = "/{id}")
	public CitasEntity modificarCitas(@PathVariable(name = "id") Long id, @RequestBody CitasEntity cita) {

		Optional<CitasEntity> citaModificada = repository.findById(id);

		if (citaModificada.isPresent()) {

			CitasEntity infoCitaActual = citaModificada.get();
			infoCitaActual.setId(cita.getId());
			infoCitaActual.setFecha(cita.getFecha());
			infoCitaActual.setHora(cita.getHora());
			infoCitaActual.setPaciente(cita.getPaciente());
			infoCitaActual.setEstado(cita.getEstado());
			infoCitaActual.setObservaciones(cita.getObservaciones());
			CitasEntity cModif = repository.save(infoCitaActual);
			return cModif;

		}
		return null;
	}

	// borrar
	@DeleteMapping(value = "/{id}")
	public void borrarCita(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}

}
