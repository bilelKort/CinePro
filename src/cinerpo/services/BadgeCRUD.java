/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.services;

import cinerpo.entities.Badge;
import cinerpo.entities.TypeAbonnement;
import cinerpo.entities.Type_Badge;
import cinerpo.interfaces.EntityCRUD;
import cinerpo.utils.CineproConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Home
 */
public class BadgeCRUD implements EntityCRUD<Badge> {

    @Override
    public void addEntity(Badge t) {
        try {
            String requete = "INSERT INTO badge(type,nbr_reservation)VALUES(?,?)";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            switch (t.getType()) {
                case gold:
                    st.setString(1, "gold");
                    st.setInt(2, 100);
                    break;
                case silver:
                    st.setString(1, "silver");
                    st.setInt(2, 50);
                    break;
                case bronze:
                    st.setString(1, "bronze");
                    st.setInt(2, 10);
            }
            // st.setInt(2,t.getNbr_reservation());
            st.setInt(2, t.getNbr_reservation());
            st.executeUpdate();
            System.out.println("Badge ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int addEntity1(Badge t) {
        try {
            String requete = "INSERT INTO badge(type,nbr_reservation)VALUES(?,?)";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            switch (t.getType()) {
                case gold:
                    st.setString(1, "gold");
                    st.setInt(2, 100);
                    break;
                case silver:
                    st.setString(1, "silver");
                    st.setInt(2, 50);
                    break;
                case bronze:
                    st.setString(1, "bronze");
                    st.setInt(2, 10);
            }
            // st.setInt(2,t.getNbr_reservation());
            st.setInt(2, t.getNbr_reservation());
            int r=st.executeUpdate();
            if(r==1){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("ID de la nouvelle entité : " + id);
                    return id;
                }
            }
            System.out.println("Badge ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public String getBadge(int id_user) {
        String badge = "";
        String requete = "SELECT * FROM Badge b left join user u on u.id_badge=b.id_badge where u.id_user='" + id_user + "'";
        try {
            Statement st = CineproConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                badge = rs.getString(2);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return badge;
    }

    @Override
    public List entitiesList() {
        ArrayList<Badge> myList = new ArrayList();
        String requete = "SELECT * FROM Badge";
        try {
            Statement st = CineproConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Badge b = new Badge();
                b.setId_badge(rs.getInt(1));
                switch (rs.getString(2)) {
                    case "gold":
                        b.setType(Type_Badge.gold);
                        break;
                    case "silver":
                        b.setType(Type_Badge.silver);
                        break;
                    case "bronze":
                        b.setType(Type_Badge.bronze);
                        break;
                }
                b.setNbr_reservation(rs.getInt(3));
                //b.setId_user(rs.getInt(4));
                myList.add(b);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void updateNbr(int id_badge) {
        try {
            String requete = "UPDATE BADGE SET nbr_reservation=nbr_reservation+1 where id_badge=?";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, id_badge);
            st.executeUpdate();
            System.out.println("Badge Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEntity(int id, int idb) {

        try {
            String requete = "UPDATE BADGE SET type=?, nbr_reservation=? where id_badge=?";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            int nbr = test1(id);
            if (nbr > 10) {
                st.setString(1, "gold");
            } else if (nbr > 5) {
                st.setString(1, "silver");
            } else if (nbr > 0) {
                st.setString(1, "bronze");
            }
            st.setInt(2, test1(id));
            st.setInt(3, idb);
            st.executeUpdate();
            System.out.println("Badge Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    public void update2(int i) throws SQLException{
        String sql="UPDATE Badge INNER JOIN user ON Badge.id_badge = user.id_badge SET Badge.type = ? WHERE user.id_user =?";
        PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(sql);
        int nbr = test1(i);
            if (nbr > 10) {
                st.setString(1, "gold");
            } else if (nbr > 5) {
                st.setString(1, "silver");
            }
            st.setInt(2,i);
            st.executeUpdate();
            System.out.println("Badge Updated!");
    }
    public int test1(int i) throws SQLException {
        int nbres = 0;
        String sql = "SELECT u.id_user, u.nom, COUNT(r.id_reservation) AS nbres FROM user u LEFT JOIN reservation r ON u.id_user = r.id_user where u.id_user=? GROUP BY u.id_user, u.nom";

        PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(sql);
        st.setInt(1, i);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String name = rs.getString("u.nom");
            nbres = rs.getInt("nbres");
            int id = rs.getInt("u.id_user");
            System.out.println(name + " " + nbres);

        }
        return nbres;

    }
    @Override
    public void deleteEntity(int id) {
        try {
            String requete = "DELETE FROM BADGE WHERE id_user=?";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Badge deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public  XYChart.Series chart(){ 
        XYChart.Series<String, Integer> chartData= new XYChart.Series<String, Integer>();
        try {
            String sql="SELECT type, COUNT(*) AS count FROM Badge GROUP BY type";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                chartData.getData().add(new XYChart.Data<String, Integer>(rs.getString(1), rs.getInt(2)));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return chartData;
        
        
                
    }

   /* public int nbres(Badge b) {
        int rowcount = 0;
        try {
            String requete = "select count(*) from Reservation Join user on reservation.id_user=user.id_user Join Badge On user.id_badge=Badge.id_badge where Badge.id_badge=?";

            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, b.getId_badge());
            ResultSet rs = st.executeQuery();
            rs.next();
            rowcount = rs.getInt(1);
            System.out.println(rowcount);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }

        return rowcount;
    }

    public void nbres2(Badge b) {
        int x = nbres(b);
        b.setNbr_reservation(x);
    }

    public void affecterBadge(Badge b) {
        nbres2(b);
        if (b.getNbr_reservation() > 10) {
            b.setType(Type_Badge.bronze);
        } else if (b.getNbr_reservation() > 30) {
            b.setType(Type_Badge.silver);
        } else if (b.getNbr_reservation() > 50) {
            b.setType(Type_Badge.gold);
        }
        System.out.println(b.getType());

    }

    public void addEntit2(Badge t) {
        nbres2(t);
        try {
            String requete = "INSERT INTO badge(type,nbr_reservation)VALUES(?,?)";
            PreparedStatement st = CineproConnection.getInstance().getCnx().prepareStatement(requete);
            if (t.getNbr_reservation() > 10) {
                st.setString(1, "gold");
                st.setInt(2, t.getNbr_reservation());
            } else if (t.getNbr_reservation() > 7) {
                st.setString(1, "silver");
                st.setInt(2, t.getNbr_reservation());
            } else if (t.getNbr_reservation() > 4) {
                st.setString(1, "bronze");
                st.setInt(2, t.getNbr_reservation());
            }
            // st.setInt(2,t.getNbr_reservation());
            st.executeUpdate();
            System.out.println("Badge ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(t.getType());
    }*/

    

    @Override
    public void updateEntity(Badge t, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
