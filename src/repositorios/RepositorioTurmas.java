package repositorios;

import entidades.Turma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioTurmas {
    private Map<String, List<Turma>> turmas = new HashMap<>();

    public void adicionarTurma(Turma turma) {
        turmas.computeIfAbsent(turma.getDisciplina().getCodigo(), k -> new ArrayList<>()).add(turma);
    }

    public List<Turma> buscarTurmasPorDisciplina(String codigoDisciplina) {
        return turmas.getOrDefault(codigoDisciplina, new ArrayList<>());
    }

    public void removerTurma(String codigoDisciplina, Turma turma) {
        List<Turma> listaTurmas = turmas.get(codigoDisciplina);
        if (listaTurmas != null) {
            listaTurmas.remove(turma);
            if (listaTurmas.isEmpty()) {
                turmas.remove(codigoDisciplina);
            }
        }
    }
}
