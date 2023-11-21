import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DbSql {
    private Connection connection;
    private Statement stmt;
    private Statement stmt1;

    DbSql() {
        connection = null;
        stmt = null;
        try {
            String url = "jdbc:sqlite:C://Programmering1sem/Databaser/Database2.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void indsaetStud(Studerende s) {
        try {
            String sql = "INSERT INTO studerende (fnavn,enavn,adresse,postnr,mobil,klasse) VALUES('" +
                    s.getFnavn() + "','" + s.getEnavn() + "','";
            sql = sql + s.getAdresse() + "','" + s.getPostnr() + "','" + s.getMobil() + "','" + s.getKlasse() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void indsaetfagstud(int stdnr, int fagnr) {
        try {
            String sql = "INSERT INTO studfag (stdnr,fagnr) VALUES(" + stdnr + "," + fagnr + ")";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void indsaetFag(Fag f) {
        try {
            String sql = "INSERT INTO fag (fagnavn) VALUES('" + f.getFagnavn() + "');";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sletStud(Integer stdnr) {
        try {
            String sql = "DELETE FROM studerende WHERE id=" + String.valueOf(stdnr);
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateStudKlasse(int stdnr, String nyKlasse) {
        try {
            String sql = "UPDATE studerende SET klasse ='" + nyKlasse + "' WHERE stdnr=" + stdnr;
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Studerende soegStdnr(int stdnr) {
        Studerende s = new Studerende();
        String sql = "SELECT * from studerende where stdnr=" + String.valueOf(stdnr);
        try {
            connection.setAutoCommit(true);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                s.setStdnr(rs.getInt(1));
                s.setFnavn(rs.getString(2));
                s.setEnavn(rs.getString(3));
                s.setAdresse(rs.getString(4));
                s.setPostnr(rs.getString(5));
                s.setMobil(rs.getString(6));
                s.setKlasse(rs.getString(7));
            } else
                s = null;
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return s;
    }


    public Studerende soegAltStdnr(int stdnr) {
        Studerende s = new Studerende();
        String sql = "SELECT * from studerende left join studfag on studerende.stdnr=studfag.stdnr left join fag on studfag.fagnr=fag.fagnr where studerende.stdnr=" + String.valueOf(stdnr);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(sql);
            int fagnr;
            String fagnavn;
            while (rs.next()) {
                s.setStdnr(rs.getInt("stdnr"));
                s.setFnavn(rs.getString("fnavn"));
                s.setEnavn(rs.getString("enavn"));
                s.setAdresse(rs.getString("adresse"));
                s.setPostnr(rs.getString("postnr"));
                s.setMobil(rs.getString("mobil"));
                s.setKlasse(rs.getString("klasse"));
                String sql1 = "SELECT * from studfag left join fag on studfag.fagnr=fag.fagnr where studfag.stdnr=" + stdnr;
                rs1 = stmt1.executeQuery(sql1);
                s.fagliste.clear();
                while (rs1.next()) {
                    Fag f = new Fag();
                    f.setFagnr(rs1.getInt("fagnr"));
                    f.setFagnavn(rs1.getString("fagnavn"));
                    s.fagliste.add(f);
                }
            }
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return s;
    }


    public ArrayList<Studerende> soegefternavn(String enavn) {
        ArrayList<Studerende> tabel = new ArrayList<Studerende>();
        String sql = "SELECT * from studerende where enavn='" + enavn + "'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Studerende s = new Studerende();
            while (rs.next()) {
                s.setStdnr(rs.getInt("stdnr"));
                s.setFnavn(rs.getString("fnavn"));
                tabel.add(s);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tabel;
    }


    public ArrayList<Studerende> alleoplysninger() {
        ArrayList<Studerende> tabel = new ArrayList<Studerende>();
        // String sql = "SELECT * from studerende left join studfag on studerende.stdnr=studfag.stdnr left join fag on studfag.fagnr=fag.fagnr order by stdnr";
        String sql = "select * from studerende";
        try {
            Statement stmt = connection.createStatement();
            Statement stmt1 = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Studerende s = new Studerende();
                int nr = rs.getInt("stdnr");
                s.setStdnr(nr);
                s.setFnavn(rs.getString("fnavn"));
                s.setEnavn(rs.getString("enavn"));
                s.setAdresse(rs.getString("adresse"));
                s.setPostnr(rs.getString("postnr"));
                s.setMobil(rs.getString("mobil"));
                s.setKlasse(rs.getString("klasse"));
                String sql1 = "SELECT * from studfag left join fag on studfag.fagnr=fag.fagnr where studfag.stdnr=" + nr;
                ResultSet rs1 = stmt1.executeQuery(sql1);
                while (rs1.next()) {
                    Fag f = new Fag();
                    f.setFagnr(rs1.getInt("fagnr"));
                    f.setFagnavn(rs1.getString("fagnavn"));
                    s.fagliste.add(f);
                }
                tabel.add(s);
            }
            stmt.close();
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tabel;
    }


    public ArrayList<Studerende> allestud() {
        ArrayList<Studerende> tabel = new ArrayList<Studerende>();
        String sql = "SELECT * from studerende";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while (rs.next()) {
                Studerende s = new Studerende();
                s.setStdnr(rs.getInt("stdnr"));
                s.setFnavn(rs.getString("fnavn"));
                s.setEnavn(rs.getString("enavn"));
                s.setAdresse(rs.getString("adresse"));
                s.setPostnr(rs.getString("postnr"));
                s.setMobil(rs.getString("mobil"));
                s.setKlasse(rs.getString("klasse"));
                tabel.add(s);
                i++;
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tabel;
    }


    public ArrayList<Studerende> allestudmedfag(int fagnr) {
        ArrayList<Studerende> tabel = new ArrayList<Studerende>();
        String sql = "SELECT * from studerende inner join studfag on studerende.stdnr=studfag.stdnr where studfag.fagnr=" + fagnr;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Studerende s = new Studerende();
                s.setStdnr(rs.getInt("stdnr"));
                s.setFnavn(rs.getString("fnavn"));
                s.setEnavn(rs.getString("enavn"));
                s.setAdresse(rs.getString("adresse"));
                s.setPostnr(rs.getString("postnr"));
                s.setMobil(rs.getString("mobil"));
                s.setKlasse(rs.getString("klasse"));
                tabel.add(s);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tabel;
    }


    public void opretTabelfag() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS fag (\n"
                    + "	fagnr integer PRIMARY KEY,\n"
                    + "	fagnavn TEXT NOT NULL \n"
                    + ");";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void tilfojfeltstudfag() {
        try {
            String sql = "alter table studfag add kar integer";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void opretTabelStudFag() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS studfag (\n"
                    + "	stdnr integer,\n"
                    + "	fagnr integer, \n"
                    + "	primary key(stdnr,fagnr), \n"
                    + "	foreign key(stdnr) references studerende(stdnr), \n"
                    + "	foreign key(fagnr) references fag(fagnr) \n"
                    + ");";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void opretTabelStud() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS studerende (\n"
                    + "	stdnr integer PRIMARY KEY,\n"
                    + "	fnavn TEXT NOT NULL,\n"
                    + "	enavn TEXT NOT NULL,\n"
                    + "	adresse TEXT NOT NULL,\n"
                    + "	postnr TEXT NOT NULL,\n"
                    + "	mobil TEXT,\n"
                    + "	klasse TEXT \n"
                    + ");";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}



