package br.com.controlefacil.dev.Controller;

import br.com.controlefacil.dev.model.Fisica;
import br.com.controlefacil.dev.repository.FisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa-fisica")
@CrossOrigin("*")
public class FisicaController {

    private final FisicaRepository repository;


    @Autowired
    public FisicaController(FisicaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fisica salvar (@RequestBody @Valid Fisica fisica){
        try {
            return repository.save(fisica);
        } catch (Exception ex){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , "Esta CPF ou NOME já foi cadastrado!");
        }
    }

    @GetMapping("{id}")
    public Fisica acharPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
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

    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Fisica> getByNome(@RequestBody String getByNome){
       return this.repository.buscar(getByNome);
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
    public List<Fisica> findall(){
        return repository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Fisica pessoaFisicaAtualizado){
        repository.findById(id).map(pessoaFisicaDesatualizado ->{
            pessoaFisicaAtualizado.setId(pessoaFisicaDesatualizado.getId());
            return repository.save(pessoaFisicaAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa Física não Encontrada!"));
    }

}
