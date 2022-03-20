package com.afpa.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","COURSDB","COURSDB");
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");

            Statement statement = conn.createStatement();
            Statement statement2 = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM JOUEUR WHERE SEXE='H'");

            ResultSet rs2 = statement2.executeQuery("SELECT * FROM JOUEUR WHERE SEXE='F'");

            while (rs.next()){
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                Long id = rs.getLong("ID");

                System.out.println("Le joueur numero " + id + " est " + nom + " " + prenom + ".");
            }

            while (rs2.next()){
                String nom = rs2.getString("NOM");
                String prenom = rs2.getString("PRENOM");
                Long id = rs2.getLong("ID");

                System.out.println("La joueuse numero " + id + " est " + nom + " " + prenom + ".");
            }


            Long identifiant = 30L;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM JOUEUR WHERE ID=?");


            preparedStatement.setLong(1, identifiant);

            ResultSet rs3 = preparedStatement.executeQuery();

            while (rs3.next()){
                String nom = rs3.getString("NOM");
                String prenom = rs3.getString("PRENOM");
                Long id = rs3.getLong("ID");

                System.out.println("Vous avez pris le numero : " + id + " c'est " + nom + " " + prenom + ".");
            }


            PreparedStatement preparedStatement1 = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");
            Long identifiant2 = 12L;
            String nom = "BECKER";
            String prenom = "Boris";

            Long identifiant3 = 24L;
            String nom2 = "Errani";
            String prenom2 = "Sara";



            preparedStatement1.setString(1, nom);
            preparedStatement1.setString(2, prenom);
            preparedStatement1.setLong(3, identifiant2);

            preparedStatement1.executeUpdate();
            int nbOfPrepared = preparedStatement1.executeUpdate();
            System.out.println("Vous avez modifié le numero : " + identifiant2 + " c'est devenu " + nom + " " + prenom + ".");
            System.out.println("c'est votre modification numero : " + nbOfPrepared);
            preparedStatement1.clearParameters();

            preparedStatement1.setString(1, nom2);
            preparedStatement1.setString(2, prenom2);
            preparedStatement1.setLong(3, identifiant3);

            preparedStatement1.executeUpdate();
            nbOfPrepared++;

            System.out.println("Vous avez modifié le numero : " + identifiant3 + " c'est devenu " + nom2 + " " + prenom2 + ".");
            System.out.println("c'est votre modification numero : " + nbOfPrepared);
            System.out.println(nbOfPrepared);



            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
