package se.lexicon.jdbc.secondproject;

import java.sql.*;
import java.util.*;

public class ArtistManager {

    private String databaseName;
    private Connection con;
    private Statement st;

    public ArtistManager(String databaseName) throws SQLException {
        this.databaseName = databaseName;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + databaseName, "maliheh", "1359");

        } catch (Exception e) {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + databaseName + ";create=true", "maliheh", "1359");

            String tableQuery = "CREATE TABLE Artists( ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME VARCHAR(24) NOT NULL,LastName VARCHAR(26),Age INTEGER NOT NULL,UNIQUE (ID))";
            con.createStatement().execute(tableQuery);
        }

        st = con.createStatement();
    }

    public void addToDatabase(Artist artist) throws SQLException {

        String sql = "INSERT INTO ARTISTS1 (NAME,LASTNAME,AGE) values(?,?,?)";
        PreparedStatement prst = con.prepareStatement(sql);

        prst.setString(1, artist.getName());
        prst.setString(2, artist.getLastName());
        prst.setInt(3, artist.getAge());
        prst.execute();

    }

    public void deleteById(int id) throws SQLException {
        try {
            String sql = "DELETE FROM artists WHERE id = ?";
            PreparedStatement prst = con.prepareStatement(sql);

            prst.setInt(1, id);

            prst.execute();

            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public void deleteByName(String name) throws SQLException {
        try {

            String sql = "DELETE FROM ARTISTS1 WHERE name = ?";
            PreparedStatement prst = con.prepareStatement(sql);

            prst.setString(1, name);

            prst.execute();

            con.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public List<Artist> showAll() throws SQLException {

        ResultSet rs1 = st.executeQuery("SELECT * FROM ARTISTS1");

        List<Artist> allMember = new ArrayList<>();

        while (rs1.next()) {

            Artist art = new Artist(rs1.getInt("ID"), rs1.getString("NAME"), rs1.getString("LASTNAME"), rs1.getInt("AGE"));
            allMember.add(art);

        }

        return allMember;

    }

    public int getLastId() throws SQLException {
        int a = 0;
        ResultSet rs = st.executeQuery("SELECT * FROM artists WHERE id = ( SELECT MAX(id) FROM artists)");
        while (rs.next()) {
            a = rs.getInt("ID");
        }
        return a;
    }

    public Artist findById(int idNumber) throws SQLException {

        ResultSet rs = st.executeQuery("SELECT * FROM ARTISTS1 WHERE ID=" + idNumber);

        if (rs.next()) {
            String name = rs.getString("NAME");
            String famillyName = rs.getString("LASTNAME");
            int age = rs.getInt("AGE");

            Artist art = new Artist(idNumber, name, famillyName, age);
            return art;
        }
        return null;

    }

    public List<Artist> showName() throws SQLException {

        ResultSet rs1 = st.executeQuery("SELECT * FROM ARTISTS1  WHERE name = ?");

        List<Artist> allMember = new ArrayList<>();

        while (rs1.next()) {

            Artist art = new Artist(rs1.getInt("ID"), rs1.getString("NAME"), rs1.getString("LASTNAME"), rs1.getInt("AGE"));
            allMember.add(art);

        }

        return allMember;

    }

}
