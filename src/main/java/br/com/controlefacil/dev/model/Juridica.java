package br.com.controlefacil.dev.model;

import com.danielfariati.annotation.CNPJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Juridica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "razao_social", nullable = false)
    @NotNull(message = "O campo RAZÃO SOCIAL é obrigatório" )
    private String razaoSocial;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "apelido", nullable = false)
    private String apelido;

    @CNPJ
    @NotNull(message = "O campo CNPJ é obrigatório" )
    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "telefone", nullable = true)
    private String telefone;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "endereco", nullable = true)
    private String endereco;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @Column(name = "desativado")
    private Integer desativado;


    public LocalDate getCreated() {
        return created;
    }
    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getDesativado() {
        return desativado;
    }

    public void setDesativado(Integer desativado) {
        this.desativado = desativado;
    }


    @Override
    public String toString() {
        return "Juridica{" +
                "id=" + id +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", apelido='" + apelido + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", created=" + created +
                ", desativado=" + desativado +
                '}';
    }

    @PrePersist
    void prePersist(){
        this.created = LocalDate.now();
        this.desativado = 0;
    }
}
