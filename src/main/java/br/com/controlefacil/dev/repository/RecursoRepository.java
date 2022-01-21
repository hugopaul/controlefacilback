package br.com.controlefacil.dev.repository;

import br.com.controlefacil.dev.model.Juridica;
import br.com.controlefacil.dev.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    @Query( value = "select * from recurso where nm_banco ilike %:x% and desativado = 0" ,nativeQuery = true)
    public List<Recurso> buscar(String x);

    @Query(value = "select * from recurso where id = :id and  desativado = 0",nativeQuery = true)
    public Recurso findOneById(Long id);
}