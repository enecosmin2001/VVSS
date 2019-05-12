package ecir2097MV.model;

import java.util.Date;

public class Nota {
    private int nrmatricol;
    private String materie;
    private double nota;
    private String data;

    public Nota(int nrmatricol, String materie, double nota, String data) {
        this.setNrmatricol(nrmatricol);
        this.setMaterie(materie);
        this.setNota(nota);
        this.setData(data);
    }

    /**
     * @return the nrmatricol
     */
    public int getNrmatricol() {
        return nrmatricol;
    }

    /**
     * @param nrmatricol the nrmatricol to set
     */
    public void setNrmatricol(int nrmatricol) {
        this.nrmatricol = nrmatricol;
    }

    /**
     * @return the materie
     */
    public String getMaterie() {
        return materie;
    }

    /**
     * @param materie the materie to set
     */
    public void setMaterie(String materie) {
        this.materie = materie;
    }

    /**
     * @return the nota
     */
    public double getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
