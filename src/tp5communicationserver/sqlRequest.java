/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5communicationserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baptman
 */
public class sqlRequest {

    static Connection conn;
    static String url = "jdbc:mysql://localhost/piecestheatre";
    
    public static void getListPlayFromDatabase(){
        connexionDatabase();
        getPlayName();
        disconnectDatabse();
    }

    public static void connexionDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/piecestheatre";
            conn = DriverManager.getConnection(url, "root", "");
            //insertReservation();
            //insertReservation();
            //insertPieceExample();
            //selectPiece();
            //countIdReservation();
            //System.out.println("Id de cepié: "+getIdTheaterPlay());
            //getPlayName();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void disconnectDatabse(){
        try{
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void insertReservation() {
        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO chegaray_abtout_reservation   "
                    + "VALUES (" + countIdReservation() + ", '" + TP5CommunicationServer.name + "', '" + TP5CommunicationServer.firstname + "', NOW(), " + TP5CommunicationServer.placeNumber + ", 1)");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void insertReservationExample() {
        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO chegaray_abtout_reservation   "
                    + "VALUES (3, 'abtout', 'Samy', NOW(), 1, 1)");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void insertPieceExample() {
        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO chegaray_abtout_piecetheatre   "
                    + "VALUES (1, 'Les fourberies de Scapin', '200')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void selectReservation() {
        System.out.println("\n[OUTPUT FROM SELECT]");
        String query = "SELECT * FROM chegaray_abtout_reservation";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("id");
                String n = rs.getString("nom");
                String n1 = rs.getString("prenom");
                String n2 = rs.getString("date");
                System.out.println(s + "   " + n + "   " + n1 + "   " + n2);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void selectPiece() {
        System.out.println("\n[OUTPUT FROM SELECT table piece]");
        String query = "SELECT * FROM chegaray_abtout_piecetheatre";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("id");
                String n = rs.getString("nom");
                String n1 = rs.getString("nombrePlace");
                System.out.println(s + "   " + n + "   " + n1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static int countIdReservation() {
        int nextIdNumber = 0;
        System.out.println("\n[OUTPUT FROM count]");
        String query = "SELECT COUNT(*) FROM chegaray_abtout_reservation";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            nextIdNumber = rs.getInt(1);
            nextIdNumber++;
            System.out.println(nextIdNumber);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nextIdNumber;
    }

    public static int getIdTheaterPlay() {
        int idTheaterPlay=0;
        System.out.println("\n[OUTPUT FROM SELECT theatre id]");
        String query = "SELECT id FROM chegaray_abtout_piecetheatre WHERE nom='" + TP5CommunicationServer.pieceName + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            idTheaterPlay= rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return idTheaterPlay;
    }
    
    public static void getPlayName(){
        List<String> listPlay = new ArrayList<String>();
        System.out.println("\n[OUTPUT FROM getPlayName]");
        String query = "SELECT nom FROM chegaray_abtout_piecetheatre";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString("nom"));
                //TP5CommunicationServer.listPlayFromDatabase.add(rs.getString("nom"));
                listPlay.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        TP5CommunicationServer.listPlayFromDatabase = listPlay;
        System.out.println("[ENDDD]");
        
       /*for(String elem: listPlay)
       {
       	 System.out.println (elem);
       }*/
        //return listPlay;
    }
}
