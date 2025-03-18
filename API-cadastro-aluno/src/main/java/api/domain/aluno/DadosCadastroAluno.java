package api.domain.aluno;

import api.domain.enderco.DadosEndereco;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        String cpf,
        @NotNull
        Modalidades modalidades,
        @NotNull
        @Valid
        DadosEndereco endereco){
}
