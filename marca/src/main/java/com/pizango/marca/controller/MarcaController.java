package com.pizango.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizango.marca.converter.MarcaConverter;
import com.pizango.marca.dto.MarcaDTO;
import com.pizango.marca.entity.Marca;
import com.pizango.marca.service.MarcaService;
import com.pizango.marca.util.WrapperResponse;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
	@Autowired
	private MarcaService service;
	@Autowired
	private MarcaConverter converter; 
	
	@GetMapping()
	public ResponseEntity<List<MarcaDTO>> findAll(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "offset", required = false, defaultValue = " 0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = " 5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Marca> marcas;
		if (nombre == null) {
			marcas = service.findAll(page);

		} else {
			marcas = service.findByNombre(nombre, page);
		}
		if (marcas.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		List<MarcaDTO> marcasDTO=converter.fromEntity(marcas);
		return new WrapperResponse(true,"succes",marcasDTO).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse< MarcaDTO>> findById(@PathVariable("id") int id) {
		Marca marca = service.findById(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		}
		MarcaDTO marcaDTO=converter.fromEntity(marca);
		return new WrapperResponse<MarcaDTO>(true, "succes",marcaDTO).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MarcaDTO> create(@RequestBody MarcaDTO articuloDTO) {
		Marca registro = service.save(converter.fromDTO(articuloDTO));
		MarcaDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.CREATED);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> update(@PathVariable("id") int id, @RequestBody MarcaDTO marcaDTO) {
		Marca registro = service.update(converter.fromDTO(marcaDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		MarcaDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> delete(@PathVariable("id") int id) {
		service.delete(id);
		return new WrapperResponse(true, "succes",null ).createResponse(HttpStatus.OK);

	}
}
