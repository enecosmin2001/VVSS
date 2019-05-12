package ecir2097MV.repository;

import ecir2097MV.model.Elev;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class EleviService implements EleviRepository{
    private List<Elev> elevi;

    public EleviService() {
        this.elevi = new LinkedList<Elev>();
    }

    @Override
    public void addElev(Elev e) {
        this.elevi.add(e);
    }

    @Override
    public List<Elev> getElevi() {
        return this.elevi;
    }

    @Override
    public Elev getElev(int nrMatricol) {
        for (Elev e :
             this.elevi) {
            if (e.getNrmatricol() == nrMatricol) { return e; }
        }
        return null;
    }

    @Override
    public void readElevi(String fisier) {
        try {
            FileInputStream fstream = new FileInputStream(fisier);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Elev elev = new Elev(Integer.parseInt(values[0]), values[1]);
               this.addElev(elev);
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
}
