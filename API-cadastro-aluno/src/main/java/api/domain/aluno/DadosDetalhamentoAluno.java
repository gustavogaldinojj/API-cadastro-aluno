package api.domain.aluno;

import api.domain.enderco.Endereco;

public record DadosDetalhamentoAluno(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
    public DadosDetalhamentoAluno(Alunos aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getTelefone(), aluno.getEndereco());
    }
}
