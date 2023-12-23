package com.pizango.marca.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pizango.marca.entity.Marca;

public interface MarcaRepository  extends JpaRepository<Marca,Integer > {
	public Marca findByNombre(String nombre);
	List<Marca> findByNombreContaining(String nombre, Pageable page);
	
}
