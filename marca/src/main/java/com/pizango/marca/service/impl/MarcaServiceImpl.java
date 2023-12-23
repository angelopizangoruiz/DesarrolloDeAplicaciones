package com.pizango.marca.service.impl;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizango.marca.entity.Marca;
import com.pizango.marca.exception.GeneralServiceException;
import com.pizango.marca.exception.NoDataServiceException;
import com.pizango.marca.exception.ValidateServiceException;
import com.pizango.marca.repository.MarcaRepository;
import com.pizango.marca.service.MarcaService;
import com.pizango.marca.validator.MarcaValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class MarcaServiceImpl implements MarcaService{
	
	@Autowired
	private MarcaRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Marca> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly  =true)
	public List<Marca> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
	}
	}

	@Override
	@Transactional(readOnly  =true)
	public Marca findById(int id) {
		try {
			return repository.findById(id).
					orElseThrow(()->new NoDataServiceException("No Existe un registro con el ID: "+id));
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public Marca save(Marca marca) {
		try {
			MarcaValidator.save(marca);
			if(repository.findByNombre(marca.getNombre())!=null){
				
				throw new ValidateServiceException("ya existe un registro con el nombre"+marca.getNombre());
				
			}
			marca.setActivo(true);
			Marca registro=repository.save(marca);
			return registro;
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public Marca update(Marca marca) {
		try {
			MarcaValidator.save(marca);
			Marca registroD=repository.findByNombre(marca.getNombre());
			if (registroD !=null && registroD.getId()!=marca.getId()) {
				throw new ValidateServiceException("ya existe un registro con ese nombre"+ marca.getNombre());
			}
			
			Marca registro=repository.findById(marca.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ marca.getId()));
			registro.setNombre(marca.getNombre());
			registro.setRuc(marca.getRuc());
			registro.setCorreo(marca.getCorreo());
			repository.save(registro);
			return registro;
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		try {
			Marca registro =repository.findById(id).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ id));
			repository.delete(registro);
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
		
	}
	


}
