package br.com.magna.musicaapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magna.musicaapi.entity.compositor.Compositor;

public interface CompositorRepository extends JpaRepository<Compositor, Long> {

}
