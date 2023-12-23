package com.aranda.producto.converter;

import org.springframework.stereotype.Component;

import com.aranda.producto.dto.ProductoDTO;
import com.aranda.producto.entity.Producto;





@Component
public class ProductoConverter extends AbstractConverter<Producto, ProductoDTO> {

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if(entity==null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.precio(entity.getPrecio())
				.cantidad(entity.getCantidad())
				.created_at(entity.getCreatedAt())
				.activo(entity.getActivo())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if(dto==null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.precio(dto.getPrecio())
				.cantidad(dto.getCantidad())
				.activo(dto.getActivo())
				.build();
	}



}
