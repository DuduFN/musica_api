package br.com.magna.musicaapi.dto.produtor;

import br.com.magna.musicaapi.entity.produtor.Produtor;

public record DetalhamentoProdutorDTO(	
		
		Long Id,
		String gravadora,
		String estudio,
		String distribuidora,
		String producao
		
		) {

	public DetalhamentoProdutorDTO(Produtor produtor) {
		
		this(
			produtor.getProdutorId(),
			produtor.getGravadora(),
			produtor.getEstudio(),
			produtor.getDistribuidora(),
			produtor.getProducao()	
			
			);
	}
}
