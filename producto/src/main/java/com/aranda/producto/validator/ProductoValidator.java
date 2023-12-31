package com.aranda.producto.validator;

import com.aranda.producto.entity.Producto;
import com.aranda.producto.exception.ValidateServiceException;

public class ProductoValidator {
	public static void save(Producto producto) {
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(producto.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		
		
		if(producto.getPrecio()<0) {
			throw new ValidateServiceException("El precio no puede ser negativo");
		}
			
		if(producto.getPrecio()==null) {
			throw new ValidateServiceException("El precio es requerido");
		}
		
		if(producto.getCantidad()<0) {
			throw new ValidateServiceException("El stock no puede ser negativo");
		}
		
		
	}
}
