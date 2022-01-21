package br.com.controlefacil.dev.service;

import br.com.controlefacil.dev.model.Recurso;
import br.com.controlefacil.dev.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository repository;

    public Recurso findOneById(Long id){
        return this.repository.findOneById(id);
    }
}
