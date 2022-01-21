package br.com.controlefacil.dev.service;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.model.Juridica;
import br.com.controlefacil.dev.repository.FisicaRepository;
import br.com.controlefacil.dev.repository.JuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuridicaService {


    @Autowired
    private JuridicaRepository repository;

    public Juridica findOneById(Long id){
        return this.repository.findOneById(id);
    }


}
