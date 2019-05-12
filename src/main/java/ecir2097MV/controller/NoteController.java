package ecir2097MV.controller;

import ecir2097MV.model.Corigent;
import ecir2097MV.model.Elev;
import ecir2097MV.model.Medie;
import ecir2097MV.model.Nota;
import ecir2097MV.repository.*;
import ecir2097MV.utils.ClasaException;
import ecir2097MV.utils.NotaException;

import java.util.HashMap;
import java.util.List;

public class NoteController {
    private NoteRepository note;
    private ClasaRepository clasa;
    private EleviRepository elevi;

    public NoteController() {
        this.note = new NoteService();
        this.clasa = new ClasaService();
        this.elevi = new EleviService();
    }

    public void addNota(Nota nota) throws NotaException {
        this.note.addNota(nota);
        this.clasa.addNota(this.elevi.getElev(nota.getNrmatricol()),nota);
    }

    public void addElev(Elev elev) {
        this.elevi.addElev(elev);
    }

    public void creeazaClasa(List<Elev> elevi, List<Nota> note) { this.clasa.creazaClasa(elevi, note);}

    public List<Medie> calculeazaMedii() throws ClasaException {
        return this.clasa.calculeazaMedii();
    }

    public List<Nota> getNote() {
        return note.getNote();
    }

    public List<Elev> getElevi() {
        return elevi.getElevi();
    }

    public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
        return clasa.getClasa();
    }


    public void readElevi(String fisier) {
        elevi.readElevi(fisier);
    }

    public void readNote(String fisier) {
        note.readNote(fisier);
    }

    public List<Corigent> getCorigenti() {
        return clasa.getCorigenti();
    }
}
