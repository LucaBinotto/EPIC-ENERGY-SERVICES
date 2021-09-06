package it.epicode.be.model;

import lombok.Data;

@Data
public class Comune {

		private Long id;
		private String  nome;
		private Provincia provincia;
}
