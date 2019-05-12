package ecir2097MV.repository;

import ecir2097MV.utils.ClasaException;
import ecir2097MV.model.Corigent;
import ecir2097MV.model.Elev;
import ecir2097MV.model.Medie;
import ecir2097MV.model.Nota;

import java.util.HashMap;
import java.util.List;

public interface ClasaRepository {
    public void creazaClasa(List<Elev> elevi, List<Nota> note);
    public HashMap<Elev, HashMap<String, List<Double>>> getClasa();
    public List<Medie> calculeazaMedii() throws ClasaException;
    public void addNota(Elev elev, Nota nota);
    public List<Corigent> getCorigenti();
}
