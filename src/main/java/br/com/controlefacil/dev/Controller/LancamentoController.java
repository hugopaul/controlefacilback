package br.com.controlefacil.dev.Controller;

import br.com.controlefacil.dev.model.Juridica;
import br.com.controlefacil.dev.model.Lancamento;
import br.com.controlefacil.dev.model.dashboards.BarDash;
import br.com.controlefacil.dev.repository.JuridicaRepository;
import br.com.controlefacil.dev.repository.LancamentoRepository;
import br.com.controlefacil.dev.service.FisicaService;
import br.com.controlefacil.dev.service.JuridicaService;
import br.com.controlefacil.dev.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamento")
@CrossOrigin("*")
public class LancamentoController {

    private final LancamentoRepository repository;
    @Autowired
    private RecursoService recursoService;
    @Autowired
    private JuridicaService juridicaService;
    @Autowired
    private FisicaService fisicaService;
    @Autowired
    public LancamentoController(LancamentoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lancamento salvar (@RequestBody @Valid Lancamento lancamento){

            return repository.save(lancamento);
        }
    @GetMapping("/dashboards/line/entrada/{tipoLancamento}")
    @ResponseStatus(HttpStatus.OK)
    public List<BarDash> entrada(@PathVariable Long tipoLancamento){
        return repository.meseqtd(tipoLancamento);

    }

    @GetMapping("/dashboards/somavalorvntrada")
    @ResponseStatus(HttpStatus.OK)
    public Long somaValorEntrada(){
        return repository.somaValorEntrada();

    }


    @GetMapping("{id}")
    public Lancamento acharPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void desativar( @RequestBody @Valid Lancamento lancamentoDesativar, @PathVariable Long id){
        try {
            repository.findById(id).map(obj -> {
                obj.setDesativado(1);
                obj.setMotivoDesativar(lancamentoDesativar.getMotivoDesativar());
                return repository.save(obj);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lancamento não Encontrado!"));
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
    public List<Lancamento> findall(){
        return repository.findAll();
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Lancamento lancamentoAtualizado){
        repository.findById(id).map(lancamentoDesatualizado ->{
            lancamentoAtualizado.setId(lancamentoDesatualizado.getId());
            return repository.save(lancamentoAtualizado);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lancamento não Encontrado!"));
    }
}
