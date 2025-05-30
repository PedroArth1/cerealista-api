package com.pedro.cerealista.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TB_FORNECEDOR")
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fornecedor")
    private Long idFornecedor;
    private String nome;
    private String cnpj;
    private String contato;
}
