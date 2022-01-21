package br.com.controlefacil.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "cod_bacen")
    @NotNull(message = "o campo CÓDIGO BACEN é obrigatório")
    private Long codBacen;

    @Column(name = "nm_banco")
    @NotNull(message = "o campo NOME DO BANCO é obrigatório")
    private String nmBanco;

    @Column(name = "desativado")
    private Integer desativado;



    @PrePersist
    void prePersist(){
        this.created = LocalDate.now();
        this.desativado = 0;
    }

    public Integer getDesativado() {
        return desativado;
    }

    public void setDesativado(Integer desativado) {
        this.desativado = desativado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodBacen() {
        return codBacen;
    }

    public void setCodBacen(Long codBacen) {
        this.codBacen = codBacen;
    }

    public String getNmBanco() {
        return nmBanco;
    }

    public void setNmBanco(String nmBanco) {
        this.nmBanco = nmBanco;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "id=" + id +
                ", created=" + created +
                ", codBacen=" + codBacen +
                ", nmBanco='" + nmBanco + '\'' +
                '}';
    }
}
