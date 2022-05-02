package br.com.rogerio.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @NotNull
    @NotEmpty
    @Length(message = "O nome deve conter no mínimo 3 caracteres")
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @NotEmpty
    @Min(value = 0, message = "O valor informado não pode ser negativo")
    @Column(name = "PRECO_PRODUTO")
    private Double preco;

    @NotNull
    @NotEmpty
    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void onSave() {
        this.dataCadastro = LocalDateTime.now();
    }

//    implementado uma linha no final do arquivo

}
