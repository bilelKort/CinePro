package cinepro.services;

import cinepro.entities.Crew;
import cinepro.interfaces.CrewCRUD;
import cinepro.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrewService implements CrewCRUD<Crew> {
    @Override
    public void addCrew(Crew crew) {
        try {
            String requete = "insert into crew (nom, photo, job, id_film) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, crew.getNom());
            preparedStatement.setString(2, crew.getPhoto());
            preparedStatement.setString(3, crew.getJob());
            preparedStatement.setInt(4, crew.getId_film());
            preparedStatement.executeUpdate();
            System.out.println("crew ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List crewList() {
        ArrayList<Crew> list = new ArrayList<Crew>();
        try {
            String requete = "select * from crew";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Crew crew = new Crew();
                crew.setId_crew(resultSet.getInt(1));
                crew.setNom(resultSet.getString(2));
                crew.setPhoto(resultSet.getString(3));
                crew.setJob(resultSet.getString(4));
                crew.setId_film(resultSet.getInt(5));
                list.add(crew);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateCrew(Crew crew) {
        try {
            String requete = "update crew set nom=?, photo=?, job=?, id_film=? where id_crew=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, crew.getNom());
            preparedStatement.setString(2, crew.getPhoto());
            preparedStatement.setString(3, crew.getJob());
            preparedStatement.setInt(4, crew.getId_film());
            preparedStatement.setInt(5, crew.getId_crew());
            preparedStatement.executeUpdate();
            System.out.println("crew modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCrew(Crew crew) {
        try {
            String requete = "delete from crew where id_crew=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, crew.getId_crew());
            preparedStatement.executeUpdate();
            System.out.println("crew supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
