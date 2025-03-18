package api.controller;

import api.domain.aluno.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder){
        var alunos = new Alunos(dados);
        repository.save(alunos);

        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(alunos.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(alunos));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemALunos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemALunos:: new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados){
        var alunos = repository.getReferenceById(dados.id());
        alunos.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(alunos));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        var alunos = repository.getReferenceById(id);
        alunos.inativar();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var alunos = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(alunos));
    }

}
