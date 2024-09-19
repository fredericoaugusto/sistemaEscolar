package repositorios;

import entidades.Aluno;
import java.util.HashMap;
import java.util.Map;

public class RepositorioAlunos {
    private Map<String, Aluno> alunos = new HashMap<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.put(aluno.getMatricula(), aluno);
    }

    public Aluno buscarAluno(String matricula) {
        return alunos.get(matricula);
    }

    public void removerAluno(String matricula) {
        alunos.remove(matricula);
    }
}
