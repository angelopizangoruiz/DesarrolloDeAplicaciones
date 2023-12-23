package com.pizango.marca.converter;

import org.springframework.stereotype.Component;
import com.pizango.marca.dto.MarcaDTO;
import com.pizango.marca.entity.Marca;

@Component
public class MarcaConverter extends AbstractConverter<Marca, MarcaDTO>  {

	@Override
	public MarcaDTO fromEntity(Marca entity) {
		if(entity==null) return null;
		return MarcaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.ruc(entity.getRuc())
				.correo(null)
				.created_at(entity.getCreatedAt())
				.activo(entity.getActivo())
				.build();
	}

	@Override
	public Marca fromDTO(MarcaDTO dto) {
		if(dto==null) return null;
		return Marca.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.ruc(dto.getRuc())
				.correo(dto.getCorreo())
				.activo(dto.getActivo())
				.build();
	}




}
