package repositorios;

import entidades.Professor;
import java.util.HashMap;
import java.util.Map;

public class RepositorioProfessores {
    private Map<String, Professor> professores = new HashMap<>();

    public void adicionarProfessor(Professor professor) {
        professores.put(professor.getId(), professor);
    }

    public Professor buscarProfessor(String id) {
        return professores.get(id);
    }

    public void removerProfessor(String id) {
        professores.remove(id);
    }
}
