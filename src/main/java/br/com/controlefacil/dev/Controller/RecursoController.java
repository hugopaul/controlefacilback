package br.com.controlefacil.dev.Controller;

import br.com.controlefacil.dev.model.Juridica;
import br.com.controlefacil.dev.model.Lancamento;
import br.com.controlefacil.dev.model.Recurso;
import br.com.controlefacil.dev.repository.LancamentoRepository;
import br.com.controlefacil.dev.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/recurso")
@CrossOrigin("*")
public class RecursoController {

    private final RecursoRepository repository;

    @Autowired
    public RecursoController(RecursoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recurso salvar (@RequestBody @Valid Recurso recurso){
        return repository.save(recurso);
    }

    @GetMapping("{id}")
    public Recurso acharPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Recurso> getByNome(@RequestBody String getByNome){
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
    public List<Recurso> findall(){
        return repository.findAll();
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Recurso recursoAtualizado){
        repository.findById(id).map(recursoDesatualizado ->{
            recursoAtualizado.setId(recursoDesatualizado.getId());
            return repository.save(recursoAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Banco não Encontrado!"));
    }
}
