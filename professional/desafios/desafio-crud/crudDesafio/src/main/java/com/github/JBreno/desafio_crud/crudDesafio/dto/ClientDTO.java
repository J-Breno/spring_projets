package com.github.JBreno.desafio_crud.crudDesafio.dto;

import com.github.JBreno.desafio_crud.crudDesafio.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;
    @Size(min = 3, max = 40, message = "Nome precisa ter no mínimo  de 3 a 40 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @NotBlank(message = "Campo requerido")
    private String cpf;
    @Positive(message = "O preço deve ser positivo")
    private Double income;
    @PastOrPresent(message = "A data do evento não pode ser no futuro.")
    private LocalDate birthDate;
    @Positive(message = "O preço deve ser positivo")
    private Integer children;

    public ClientDTO() {}

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
