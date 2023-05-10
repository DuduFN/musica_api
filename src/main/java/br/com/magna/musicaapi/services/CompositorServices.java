package br.com.magna.musicaapi.services;

import org.springframework.stereotype.Service;

import br.com.magna.musicaapi.dto.compositor.CompositorDTO;
import br.com.magna.musicaapi.entity.compositor.Compositor;

@Service
public class CompositorServices {

	public Compositor criarCompositor(CompositorDTO dados) {
		Compositor compositor = new Compositor();

		compositor.setNomeIntegrante(dados.nomeIntegrante());
		compositor.setFuncaoIntegrante(dados.funcaoIntegrante());
		
		return compositor;
	}

}
