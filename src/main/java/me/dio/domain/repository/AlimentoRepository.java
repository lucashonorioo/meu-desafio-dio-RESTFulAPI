package me.dio.domain.repository;

import me.dio.domain.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM tb_alimento a WHERE a.localArmazenamento.id = :localArmazenamentoId")
    List<Alimento> findByLocalArmazenamentoId(@Param("localArmazenamentoId") Long localArmazenamentoId);

    @Modifying
    @Query("DELETE FROM tb_alimento a WHERE a.id = :alimentoId")
    void deleteAlimentoById(@Param("alimentoId") Long alimentoId);
}
