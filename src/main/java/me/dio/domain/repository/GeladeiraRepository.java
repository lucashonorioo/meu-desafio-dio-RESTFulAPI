package me.dio.domain.repository;

import me.dio.domain.model.Geladeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeladeiraRepository extends JpaRepository<Geladeira, Long> {
}
