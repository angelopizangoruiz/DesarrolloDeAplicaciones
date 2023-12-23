package com.aranda.producto.dto;

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
public class ProductoDTO {
	private int id;
	private String nombre;
	private Double precio;
	private int cantidad;
	private Date created_at	;
	private Boolean activo;
}