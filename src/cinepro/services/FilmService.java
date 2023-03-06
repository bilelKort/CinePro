package cinepro.services;

import cinepro.entities.Crew;
import cinepro.entities.Film;
import cinepro.interfaces.FilmCRUD;
import cinepro.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilmService implements FilmCRUD<Film> {
    @Override
    public void addFilm(Film film) throws SQLException {
        try {
            String requete = "insert into film (nom, categorie, description, duree, poster, trailer, releaseDate, id_imdb) values (?, ?, ?, ?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, film.getNom());
            preparedStatement.setString(2, film.getCategorie());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setInt(4, film.getDuree());
            preparedStatement.setString(5, film.getPoster());
            preparedStatement.setString(6, film.getTrailer());
            preparedStatement.setString(7, film.getReleaseDate());
            preparedStatement.setString(8, film.getId_imdb());
            preparedStatement.executeUpdate();
            System.out.println("film ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public List filmList() {
        ArrayList<Film> list = new ArrayList<Film>();
        try {
            String requete = "select * from film";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Film film = new Film();
                film.setId_film(resultSet.getInt(1));
                film.setNom(resultSet.getString(2));
                film.setCategorie(resultSet.getString(3));
                film.setDescription(resultSet.getString(4));
                film.setDuree(resultSet.getInt(5));
                film.setPoster(resultSet.getString(6));
                film.setTrailer(resultSet.getString(7));
                film.setReleaseDate(resultSet.getString(8));
                film.setId_imdb(resultSet.getString(9));
                list.add(film);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List filmList(String name, String date) {
        ArrayList<Film> list = new ArrayList<Film>();
        try {
            String requete = "select distinct(f.id_film), f.nom, f.poster from film f left join projection p on f.id_film = p.id_film where f.nom like ?";
            if (!date.isEmpty()) {
                requete = requete + " and date(p.date_debut) = str_to_date(?, '%d/%m/%Y')";
            }
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, "%"+name+"%");
            if (!date.isEmpty()) {
                preparedStatement.setString(2, date);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId_film(resultSet.getInt(1));
                film.setNom(resultSet.getString(2));
                film.setPoster(resultSet.getString(3));
                list.add(film);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Film getFilmById(int id_film) {
        Film film = new Film();
        try {
            String requete = "select * from film where id_film=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, id_film);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                film.setId_film(resultSet.getInt(1));
                film.setNom(resultSet.getString(2));
                film.setCategorie(resultSet.getString(3));
                film.setDescription(resultSet.getString(4));
                film.setDuree(resultSet.getInt(5));
                film.setPoster(resultSet.getString(6));
                film.setTrailer(resultSet.getString(7));
                film.setReleaseDate(resultSet.getString(8));
                film.setId_imdb(resultSet.getString(9));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return film;
    }

    @Override
    public Film getFilm(String id_imdb) {
        Film film = new Film();
        try {
            String requete = "select * from film where id_imdb='" + id_imdb + "'";
            Statement statement = MyConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                film.setId_film(resultSet.getInt(1));
                film.setNom(resultSet.getString(2));
                film.setCategorie(resultSet.getString(3));
                film.setDescription(resultSet.getString(4));
                film.setDuree(resultSet.getInt(5));
                film.setPoster(resultSet.getString(6));
                film.setTrailer(resultSet.getString(7));
                film.setReleaseDate(resultSet.getString(8));
                film.setId_imdb(resultSet.getString(9));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return film;
    }

    @Override
    public void updateFilm(Film film) {
        try {
            String requete = "update film set nom=?, categorie=?, description=?, duree=?, poster=?, trailer=?, releaseDate=str_to_date(?, '%Y-%m-%d') where id_film=? and id_imdb=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, film.getNom());
            preparedStatement.setString(2, film.getCategorie());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setInt(4, film.getDuree());
            preparedStatement.setString(5, film.getPoster());
            preparedStatement.setString(6, film.getTrailer());
            preparedStatement.setString(7, film.getReleaseDate());
            preparedStatement.setInt(8, film.getId_film());
            preparedStatement.setString(9, film.getId_imdb());
            preparedStatement.executeUpdate();
            System.out.println("film modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteFilm(Film film) {
        try {
            String requete = "delete from film where id_film=? and id_imdb=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setInt(1, film.getId_film());
            preparedStatement.setString(2, film.getId_imdb());
            preparedStatement.executeUpdate();
            System.out.println("film supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
