package ecir2097MV.main;

import ecir2097MV.controller.NoteController;
import ecir2097MV.model.Corigent;
import ecir2097MV.model.Elev;
import ecir2097MV.model.Medie;
import ecir2097MV.model.Nota;
import ecir2097MV.utils.ClasaException;
import ecir2097MV.utils.NotaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class StartApp {
    /**
     * @param args
     * @throws ClasaException
     */
    public static void main(String[] args){
        NoteController ctrl = new NoteController();
        List<Medie> medii = new LinkedList<Medie>();
        List<Corigent> corigenti = new ArrayList<Corigent>();
        ctrl.readElevi(args[0]);
        ctrl.readNote(args[1]);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        boolean gasit = false;
        while (!gasit) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Iesire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                int option = Integer.parseInt(br.readLine());
                switch (option) {
                    case 1:
                        Nota nota = readNota(ctrl, br);
                        ctrl.addNota(nota);
                        break;
                    case 2:
                        medii = ctrl.calculeazaMedii();
                        for (Medie medie : medii)
                            System.out.println(medie);
                        break;
                    case 3:
                        corigenti = ctrl.getCorigenti();
                        for (Corigent corigent : corigenti)
                            System.out.println(corigent);
                        break;
                    case 4:
                        gasit = true;
                        break;
                    default:
                        System.out.println("Introduceti o optiune valida!");
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("Va rugam sa verifica numarul introdus!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClasaException e) {
                System.out.println(e.getMessage());
            } catch (NotaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Nota readNota(NoteController ctrl,BufferedReader br){
        boolean go = true;
        while(go) {
            for (Elev e :
                    ctrl.getElevi()) {
                System.out.println(e.toString());
            }
            try {
                System.out.println("Introdu numar matricol : ");
                int nr = Integer.parseInt(br.readLine());
                System.out.println("Introdu materia : ");
                String materie = br.readLine();
                System.out.println("Introdu nota : ");
                int nota = Integer.parseInt(br.readLine());
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                return new Nota(nr, materie, nota, date);
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("Nota trebuie sa fie un numar intreg de la 1-10!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
