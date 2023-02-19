package cinepro.services;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.interfaces.ProjectionCRUD;
import cinepro.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectionService implements ProjectionCRUD<Projection> {
    @Override
    public void addProjection(Projection projection) {
        try {
            String requete = "insert into projection (id_salle, id_film, date, nbr_places, diffuse, type) values (?, ?, str_to_date(?, '%d/%m/%Y-%H:%i'), ?, ?, ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, projection.getId_salle());
            preparedStatement.setInt(2, projection.getId_film());
            preparedStatement.setString(3, projection.getDate());
            preparedStatement.setInt(4, projection.getNbr_places());
            preparedStatement.setBoolean(5, projection.isDiffuse());
            preparedStatement.setString(6, projection.getType());
            preparedStatement.executeUpdate();
            System.out.println("projection ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List ProjectionList() {
        ArrayList<Projection> list = new ArrayList<Projection>();
        try {
            String requete = "select * from projection";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Projection projection = new Projection();
                projection.setId_projection(resultSet.getInt(1));
                projection.setId_salle(resultSet.getInt(2));
                projection.setDate(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setType(resultSet.getString(7));
                list.add(projection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Projection getProjection(int id_projection) {
        Projection projection = new Projection();
        try {
            String requete = "select * from projection where id_projection='" + id_projection + "'";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                projection.setId_projection(resultSet.getInt(1));
                projection.setId_salle(resultSet.getInt(2));
                projection.setDate(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setType(resultSet.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projection;
    }

    @Override
    public void updateProjection(Projection projection) {
        try {
            String requete = "update projection set id_salle=?, date=str_to_date(?, '%d/%m/%Y'), id_film=?, nbr_places=?, diffuse=?, type=? where id_projection=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, projection.getId_salle());
            preparedStatement.setString(2, projection.getDate());
            preparedStatement.setInt(3, projection.getId_film());
            preparedStatement.setInt(4, projection.getNbr_places());
            preparedStatement.setBoolean(5, projection.isDiffuse());
            preparedStatement.setString(6, projection.getType());
            preparedStatement.setInt(7, projection.getId_projection());
            preparedStatement.executeUpdate();
            System.out.println("projection modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteProjection(Projection projection) {
        try {
            String requete = "delete from projection where id_projection=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, projection.getId_projection());
            preparedStatement.executeUpdate();
            System.out.println("projection supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
