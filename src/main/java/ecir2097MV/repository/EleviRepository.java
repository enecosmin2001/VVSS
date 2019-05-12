package ecir2097MV.repository;

import ecir2097MV.model.Elev;

import java.util.List;

public interface EleviRepository {
    public void addElev(Elev e);
    public List<Elev> getElevi();
    public Elev getElev(int nrMatricol);
    public void readElevi(String fisier);
}
