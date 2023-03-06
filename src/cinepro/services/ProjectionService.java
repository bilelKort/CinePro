package cinepro.services;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.interfaces.ProjectionCRUD;
import cinepro.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectionService implements ProjectionCRUD<Projection> {
    @Override
    public void addProjection(Projection projection) {
        try {
            if (countFilms(projection.getId_film()) == 0) {
                FilmService filmService = new FilmService();
                Film film = filmService.getFilmById(projection.getId_film());
                Mail mail = new Mail();
                mail.envoyer(film.getNom(), film.getTrailer(), projection.getDate_debut());
            }

            String requete = "insert into projection (id_salle, id_film, date_debut, date_fin, nbr_places, diffuse) values (?, ?, str_to_date(?, '%Y-%m-%d %H:%i'), str_to_date(?, '%Y-%m-%d %H:%i'), ?, ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, projection.getId_salle());
            preparedStatement.setInt(2, projection.getId_film());
            preparedStatement.setString(3, projection.getDate_debut());
            preparedStatement.setString(4, projection.getDate_fin());
            preparedStatement.setInt(5, projection.getNbr_places());
            preparedStatement.setBoolean(6, projection.isDiffuse());
            preparedStatement.executeUpdate();
            System.out.println("projection ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List projectionList() {
        ArrayList<Projection> list = new ArrayList<Projection>();
        try {
            String requete = "select * from projection order by diffuse, date_debut";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Projection projection = new Projection();
                projection.setId_projection(resultSet.getInt(1));
                projection.setId_salle(resultSet.getInt(2));
                projection.setDate_debut(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setDate_fin(resultSet.getString(7));
                list.add(projection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List projectionListByFilm(int id_film) {
        ArrayList<Projection> list = new ArrayList<Projection>();
        try {
            String requete = "select * from projection where id_film='" + id_film + "' and date_debut > sysdate()";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Projection projection = new Projection();
                projection.setId_projection(resultSet.getInt(1));
                projection.setId_salle(resultSet.getInt(2));
                projection.setDate_debut(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setDate_fin(resultSet.getString(7));
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
                projection.setDate_debut(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setDate_fin(resultSet.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projection;
    }

    @Override
    public void updateProjection(Projection projection) {
        try {
            String requete = "update projection set id_salle=?, date_debut=str_to_date(?, '%Y-%m-%d %H:%i'), date_fin=str_to_date(?, '%Y-%m-%d %H:%i'), id_film=?, nbr_places=?, diffuse=? where id_projection=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, projection.getId_salle());
            preparedStatement.setString(2, projection.getDate_debut());
            preparedStatement.setString(3, projection.getDate_fin());
            preparedStatement.setInt(4, projection.getId_film());
            preparedStatement.setInt(5, projection.getNbr_places());
            preparedStatement.setBoolean(6, projection.isDiffuse());
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

    @Override
    public boolean checkDate(Projection projection) {
        List<Projection> list = projectionListBySalle(projection.getId_salle());
        for (int i=0; i<list.size(); i++) {
            if (projection.getId_projection() == list.get(i).getId_projection()) {
                list.remove(i);
            }
        }
        List<LocalDateTime> debut = new ArrayList<>();
        List<LocalDateTime> fin = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        list.stream().forEach(projection1 -> {
            debut.add(LocalDateTime.parse(projection1.getDate_debut(), dateTimeFormatter));
            fin.add(LocalDateTime.parse(projection1.getDate_fin(), dateTimeFormatter));
        });
        LocalDateTime this_debut = LocalDateTime.parse(projection.getDate_debut(), dateTimeFormatter1);
        LocalDateTime this_fin = LocalDateTime.parse(projection.getDate_fin(), dateTimeFormatter1);
        for (int i=0; i<list.size(); i++) {
            if (!((debut.get(i).compareTo(this_debut) < 0) && (fin.get(i).compareTo(this_debut) < 0) ||
                            (debut.get(i).compareTo(this_fin) > 0) && (fin.get(i).compareTo(this_fin) > 0))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List projectionListBySalle(int id_salle) {
        ArrayList<Projection> list = new ArrayList<Projection>();
        try {
            String requete = "select * from projection where id_salle = '" + id_salle + "'";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Projection projection = new Projection();
                projection.setId_projection(resultSet.getInt(1));
                projection.setId_salle(resultSet.getInt(2));
                projection.setDate_debut(resultSet.getString(3));
                projection.setId_film(resultSet.getInt(4));
                projection.setNbr_places(resultSet.getInt(5));
                projection.setDiffuse(resultSet.getBoolean(6));
                projection.setDate_fin(resultSet.getString(7));
                list.add(projection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Integer countFilms(int id_film) {
        Integer n = null;
        try {
            String requete = "select count(*) from projection where id_film = '" + id_film + "'";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                n = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n;
    }

    @Override
    public void updateDiffuse() {
        List<Projection> list = projectionList();
        for (Projection projection1 : list) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm:ss");
            LocalDateTime diffuse = LocalDateTime.parse(projection1.getDate_debut(), formatter);
            LocalDateTime now = LocalDateTime.now();
            if ((diffuse.compareTo(now) < 0) && (!projection1.isDiffuse())) {
                try {
                    String requete = "update projection set diffuse=true where id_projection=?";
                    PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
                    preparedStatement.setInt(1, projection1.getId_projection());
                    preparedStatement.executeUpdate();
                    System.out.println("projection modifié");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
