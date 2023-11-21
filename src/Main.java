import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Studerende inputstuddata(){
        Scanner in=new Scanner(System.in);
        Studerende s=new Studerende();
        System.out.println("fnavn, enavn, adresse, postnr, mobil, klasse");
        s.setFnavn(in.next());
        s.setEnavn(in.next());
        s.setAdresse(in.next());
        s.setPostnr(in.next());
        s.setMobil(in.next());
        s.setKlasse(in.next());

        return s;
    }


    public static void menu(){
        int valg=1;
        Scanner input=new Scanner(System.in);
        DbSql db=new DbSql();
        ArrayList<Studerende> tabel=new ArrayList<Studerende>();
        while(valg!=0) {
            System.out.println("1. Opret ny studerende");
            System.out.println("2. Opret nyt fag ");
            System.out.println("3. Tildel studerende nyt fag ");
            System.out.println("4. Udskriv stamdata em en studerende ");
            System.out.println("5. Udskriv alle oplysninger om alle studerende ");
            System.out.println("6. Udskriv alle oplysninger om en studerende ");
            System.out.println("7. Søg alle der har et givet fag ");
            System.out.println("8. Opdater studerendes klasse ");
            System.out.println("9. Slet studerende ");
            System.out.println("0. Stop programmet");
            System.out.print("Indtast valg:  ");
            valg=input.nextInt();
            switch(valg){
                case 1: Studerende s;
                    s=inputstuddata();
                    db.indsaetStud(s);
                    break;
                case 2:
                    System.out.print("Indtast fagnavn: ");
                    Fag f=new Fag();
                    f.setFagnavn(input.next());
                    db.indsaetFag(f);
                    break;
                case 3: System.out.println("Indtast stdnr og fagnr");
                    db.indsaetfagstud(input.nextInt(),input.nextInt());
                    break;
                case 4: System.out.println("Indtast stdnr");
                    s=db.soegStdnr(input.nextInt());
                    System.out.println(s.toString());
                    break;
                case 5: tabel.clear();
                    tabel=db.alleoplysninger();
                    for(int i=0;i<tabel.size();i++) {
                        System.out.format("%s \n",tabel.get(i).toString());
                    }
                    break;
                case 6: System.out.println("Indtast stdnr");
                    s=db.soegAltStdnr(input.nextInt());
                    System.out.println(s.toString());
                    break;
                case 7: System.out.println("Indtast fagnr");
                    tabel.clear();
                    tabel=db.allestudmedfag(input.nextInt());
                    for(int i=0;i<tabel.size();i++) {
                        System.out.format("%s \n",tabel.get(i).toString());
                    }
                    break;
                case 8: System.out.println("Indtast stdnr og den nye klasse");
                    db.updateStudKlasse(input.nextInt(),input.next());
                    break;
            }
        }

    }

    public static void main(String[] args) {
        menu();

    }
}
