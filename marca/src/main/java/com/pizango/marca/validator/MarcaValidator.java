package com.pizango.marca.validator;

import com.pizango.marca.entity.Marca;
import com.pizango.marca.exception.ValidateServiceException;

public class MarcaValidator {
	public static void save(Marca marca) {
		if(marca.getNombre()==null || marca.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(marca.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(marca.getRuc().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(marca.getCorreo().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}		
	}

}
