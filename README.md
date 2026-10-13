# 🥋 API de Cadastro de Alunos

API REST desenvolvida em **Java + Spring Boot** para gerenciar o cadastro de alunos de uma academia de artes marciais, com as modalidades **Jiu-Jitsu**, **Jiu-Jitsu Kids** e **Boxe**.

---

## 🎯 Objetivo

Praticar a construção de uma API REST completa com Spring Boot, aplicando:

- separação em camadas (controller, domínio e repositório);
- uso de **DTOs com records** para entrada e saída de dados;
- persistência com **Spring Data JPA**;
- validação de dados de entrada;
- paginação e ordenação de resultados;
- exclusão lógica (o registro é inativado, não apagado do banco).

---

## ✅ Funcionalidades

- **Cadastrar** aluno com nome, e-mail, telefone, CPF, modalidade e endereço
- **Listar** alunos ativos com paginação (10 por página) e ordenação por nome
- **Detalhar** um aluno pelo ID
- **Atualizar** nome, telefone e endereço de um aluno
- **Inativar** um aluno (exclusão lógica)
- **Validar** os dados recebidos: campos obrigatórios, formato de e-mail, CPF e CEP

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 3 (Web e Data JPA)
- Bean Validation (Jakarta Validation)
- H2 Database (banco em memória para desenvolvimento)
- PostgreSQL (driver incluído no projeto)
- Lombok
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## 🗂️ Estrutura do projeto

```
src/main/java/api/
├── ApiCadastroAlunoApplication.java   # Classe principal
├── controller/
│   └── AlunoController.java           # Endpoints REST
└── domain/
    ├── aluno/
    │   ├── Alunos.java                # Entidade JPA
    │   ├── AlunoRepository.java       # Acesso ao banco
    │   ├── Modalidades.java           # Enum: JIUJITSU, JIUJITSUKIDS, BOX
    │   ├── DadosCadastroAluno.java    # DTO de entrada (cadastro)
    │   ├── DadosAtualizacaoAluno.java # DTO de entrada (atualização)
    │   ├── DadosDetalhamentoAluno.java# DTO de saída (detalhes)
    │   └── ListagemALunos.java        # DTO de saída (listagem)
    └── enderco/
        ├── Endereco.java              # Endereço embutido na entidade
        └── DadosEndereco.java         # DTO do endereço
```

---

## 📦 Endpoints

| Método | Rota | Descrição | Resposta |
| --- | --- | --- | --- |
| `POST` | `/alunos` | Cadastra um aluno | `201 Created` |
| `GET` | `/alunos` | Lista alunos ativos (paginado) | `200 OK` |
| `GET` | `/alunos/{id}` | Detalha um aluno | `200 OK` |
| `PUT` | `/alunos` | Atualiza nome, telefone e endereço | `200 OK` |
| `DELETE` | `/alunos/{id}` | Inativa um aluno | `204 No Content` |

### Exemplo: cadastrar aluno

`POST /alunos`

```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "telefone": "11999998888",
  "cpf": "123.456.789-09",
  "modalidades": "JIUJITSU",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": "100",
    "complemento": "Apto 12",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001000"
  }
}
```

**Regras de validação:**
- `nome`, `email`, `telefone`, `cpf`, `modalidades` e `endereco` são obrigatórios;
- `email` precisa ter formato válido;
- `cpf` aceita `12345678909` ou `123.456.789-09`;
- `cep` deve ter 8 dígitos, sem traço;
- `modalidades` aceita `JIUJITSU`, `JIUJITSUKIDS` ou `BOX`.

### Exemplo: atualizar aluno

`PUT /alunos`

```json
{
  "id": 1,
  "telefone": "11988887777"
}
```

Apenas os campos enviados são alterados.

### Paginação e ordenação

```
GET /alunos?page=0&size=5&sort=nome,desc
```

---

## ▶️ Como executar

**Pré-requisitos:** Java 17 ou superior. O Maven não precisa estar instalado, pois o projeto usa o Maven Wrapper.

```bash
# 1. Clonar o repositório
git clone https://github.com/gustavogaldinojj/api-cadastro-alunos-academia.git

# 2. Entrar na pasta do projeto
cd api-cadastro-alunos-academia/API-cadastro-aluno

# 3. Executar com o perfil de desenvolvimento (banco H2 em memória)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

No Windows, use `mvnw.cmd` no lugar de `./mvnw`.

A API ficará disponível em **http://localhost:8080**.

| Recurso | Endereço |
| --- | --- |
| Swagger UI (testar os endpoints) | http://localhost:8080/swagger-ui/index.html |
| Console do banco H2 | http://localhost:8080/h2-console |

Para acessar o console do H2, use a URL JDBC `jdbc:h2:mem:sdw2023` e o usuário `sdw2023`, sem senha. Como o banco é em memória, os dados são apagados sempre que a aplicação é encerrada.

---

## 📌 Melhorias futuras

- Configurar o PostgreSQL como banco de produção
- Tratamento de erros padronizado (ex.: retornar 404 para aluno inexistente)
- Testes unitários e de integração
- Autenticação com Spring Security e JWT
- Permitir alterar a modalidade do aluno na atualização

---

## 👨‍💻 Autor

**Gustavo Alves Oliveira**

- GitHub: [gustavogaldinojj](https://github.com/gustavogaldinojj)
- LinkedIn: [gustavo-alves-oliveira](https://www.linkedin.com/in/gustavo-alves-oliveira-58167a271/)
