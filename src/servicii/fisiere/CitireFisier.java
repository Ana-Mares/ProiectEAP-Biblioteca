package servicii.fisiere;

import clase.*;
import servicii.ServiceDate;
import validari.Validari;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitireFisier {
    public static CitireFisier instanta = null;

    private CitireFisier() {}

    public static CitireFisier getInstanta() {
        if(instanta == null)
            instanta = new CitireFisier();
        return instanta;
    }

    Validari valid = new Validari();

    public void citireCalculator (ArrayList<Calculator> calculatoare) {
        BufferedReader cititor = null;
        try {
            String linie = "";
            cititor = new BufferedReader(new FileReader("src\\servicii\\fisiere\\resurse\\calculatoare.csv"));
            while (( linie = cititor.readLine()) != null) {
                String[] info = linie.split(",");
                if (info.length > 0){
                    Data achizitieTemp = new Data(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                    Calculator calculatorTemp = new Calculator(achizitieTemp, info[3]);
                    calculatoare.add(calculatorTemp);
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Eroare la citirea din fisierul calculatoare.csv.");
        }

        try {
            cititor.close();
        }
        catch (IOException exception) {
            System.out.println("Eroare la inchiderea fisierului calculatoare.csv.");
        }
    }

    public void citireEditie (ArrayList<Editie> editii) {
        BufferedReader cititor = null;
        try {
            String linie = "";
            cititor = new BufferedReader(new FileReader("src\\servicii\\fisiere\\resurse\\editii.csv"));
            while (( linie = cititor.readLine()) != null) {
                String[] info = linie.split(",");
                if (info.length > 0){
                    Data achizitieTemp = new Data(Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
                    Autor autorTemp = new Autor(info[7], info[8], info[9], info[10]);
                    Editie editieTemp = new Editie(achizitieTemp, info[6], autorTemp, info[11], info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                    if (valid.validEditie(editieTemp))
                        editii.add(editieTemp);
                    else System.out.println("NU S-A PUTUT ADAUGA CARTEA/ EDITIA: " + editieTemp + "\n");
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Eroare la citirea din fisierul editii.csv.");
        }

        try {
            cititor.close();
        }
        catch (IOException exception) {
            System.out.println("Eroare la inchiderea fisierului editii.csv.");
        }
    }

    public void citireCititori (ArrayList<Cititor> cititori) {
        BufferedReader cititor = null;
        try {
            String linie = "";
            cititor = new BufferedReader(new FileReader("src\\servicii\\fisiere\\resurse\\cititori.csv"));
            while (( linie = cititor.readLine()) != null) {
                String[] info = linie.split(",");
                if (info.length > 0){
                    Data nastereTemp = new Data(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                    Adresa adresaTemp = new Adresa(info[6], info[7], info[8], Integer.parseInt(info[9]));
                    Data dAbonamentTemp = new Data(Integer.parseInt(info[10]), Integer.parseInt(info[11]), Integer.parseInt(info[12]));
                    Abonament abonamentTemp = new Abonament(dAbonamentTemp);

                    Cititor persoanaTemp = null;
                    persoanaTemp = new Cititor(info[13], info[14], nastereTemp, info[3], info[4], info[5], adresaTemp, abonamentTemp);
                    if (valid.validCititor(persoanaTemp))
                        cititori.add(persoanaTemp);
                    else System.out.println("NU S-A PUTUT ADAUGA PERSOANA: " + persoanaTemp + "\n");
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Eroare la citirea din fisierul cititori.csv.");
        }

        try {
            cititor.close();
        }
        catch (IOException exception) {
            System.out.println("Eroare la inchiderea fisierului cititori.csv.");
        }
    }



    public void citireAngajati (ArrayList<Angajat> angajati) {
        BufferedReader cititor = null;
        try {
            String linie = "";
            cititor = new BufferedReader(new FileReader("src\\servicii\\fisiere\\resurse\\angajati.csv"));
            while (( linie = cititor.readLine()) != null) {
                String[] info = linie.split(",");
                if (info.length > 0){
                    Data angajareTemp = new Data(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                    Data nastereTemp = new Data(Integer.parseInt(info[4]), Integer.parseInt(info[5]), Integer.parseInt(info[6]));
                    Adresa adresaTemp = new Adresa(info[10], info[11], info[12], Integer.parseInt(info[13]));
                    Data dAbonamentTemp = new Data(Integer.parseInt(info[14]), Integer.parseInt(info[15]), Integer.parseInt(info[16]));
                    Abonament abonamentTemp = new Abonament(dAbonamentTemp);

                    Angajat persoanaTemp = null;
                        persoanaTemp = new Angajat(info[17], info[18], nastereTemp, info[7], info[8], info[9], adresaTemp, abonamentTemp, angajareTemp, Integer.parseInt(info[3]));
                        if (valid.validAngajat(persoanaTemp))
                            angajati.add(persoanaTemp);
                        else System.out.println("NU S-A PUTUT ADAUGA PERSOANA: " + persoanaTemp + "\n");
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Eroare la citirea din fisierul angajati.csv.");
        }

        try {
            cititor.close();
        }
        catch (IOException exception) {
            System.out.println("Eroare la inchiderea fisierului angajati.csv.");
        }
    }

    public void citireImprumuturi (ArrayList<Cititor> cititori, ArrayList<Inventar> inventar) {
        BufferedReader cititor = null;
        try {
            String linie = "";
            cititor = new BufferedReader(new FileReader("src\\servicii\\fisiere\\resurse\\imprumuturi.csv"));
            while (( linie = cititor.readLine()) != null) {
                int existaCnp = 0;

                String[] info = linie.split(",");
                if (info.length > 0){
                    for (int i = 0; i < cititori.size(); i++) {
                        if (cititori.get(i).getCnp().equals(info[0])) {
                            existaCnp = 1;
                            Data imprumutareTemp = new Data(Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]));
                            Inventar obiect = null;
                            obiect = inventar.get(Integer.parseInt(info[4]));
                            ServiceDate sDate = new ServiceDate();

                            int existaImprumut = 0;
                            for ( int j = 0; j < cititori.get(i).getImprumuturi().size(); j++ ) {
                                if ( cititori.get(i).getImprumuturi().get(j).getImprumutare().equals(imprumutareTemp) ) {  //adaug un obiect la un imprumut deja existent
                                    List <Inventar> inventarTemp = cititori.get(i).getImprumuturi().get(j).getObiecte();
                                    inventarTemp.add(obiect);
                                    cititori.get(i).getImprumuturi().get(j).setObiecte( inventarTemp );
                                    existaImprumut = 1;
                                }
                                }
                            if ( existaImprumut == 0 ) {   //adaug imprumut nou
                                List<Inventar> inventarTemp = new ArrayList<Inventar>();
                                inventarTemp.add(obiect);
                                Imprumut imprumutTemp = new Imprumut(imprumutareTemp, sDate.adaugaSaptamani(imprumutareTemp, 2), inventarTemp);
                               // Imprumut imprumutTemp = new Imprumut(imprumutareTemp, new Data(10, 10, 1900), inventarTemp); //nu se adauga niciun imprumut, deoarece data de restituire nu va fi valida

                                if (valid.validImprumut(imprumutTemp)){
                                    List<Imprumut> imprumuturiTemp = new ArrayList<Imprumut>();
                                    imprumuturiTemp = cititori.get(i).getImprumuturi();
                                    imprumuturiTemp.add(imprumutTemp);
                                    cititori.get(i).setImprumuturi(imprumuturiTemp);

                                }

                                else System.out.println("NU S-A PUTUT ADAUGA IMPRUMUTUL: " + imprumutTemp + "\n");
                                //List<Imprumut> imprumuturiTemp = new ArrayList<Imprumut>();
                               // imprumuturiTemp = cititori.get(i).getImprumuturi();
                               // imprumuturiTemp.add(imprumutTemp);

                               // cititori.get(i).setImprumuturi(imprumuturiTemp);
                            }
                            }
                        }
                    if (existaCnp == 0) System.out.println("Nu s-a putut adauga inregistrarea. CNP invalid.");
                }
                    }
        }
        catch (Exception exception) {
            System.out.println("Eroare la citirea din fisierul imprumuturi.csv.");
        }

        try {
            cititor.close();
        }
        catch (IOException exception) {
            System.out.println("Eroare la inchiderea fisierului imprumuturi.csv.");
        }
    }

}
