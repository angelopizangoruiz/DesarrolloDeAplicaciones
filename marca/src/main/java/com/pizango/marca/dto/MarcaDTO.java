package com.pizango.marca.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarcaDTO {
	private int id;
	private String nombre;
	private String ruc;
	private String correo;
	private Date created_at	;
	private Boolean activo;
}
