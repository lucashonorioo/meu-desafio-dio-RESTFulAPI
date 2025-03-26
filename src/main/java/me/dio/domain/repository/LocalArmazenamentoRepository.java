package me.dio.domain.repository;

import me.dio.domain.model.LocalArmazenamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalArmazenamentoRepository extends JpaRepository<LocalArmazenamento, Long> {
}
