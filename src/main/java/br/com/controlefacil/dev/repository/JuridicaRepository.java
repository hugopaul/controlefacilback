package br.com.controlefacil.dev.repository;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.model.Juridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuridicaRepository extends JpaRepository<Juridica, Long> {

    @Query( value = "select * from juridica where razao_social ilike %:x% and desativado = 0" ,nativeQuery = true)
    public List<Juridica> buscar(String x);

    @Query(value = "select * from juridica where id = :id and  desativado = 0",nativeQuery = true)
    public Juridica findOneById(Long id);
}