package se.lexicon.jdbc.secondproject;

import java.sql.*;
import static se.lexicon.jdbc.secondproject.Utilities.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println(""
                + "1: Create a new database " + "\n"
                + "2: Add a new artist to database¨" + "\n"
                + "3: Delete from database" + "\n"
                + "4: Show all" + "\n"
                + "5: FindById" + "\n");

        int choice = nextInt("please make a choice");
        switch (choice) {
            case 1:
                ArtistManager am2 = new ArtistManager(nextLine("Enter name of new database"));
                break;
            case 2:
                ArtistManager am = new ArtistManager("artist");
                int newId = findLastId();
                Artist newartist = new Artist(++newId, nextLine("Insert name"), nextLine("Insert last name"), nextInt("Age: "));
                am.addToDatabase(newartist);
                System.out.println(" ");
                System.out.println("you add a new  person in artists1 database");
                break;
            case 3:
                System.out.println("1: delete by Id");
                System.out.println("2: delete by Name");

                int choice1 = nextInt("Please make a choice");
                switch (choice1) {
                    case 1:
                        ArtistManager am3 = new ArtistManager("artist");
                        am3.deleteById(nextInt("Choose an id to delete"));

                        break;
                    case 2:
                        ArtistManager am4 = new ArtistManager("artist");
                        am4.deleteByName(nextLine("choose a name to delete"));
                        break;
                }
                break;
            case 4:
                ArtistManager am5 = new ArtistManager("artist");
                List<Artist> result = am5.showAll();
                for (Artist a : result) {

                    System.out.println(a);
                }
                break;
            case 5:
                ArtistManager am7 = new ArtistManager("artist");
                Artist a = am7.findById(nextInt("Enter an id : "));
                System.out.println(a);
                ;

                break;

        }

    }

    public static int findLastId() throws SQLException {
        ArtistManager am1 = new ArtistManager("artist");
        return am1.getLastId();

    }
}
