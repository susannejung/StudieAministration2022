import java.util.ArrayList;

public class Studerende {
    private Integer stdnr;
    private String fnavn;
    private String enavn;
    private String adresse;
    private String postnr;
    private String mobil;
    private String klasse;

    public ArrayList<Fag> fagliste=new ArrayList<Fag>();

    public Studerende(){

    }

   /* @Override
    public String toString() {
        return "Studerende{" +
                "stdnr=" + stdnr +
                ", fnavn='" + fnavn + '\'' +
                ", enavn='" + enavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnr='" + postnr + '\'' +
                ", mobil='" + mobil + '\'' +
                ", klasse='" + klasse + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Studerende{" +
                "stdnr=" + stdnr +
                ", fnavn='" + fnavn + '\'' +
                ", enavn='" + enavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnr='" + postnr + '\'' +
                ", mobil='" + mobil + '\'' +
                ", klasse='" + klasse + '\'' +
                ", fagliste=" + fagliste +
                '}';
    }

    public Studerende(Integer stdnr, String fnavn, String enavn, String adresse, String postnr, String mobil, String klasse) {
        this.stdnr = stdnr;
        this.fnavn = fnavn;
        this.enavn = enavn;
        this.adresse = adresse;
        this.postnr = postnr;
        this.mobil = mobil;
        this.klasse = klasse;
    }

    public Integer getStdnr() {
        return stdnr;
    }

    public void setStdnr(Integer stdnr) {
        this.stdnr = stdnr;
    }

    public String getFnavn() {
        return fnavn;
    }

    public void setFnavn(String fnavn) {
        this.fnavn = fnavn;
    }

    public String getEnavn() {
        return enavn;
    }

    public void setEnavn(String enavn) {
        this.enavn = enavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public ArrayList<Fag> getFagliste() {
        return fagliste;
    }

    public void setFagliste(ArrayList<Fag> fagliste) {
        this.fagliste = fagliste;
    }


}

