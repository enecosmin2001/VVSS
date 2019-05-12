package ecir2097MV.repository;

import ecir2097MV.utils.ClasaException;
import ecir2097MV.model.Corigent;
import ecir2097MV.model.Elev;
import ecir2097MV.model.Medie;
import ecir2097MV.model.Nota;
import ecir2097MV.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ClasaService implements ClasaRepository{

    private HashMap<Elev, HashMap<String, List<Double>>> clasa;

    public ClasaService() {
        this.clasa = new HashMap<>();
    }

    @Override
    public void creazaClasa(List<Elev> elevi, List<Nota> note) {
        List<String> materii = new LinkedList<String>();
        for(Nota nota : note) {
            if(!materii.contains(nota.getMaterie()))
                materii.add(nota.getMaterie());
        }
        for (Elev elev : elevi) {
            HashMap<String, List<Double>> situatie = new HashMap<String, List<Double>>();
            for(String materie : materii) {
                List<Double> noteMaterie = new LinkedList<Double>();
                for(Nota nota : note)
                    if(nota.getMaterie().equals(materie) && nota.getNrmatricol() == elev.getNrmatricol())
                        noteMaterie.add(nota.getNota());
                situatie.put(materie, noteMaterie);
            }
            clasa.put(elev, situatie);
        }
    }

    @Override
    public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
        return null;
    }

    @Override
    public List<Medie> calculeazaMedii() throws ClasaException {
        List<Medie> medii = new LinkedList<Medie>();
        if(clasa.size() > 0) {  //1
            for(Elev elev : clasa.keySet()) {  //2
                Medie medie = new Medie();
                medie.setElev(elev);
                int nrMaterii = 0;//3
                double sumaMedii = 0;
                double medieElev = 0;
                for(String materie : clasa.get(elev).keySet()) { //4
                    nrMaterii++;
                    List<Double> noteElev = clasa.get(elev).get(materie);
                    int nrNote = noteElev.size();//5
                    int i = 0;
                    double suma = 0;
                    if(nrNote >= 0) { //6
                        while(i < nrNote) { //7
                            double nota = noteElev.get(i);
                            suma += nota;//8
                            i++;
                        }
                        if (i != 0) //9
                            sumaMedii = sumaMedii + suma/i;
                    }
                }
                medieElev = sumaMedii / nrMaterii;
                medie.setMedie(medieElev);//10
                medii.add(medie);
            }
        }
        else //11
            throw new ClasaException(Constants.emptyRepository);
        return medii;//12
    }



    @Override
    public void addNota(Elev elev, Nota nota){
        String materie = nota.getMaterie();
        this.clasa.get(elev).get(materie).add(nota.getNota());
    }

    @Override
    public List<Corigent> getCorigenti() {
        List<Corigent> corigenti = new ArrayList<Corigent>();
        if(clasa.size() >= 0) {
            for(Elev elev : clasa.keySet()) {
                Corigent corigent = new Corigent(elev.getNume(), 0);
                for(String materie : clasa.get(elev).keySet()) {
                    List<Double> noteElev = clasa.get(elev).get(materie);
                    int nrNote = noteElev.size();
                    int i = 0;
                    double suma = 0;
                    if(nrNote >= 0) {
                        while(i < nrNote) {
                            double nota = noteElev.get(i);
                            suma += nota;
                            i++;
                        }
                        double media = suma/i;
                        if (media < 5 && media != 0 )
                            corigent.setNrMaterii(corigent.getNrMaterii() + 1);
                    }
                }
                if(corigent.getNrMaterii() > 0) {
                    int i = 0;
                    while(i < corigenti.size() && corigenti.get(i).getNrMaterii() < corigent.getNrMaterii())
                        i++;
                    if(i != corigenti.size() && corigenti.get(i).getNrMaterii() == corigent.getNrMaterii()) {
                        while(i < corigenti.size() && corigenti.get(i).getNrMaterii() == corigent.getNrMaterii() && corigenti.get(i).getNumeElev().compareTo(corigent.getNumeElev()) < 0)
                            i++;
                        corigenti.add(i, corigent);
                    }
                    else
                        corigenti.add(i, corigent);
                }
            }
        }
        return corigenti;
    }
}
