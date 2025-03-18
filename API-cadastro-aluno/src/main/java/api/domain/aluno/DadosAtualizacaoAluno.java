package api.domain.aluno;

import api.domain.enderco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoAluno(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco,
        @Valid Modalidades modalidades) {
}
