package clase;

import java.util.Objects;

public class Adresa {
    private String judet;
    private String localitate;
    private String strada;
    private int numar;


    public Adresa(String judet, String localitate, String strada, int numar) {
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
        this.numar = numar;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    @Override
    public String toString() {
        return "Adresa:" +
                "Judet " + judet +
                ", localitate " + localitate +
                ", strada " + strada +
                ", numarul " + numar;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Adresa adresa = (Adresa) obj;
        return numar == adresa.numar && Objects.equals(judet, adresa.judet) && Objects.equals(localitate, adresa.localitate) && Objects.equals(strada, adresa.strada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(judet, localitate, strada, numar);
    }
}
