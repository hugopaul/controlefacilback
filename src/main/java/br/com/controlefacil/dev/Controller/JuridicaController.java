package br.com.controlefacil.dev.Controller;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.model.Juridica;
import br.com.controlefacil.dev.repository.JuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/pessoa-juridica")
@CrossOrigin("*")
public class JuridicaController {

    private final JuridicaRepository repository;

    @Autowired
    public JuridicaController(JuridicaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Juridica salvar (@RequestBody @Valid Juridica juridica){
        try {
            return repository.save(juridica);
        } catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "Este CNPJ ou RAZÃO SOCIAL já foi cadastrado!");
        }
    }

    @GetMapping("{id}")
    public Juridica acharPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Juridica> getByNome(@RequestBody String getByNome){
        return this.repository.buscar(getByNome);
    }

    @GetMapping("/desativar/{id}")
    public void desativar(@PathVariable Long id){
        try {
            repository.findById(id).map(obj -> {
                obj.setDesativado(1);
                return repository.save(obj);
            });
        }catch (ResponseStatusException e){
            HttpStatus.BAD_REQUEST.getReasonPhrase();
        }
    }
    @GetMapping("/ativa/{id}")
    public void ativa(@PathVariable Long id){
        try {
            repository.findById(id).map(obj -> {
                obj.setDesativado(0);
                return repository.save(obj);
            });
        }catch (ResponseStatusException e){
            HttpStatus.BAD_REQUEST.getReasonPhrase();
        }
    }
    @GetMapping("/all")
    public List<Juridica> findall(){
        return repository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Juridica pessoaJuridicaAtualizado){
        repository.findById(id).map(pessoaJuridicaDesatualizado ->{
            pessoaJuridicaAtualizado.setId(pessoaJuridicaDesatualizado.getId());
            return repository.save(pessoaJuridicaAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Jurídica não Encontrada!"));
    }


}
