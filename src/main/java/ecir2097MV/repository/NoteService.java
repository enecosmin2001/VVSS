package ecir2097MV.repository;

import ecir2097MV.model.Nota;
import ecir2097MV.utils.Constants;
import ecir2097MV.utils.NotaException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class NoteService implements NoteRepository{
    private List<Nota> note;

    public NoteService() {
        this.note = new LinkedList<Nota>();
    }

    @Override
    public void addNota(Nota nota) throws NotaException {
        // TODO Auto-generated method stub
        if(!validareNota(nota))
            return;
        this.note.add(nota);
    }

    @Override
    public List<Nota> getNote() {
        return this.note;
    }

    @Override
    public void readNote(String fisier) {
        try {
            FileInputStream fstream = new FileInputStream(fisier);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Nota nota = new Nota(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), values[3]);
                note.add(nota);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean validareNota(Nota nota) throws NotaException {
        // TODO Auto-generated method stub
        if(nota.getMaterie().length() < 5 || nota.getMaterie().length() > 20)
            throw new NotaException(Constants.invalidMateria);
        if(nota.getNrmatricol() < Constants.minNrmatricol || nota.getNrmatricol() > Constants.maxNrmatricol)
            throw new NotaException(Constants.invalidNrmatricol);
        if(nota.getNota() < Constants.minNota || nota.getNota() > Constants.maxNota)
            throw new NotaException(Constants.invalidNota);
        if(nota.getNota() != (int)nota.getNota())
            throw new NotaException(Constants.invalidNota);
        if(nota.getNrmatricol() != (int)nota.getNrmatricol())
            throw new NotaException(Constants.invalidNrmatricol);
        return true;
    }
}
