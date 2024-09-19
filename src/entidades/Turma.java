package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Turma {
    private Disciplina disciplina;
    private Professor professor;
    private List<Aluno> alunos;
    private Map<Aluno, Double[]> notasParciais;
    private Map<Aluno, Double> notasFinais;

    // Construtor
    public Turma(Disciplina disciplina, Professor professor) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.alunos = new ArrayList<>();
        this.notasParciais = new HashMap<>();
        this.notasFinais = new HashMap<>();
    }

    // Getters
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() { 
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    // Adicionar aluno
    public void adicionarAluno(Aluno aluno) {
        if (alunos.contains(aluno)) {
            System.out.println("Erro: O aluno " + aluno.getNome() + " já está cadastrado nesta turma.");
            return;
        }
        alunos.add(aluno);
        notasParciais.put(aluno, new Double[2]);
        notasFinais.put(aluno, null);
        System.out.println("Aluno " + aluno.getNome() + " adicionado com sucesso à turma.");
    }

    // Colocar nota parcial
    public void registrarNotas(Aluno aluno, double nota1, double nota2) {
        if (nota1 < 0 || nota1 > 100 || nota2 < 0 || nota2 > 100) {
            System.out.println("Erro: As notas devem estar entre 0 e 100.");
            return;
        }
        if (alunos.contains(aluno)) {
            notasParciais.put(aluno, new Double[]{nota1, nota2});
            System.out.println("Notas registradas para " + aluno.getNome());
        } else {
            System.out.println("Erro: Aluno não encontrado na turma.");
        }
    }

    // Colocar nota final
    public void registrarNotaFinal(Aluno aluno, double notaFinal) {
        if (notaFinal < 0 || notaFinal > 100) {
            System.out.println("Erro: A nota final deve estar entre 0 e 100.");
            return;
        }
        if (alunos.contains(aluno)) {
            notasFinais.put(aluno, notaFinal);
            System.out.println("Nota final registrada para " + aluno.getNome());
        } else {
            System.out.println("Erro: Aluno não encontrado na turma.");
        }
    }

    // Alterar notas
    public void alterarNotas(Aluno aluno, double nota1, double nota2) {
        if (nota1 < 0 || nota1 > 100 || nota2 < 0 || nota2 > 100) {
            System.out.println("Erro: As notas devem estar entre 0 e 100.");
            return;
        }
        if (alunos.contains(aluno)) {
            notasParciais.put(aluno, new Double[]{nota1, nota2});
            System.out.println("Notas alteradas com sucesso para " + aluno.getNome());
        } else {
            System.out.println("Erro: Aluno não encontrado na turma.");
        }
    }

    // Tirar aluno da turma
    public void removerAluno(Aluno aluno) {
        if (alunos.remove(aluno)) {
            notasParciais.remove(aluno);
            notasFinais.remove(aluno);
            System.out.println("Aluno " + aluno.getNome() + " removido da turma.");
        } else {
            System.out.println("Erro: Aluno não encontrado na turma.");
        }
    }

    // Emitir boletim
    public String emitirBoletim(Aluno aluno) {
        StringBuilder boletim = new StringBuilder();
        boletim.append("----- Boletim Escolar -----\n");
        boletim.append("Disciplina: ").append(disciplina.getNome()).append("\n");
        boletim.append("Professor: ").append(professor.getNome()).append("\n");

        Double[] notas = notasParciais.get(aluno);
        boletim.append("Aluno: ").append(aluno.getNome()).append(" - Matrícula: ").append(aluno.getMatricula()).append("\n");

        if (notas != null && notas[0] != null && notas[1] != null) {
            boletim.append("Notas Parciais: ").append(notas[0]).append(", ").append(notas[1]).append("\n");
        } else {
            boletim.append("Notas Parciais: Não atribuídas\n");
        }

        if (notasFinais.get(aluno) != null) {
            boletim.append("Nota Final: ").append(notasFinais.get(aluno)).append("\n");
        }

        // Cálculo da média e print de aprovado ou n
        double media = (notas != null && notas[0] != null && notas[1] != null)
                ? (notas[0] + notas[1]) / 2
                : 0;

        if (media < 60 && notasFinais.get(aluno) != null) {
            media = (media + notasFinais.get(aluno)) / 2;
        }

        boletim.append("Média Final: ").append(media).append("\n");

        if (media >= 60) {
            boletim.append("Status: Aprovado\n");
        } else {
            boletim.append("Status: Reprovado\n");
        }

        return boletim.toString();
    }
}
