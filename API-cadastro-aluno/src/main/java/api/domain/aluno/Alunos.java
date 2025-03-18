package api.domain.aluno;

import api.domain.enderco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "tb_alunos")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Modalidades modalidades;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Alunos(DadosCadastroAluno dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.modalidades = dados.modalidades();
        this.endereco = new Endereco(dados.endereco());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Modalidades getModalidades() {
        return modalidades;
    }

    public Long getId() {
        return id;
    }

    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
    public void inativar() {
        this.ativo = false;
    }
}
