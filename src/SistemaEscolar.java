import entidades.*;
import repositorios.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaEscolar {
    private static RepositorioAlunos repoAlunos = new RepositorioAlunos();
    private static RepositorioDisciplinas repoDisciplinas = new RepositorioDisciplinas();
    private static RepositorioProfessores repoProfessores = new RepositorioProfessores();
    private static RepositorioTurmas repoTurmas = new RepositorioTurmas(); 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Adicionar Aluno");
            System.out.println("2. Adicionar Disciplina");
            System.out.println("3. Adicionar Professor");
            System.out.println("4. Criar Turma");
            System.out.println("5. Atribuir Notas");
            System.out.println("6. Alterar Notas");
            System.out.println("7. Emitir Boletim");
            System.out.println("8. Remover Aluno da Turma");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        adicionarAluno(scanner);
                        break;
                    case 2:
                        adicionarDisciplina(scanner);
                        break;
                    case 3:
                        adicionarProfessor(scanner);
                        break;
                    case 4:
                        criarTurma(scanner);
                        break;
                    case 5:
                        atribuirNotas(scanner);
                        break;
                    case 6:
                        alterarNotas(scanner);
                        break;
                    case 7:
                        emitirBoletim(scanner);
                        break;
                    case 8:
                        removerAlunoDaTurma(scanner);
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida! Tenta de novo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Use números pra escolher as opções.");
                scanner.nextLine(); 
            }
        }
    }

    private static void adicionarAluno(Scanner scanner) {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        if (repoAlunos.buscarAluno(matricula) != null) {
            System.out.println("Erro: Aluno já cadastrado.");
            return;
        }

        Aluno aluno = new Aluno(nome, matricula);
        repoAlunos.adicionarAluno(aluno);
        System.out.println("Aluno adicionado com sucesso!");
    }

    private static void adicionarDisciplina(Scanner scanner) {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        if (repoDisciplinas.buscarDisciplina(codigo) != null) {
            System.out.println("Erro: Disciplina já cadastrada.");
            return;
        }

        Disciplina disciplina = new Disciplina(nome, codigo);
        repoDisciplinas.adicionarDisciplina(disciplina);
        System.out.println("Disciplina adicionada com sucesso!");
    }

    private static void adicionarProfessor(Scanner scanner) {
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("ID do professor: ");
        String id = scanner.nextLine();

        if (repoProfessores.buscarProfessor(id) != null) {
            System.out.println("Erro: Professor já cadastrado.");
            return;
        }

        Professor professor = new Professor(nome, id);
        repoProfessores.adicionarProfessor(professor);
        System.out.println("Professor adicionado com sucesso!");
    }

    private static void criarTurma(Scanner scanner) {
        System.out.print("Código da disciplina para a turma: ");
        String codigoDisciplina = scanner.nextLine();
        Disciplina disciplina = repoDisciplinas.buscarDisciplina(codigoDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("ID do professor da turma: ");
        String idProfessor = scanner.nextLine();
        Professor professor = repoProfessores.buscarProfessor(idProfessor);

        if (professor == null) {
            System.out.println("Professor não encontrado!");
            return;
        }

        Turma turma = new Turma(disciplina, professor);
        repoTurmas.adicionarTurma(turma);
        System.out.println("Turma criada com sucesso!");

        while (true) {
            System.out.print("Deseja adicionar um aluno à turma? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                System.out.print("Matrícula do aluno: ");
                String matricula = scanner.nextLine();
                Aluno aluno = repoAlunos.buscarAluno(matricula);

                if (aluno == null) {
                    System.out.println("Aluno não encontrado!");
                } else {
                    turma.adicionarAluno(aluno);
                    System.out.println("Aluno adicionado à turma.");
                }
            } else {
                break;
            }
        }
    }

    private static void atribuirNotas(Scanner scanner) {
        Turma turma = selecionarTurma(scanner);
        if (turma == null) return;

        for (Aluno aluno : turma.getAlunos()) {
            System.out.println("Atribuindo notas para o aluno: " + aluno.getNome());

            try {
                System.out.print("Nota 1: ");
                double nota1 = scanner.nextDouble();
                System.out.print("Nota 2: ");
                double nota2 = scanner.nextDouble();

                turma.registrarNotas(aluno, nota1, nota2);

                if ((nota1 + nota2) / 2 < 60) {
                    System.out.print("O aluno precisa de prova final. Nota da prova final: ");
                    double notaFinal = scanner.nextDouble();
                    turma.registrarNotaFinal(aluno, notaFinal);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Notas devem ser numéricas.");
                scanner.nextLine();
            }
        }
    }

    private static void alterarNotas(Scanner scanner) {
        Turma turma = selecionarTurma(scanner);
        if (turma == null) return;

        for (Aluno aluno : turma.getAlunos()) {
            System.out.println("Alterando notas para o aluno: " + aluno.getNome());

            try {
                System.out.print("Nova Nota 1: ");
                double nota1 = scanner.nextDouble();
                System.out.print("Nova Nota 2: ");
                double nota2 = scanner.nextDouble();

                turma.alterarNotas(aluno, nota1, nota2);
                System.out.println("Notas alteradas com sucesso!");

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Notas devem ser numéricas.");
                scanner.nextLine(); 
            }
        }
    }

    private static void emitirBoletim(Scanner scanner) {
        Turma turma = selecionarTurma(scanner);
        if (turma == null) return;

        for (Aluno aluno : turma.getAlunos()) {
            String boletim = turma.emitirBoletim(aluno);
            System.out.println(boletim);
        }
    }

    private static void removerAlunoDaTurma(Scanner scanner) {
        Turma turma = selecionarTurma(scanner);
        if (turma == null) return;

        System.out.print("Matrícula do aluno para remover: ");
        String matricula = scanner.nextLine();
        Aluno aluno = repoAlunos.buscarAluno(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
        } else {
            turma.removerAluno(aluno);
            System.out.println("Aluno removido da turma.");
        }
    }

    private static Turma selecionarTurma(Scanner scanner) {
        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        
        List<Turma> turmasDisciplina = repoTurmas.buscarTurmasPorDisciplina(codigoDisciplina);
        
        if (turmasDisciplina.isEmpty()) {
            System.out.println("Nenhuma turma encontrada para essa disciplina.");
            return null;
        }
        
        System.out.println("Turmas disponíveis para a disciplina " + codigoDisciplina + ":");
        for (int i = 0; i < turmasDisciplina.size(); i++) {
            System.out.println((i + 1) + ". Professor: " + turmasDisciplina.get(i).getProfessor().getNome());
        }
        
        System.out.print("Escolha uma turma (número): ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
        
        if (opcao < 1 || opcao > turmasDisciplina.size()) {
            System.out.println("Opção inválida!");
            return null;
        }
        
        return turmasDisciplina.get(opcao - 1);
    }
}
