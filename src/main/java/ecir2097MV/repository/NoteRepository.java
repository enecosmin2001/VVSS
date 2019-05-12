package ecir2097MV.repository;

import ecir2097MV.model.Nota;
import ecir2097MV.utils.NotaException;

import java.util.List;

public interface NoteRepository {
    public void addNota(Nota nota) throws NotaException;
    public List<Nota> getNote();
    public void readNote(String fisier);
}
