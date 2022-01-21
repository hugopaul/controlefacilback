package br.com.controlefacil.dev.service;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.model.Recurso;
import br.com.controlefacil.dev.repository.FisicaRepository;
import br.com.controlefacil.dev.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FisicaService {

    @Autowired
    private FisicaRepository repository;

    public Fisica findOneById(Long id){
        return this.repository.findOneById(id);
    }
}
