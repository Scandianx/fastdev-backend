package br.com.scandianx.fastdev.repository.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.repository.interfaces.VideoRepository;

@Repository
public interface VideoRepositoryImpl 
        extends JpaRepository<VideoAbstrato, Long>, VideoRepository {

    boolean existsByTituloIgnoreCase(String titulo);

    @Override
    @Query("select v from VideoAbstrato v where lower(v.titulo) like lower(concat('%', :termo, '%')) order by v.dtCriacao desc")
    List<VideoAbstrato> buscarPorTituloSimilar(@Param("termo") String termo);

    @Override
    @Query("select v from VideoAbstrato v left join Favorito f on f.video = v group by v order by count(f) desc")
    List<VideoAbstrato> buscarMaisFavoritados(Pageable pageable);
}
