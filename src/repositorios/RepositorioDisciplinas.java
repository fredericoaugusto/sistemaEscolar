package repositorios;

import entidades.Disciplina;
import java.util.HashMap;
import java.util.Map;

public class RepositorioDisciplinas {
    private Map<String, Disciplina> disciplinas = new HashMap<>();

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(), disciplina);
    }

    public Disciplina buscarDisciplina(String codigo) {
        return disciplinas.get(codigo);
    }

    public void removerDisciplina(String codigo) {
        disciplinas.remove(codigo);
    }
}
