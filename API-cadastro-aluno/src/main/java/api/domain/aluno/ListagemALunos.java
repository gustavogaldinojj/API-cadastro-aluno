package api.domain.aluno;

public record ListagemALunos(Long id, String nome, String email, String cpf, Modalidades modalidades) {
    public ListagemALunos(Alunos alunos) {
        this(alunos.getId(), alunos.getNome(), alunos.getEmail(), alunos.getCpf(), alunos.getModalidades());
    }
}
