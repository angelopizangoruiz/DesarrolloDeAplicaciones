package com.pizango.marca.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.pizango.marca.entity.Marca;

public interface MarcaService {
	public List<Marca> findAll(Pageable page);
	public List<Marca> findByNombre(String nombre, Pageable page);
	public Marca findById(int id);
	public Marca save(Marca marca);
	public Marca update(Marca marca);
	public void delete(int id);
}

