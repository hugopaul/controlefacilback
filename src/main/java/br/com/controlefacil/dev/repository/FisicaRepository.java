package br.com.controlefacil.dev.repository;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Observable;

public interface FisicaRepository extends JpaRepository<Fisica, Long> {


    @Query( value = "select * from fisica where nome ilike %:x% and desativado = 0 " ,nativeQuery = true)
    public List<Fisica> buscar(String x);

    @Query(value = "select * from fisica where id = :id and  desativado = 0",nativeQuery = true)
    public Fisica findOneById(Long id);
}