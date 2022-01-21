package br.com.controlefacil.dev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "descricao", nullable = false)
    @NotNull(message = "O campo DESCRIÇÃO é obrigatório" )
    private String descricao;

    @Column(name = "nfOuCodigo", nullable = false)
    @NotNull(message = "O campo NOTA FISCAL/CÓDIGO é obrigatório" )
    private String nfOuCodigo;

    @Column(name = "valor", nullable = false)
    @NotNull(message = "O campo VALOR é obrigatório" )
    private Double valor;

    @Column(name = "tipo_lancamento", nullable = false)
    @NotNull(message = "O campo TIPO LANÇAMENTO é obrigatório" )
    private Integer tipoLancamento;

    @Column(name = "recurso_id")
    private Long recurso;

    @Column(name = "data", nullable = false)
    @NotNull(message = "O campo DATA DO LANÇAMENTO é obrigatório" )
    private LocalDate data;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @Column(name = "desativado")
    private Integer desativado;

    @Column(name = "motivo_desativ")
    private String motivoDesativar;

    @Column
    private Boolean pf;

    @Column
    private boolean pj;

    @Column(name = "pessoa_fisica_id")
    private Long pessoaFisica;

    @Column(name = "pessoa_juridica_id")
    private Long pessoaJuridica ;

    public String getMotivoDesativar() {
        return motivoDesativar;
    }

    public void setMotivoDesativar(String motivoDesativar) {
        this.motivoDesativar = motivoDesativar;
    }

    public Boolean getPf() {
        return pf;
    }

    public void setPf(Boolean pf) {
        this.pf = pf;
    }

    public boolean isPj() {
        return pj;
    }

    public void setPj(boolean pj) {
        this.pj = pj;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNfOuCodigo() {
        return nfOuCodigo;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getTipoLancamento() {
        return tipoLancamento;
    }

    public Long getRecurso() {
        return recurso;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNfOuCodigo(String nfOuCodigo) {
        this.nfOuCodigo = nfOuCodigo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setTipoLancamento(Integer tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public void setRecurso(Long recurso) {
        this.recurso = recurso;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getDesativado() {
        return desativado;
    }

    public void setDesativado(Integer desativado) {
        this.desativado = desativado;
    }

    public Long getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(Long pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Long getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(Long pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", nfOuCodigo='" + nfOuCodigo + '\'' +
                ", valor=" + valor +
                ", tipoLancamento=" + tipoLancamento +
                ", recurso='" + recurso + '\'' +
                ", data=" + data +
                ", created=" + created +
                ", desativado=" + desativado +
                ", motivoDesativar='" + motivoDesativar + '\'' +
                ", pessoaFisica=" + pessoaFisica +
                ", pessoaJuridica=" + pessoaJuridica +
                '}';
    }

    @PrePersist
    void prePersist(){
        this.created = LocalDate.now();
        this.desativado = 0;
    }
}
