package br.com.controlefacil.dev.model;

import com.danielfariati.annotation.CPF;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "O campo NOME é obrigatório")
    private String nome;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    @NotNull(message = "O campo CPF é obrigatório" )
    private String cpf;

    @Column(name = "Telefone", nullable = true)
    private String Telefone;

    @Email
    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "endereco", nullable = true)
    private String endereco;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "desativado")
    private Integer desativado;

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
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

    public LocalDate getCreated() {
        return created;
    }

    public Integer getDesativado() {
        return desativado;
    }

    public void setDesativado(Integer desativado) {
        this.desativado = desativado;
    }

    @Override
    public String toString() {
        return "Fisica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", Telefone='" + Telefone + '\'' +
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
