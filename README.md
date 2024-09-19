# Sistema de Gerenciamento Escolar

## Visão Geral

O **Sistema de Gerenciamento Escolar** é uma aplicação desenvolvida em **Java** com uma interface de texto intuitiva. Ele facilita o gerenciamento de alunos, disciplinas, professores, turmas e notas, oferecendo uma solução simples e eficiente para a organização acadêmica. O sistema foi projetado para centralizar o cadastro, a gestão de notas e a emissão de boletins, garantindo que todas as informações acadêmicas sejam acessadas de maneira fácil e rápida.

---

## Funcionalidades

### 1. Cadastro de Alunos, Disciplinas, Professores e Turmas

- **Alunos:** Cada aluno é cadastrado com um nome e matrícula únicos, assegurando a integridade dos dados.
- **Disciplinas:** Disciplinas semestrais são cadastradas com um nome e código, evitando duplicações.
- **Professores:** Professores são identificados por um ID e um nome, garantindo controle sobre a gestão de turmas.
- **Turmas:** As turmas associam um professor a uma disciplina e permitem o gerenciamento de alunos matriculados.

### 2. Gerenciamento de Turmas e Professores

- **Atribuição de Disciplinas:** Cada turma é vinculada a um professor e uma disciplina, garantindo a organização acadêmica.
- **Remoção de Alunos:** Alunos podem ser adicionados ou removidos de turmas conforme a necessidade.

### 3. Registro e Gestão de Notas

- **Registro de Notas:** Professores podem registrar até duas notas parciais para cada aluno, além de uma nota de prova final, caso o aluno não tenha atingido a média necessária.
- **Alteração de Notas:** As notas podem ser alteradas caso necessário, permitindo correções ou ajustes.

### 4. Regras de Avaliação

- **Critério de Aprovação:** A média das duas notas parciais deve ser maior ou igual a 60. Se a média for menor, a prova final pode ser aplicada para que o aluno tenha outra chance de aprovação.
- **Prova Final:** Se a nota da prova final for suficiente, ela será usada para determinar a aprovação.

### 5. Emissão de Boletins

- O sistema gera boletins para cada aluno contendo:
  - Nome completo e matrícula do aluno.
  - Notas parciais e a nota da prova final, se houver.
  - Informações sobre a disciplina e o professor responsável pela turma.
  - Status de aprovação ou reprovação baseado na média das notas.

---

## Uso do Sistema

A interação com o sistema é feita via linha de comando, utilizando um menu interativo que guia o usuário pelas principais funcionalidades. Tudo é automatizado, então basta apenas selecionar a opção desejada e inserir os dados conforme solicitados.

### 1. Cadastrar Alunos, Professores e Disciplinas

O sistema solicita informações como nome, matrícula e código. Exemplo:

```bash
1. Adicionar Aluno
2. Adicionar Disciplina
3. Adicionar Professor
```

O usuário insere as informações solicitadas, e o sistema valida e armazena os dados.

### 2. Criar Turmas

O usuário escolhe uma disciplina e um professor para formar a turma, com a possibilidade de adicionar alunos à turma. Exemplo:

```bash
4. Criar Turma
```

Depois é so inserir os dados solicitados via terminal.

### 3. Atribuir Notas

Professores podem registrar notas dos alunos matriculados nas turmas. Exemplo:

```bash
5. Atribuir Notas
```

Depois é so inserir os dados solicitados via terminal.

### 4. Alterar Notas

Caso seja necessário alterar notas já atribuídas, o sistema oferece essa funcionalidade. Exemplo:

```bash
Alterar Nota JoãoSilva 70 80
```

Depois é so inserir os dados solicitados via terminal.

### 5. Emitir Boletins

O sistema gera boletins com todas as informações acadêmicas do aluno, incluindo notas e status. Exemplo:

```bash
7. Emitir Boletim
```

Depois é so inserir os dados solicitados via terminal.

### 6. Remover Alunos da Turma

Se for necessário remover um aluno de uma turma, o sistema permite isso de forma simples. Exemplo:

```bash
8. Remover Aluno da Turma
```

Depois é so inserir os dados solicitados via terminal.

## Estrutura do Código

### O código está organizado de forma modular, com cada componente desempenhando um papel específico no sistema:

**entidades/Aluno.java**: Classe que representa os alunos, contendo atributos como nome e matrícula.
**entidades/Disciplina.java**: Representa as disciplinas com nome e código.
**entidades/Professor.java**: Classe para representar os professores, com nome e ID.
**entidades/Turma.java**: Gerencia as turmas, alunos, professores e notas, permitindo também a emissão de boletins.
**repositorios/RepositorioAlunos.java**: Armazena e gerencia os dados dos alunos cadastrados.
**repositorios/RepositorioDisciplinas.java**: Responsável pelo gerenciamento das disciplinas.
**repositorios/RepositorioProfessores.java**: Armazena e gerencia os dados dos professores.
**repositorios/RepositorioTurmas.java**: Gerencia as turmas, incluindo a relação entre alunos, professores e disciplinas.
**src/SistemaEscolar.java**: A classe principal que coordena todas as interações com o usuário via linha de comando, centralizando o cadastro, gerenciamento de turmas e notas.

## Melhorias adicionais

**Mensagens de Erro Mais Informativas**: O sistema foi projetado para fornecer mensagens claras em caso de entradas inválidas, guiando o usuário sobre o que deve ser corrigido.
**Tratamento de Exceções**: Entradas incorretas, como valores não numéricos nas notas, são tratadas adequadamente, evitando que o sistema quebre durante a execução.

## Tratamento de Exceções

**Entradas Inválidas**: Caso o usuário insira uma entrada incorreta, como valores não numéricos para notas, o sistema informa claramente o erro e solicita uma nova entrada.
**Cadastro Duplicado**: O sistema impede o cadastro duplicado de alunos, professores e disciplinas, garantindo integridade dos dados.
**Validação de Notas**: Caso o aluno não tenha notas atribuídas, o sistema lida com essa situação, evitando exceções como NullPointerException.

## Conclusão

### Este Sistema de Gerenciamento Escolar foi desenvolvido com uma arquitetura modular, fácil de entender e manter. Ele cumpre todos os requisitos para o gerenciamento acadêmico básico, oferecendo uma solução simples, mas poderosa, para o cadastro de alunos, professores e disciplinas, além de um controle eficaz de notas e emissão de boletins. A modularidade do código também permite fácil expansão para futuras funcionalidades, tornando-o um sistema flexível e preparado para novas demandas.
