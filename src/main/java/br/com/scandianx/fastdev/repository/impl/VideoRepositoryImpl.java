package br.com.scandianx.fastdev.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.repository.interfaces.VideoRepository;

@Repository
public interface VideoRepositoryImpl 
        extends JpaRepository<VideoAbstrato, Long>, VideoRepository {

    @Override
    @Query(value = "select * from tvideo_base v \n" +
            "where lower(v.titulo) like lower(concat('%', :termo, '%')) \n" +
            "order by v.dt_criacao desc", nativeQuery = true)
    List<VideoAbstrato> buscarPorTituloSimilar(@Param("termo") String termo);

    @Override
    @Query(value = "select v.* from tvideo_base v \n" +
            "join (select f.video_id as vid, count(f.id) as favs from tvideos_favoritos f group by f.video_id order by favs desc limit :lim) t on t.vid = v.id \n" +
            "order by t.favs desc", nativeQuery = true)
    List<VideoAbstrato> buscarMaisFavoritados(@Param("lim") int limite);
}
