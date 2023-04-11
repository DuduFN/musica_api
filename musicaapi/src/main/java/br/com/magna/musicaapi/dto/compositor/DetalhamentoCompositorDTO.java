package br.com.magna.musicaapi.dto.compositor;

import br.com.magna.musicaapi.entity.compositor.Compositor;

public record DetalhamentoCompositorDTO(
		
		Long Id,
		String nomeIntegrante,
		String funcaoIntegrante

		) {

	public DetalhamentoCompositorDTO(Compositor compositor) {
		this(
			compositor.getCompositorId(),
			compositor.getNomeIntegrante(),
			compositor.getFuncaoIntegrante()	
			);
	}

}
