package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.model.Visualizador;
import br.com.scandianx.fastdev.repository.interfaces.VisualizadorRepository;
@Repository
public interface VisualizadorRepositoryImpl extends JpaRepository<Visualizador, Long>, VisualizadorRepository{
    
}
