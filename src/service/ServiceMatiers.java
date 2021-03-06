
package com.Olympians.Service;

import java.sql.SQLException;
import java.util.List;
import com.Olympians.Entite.Matiers;
import com.Olympians.IService.IService;
import database.connect;




import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Bsm
 */


public class ServiceMatiers implements IService<Matiers> {

    private Connection con;
    private Statement ste;

    public ServiceMatiers() {
        con = connect.getInstance().getConnection();
    }
    
    @Override   
    public void ajouter(Matiers a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `matiere` (`id`,`nom`, `coefficient`, `cheffmodule`) VALUES (?, ?, ?, ?);");
        PS.setInt(1,a.getId());
        PS.setString(2, a.getNom());
        PS.setDouble(3, a.getCoefficient());
        PS.setString(4, a.getChef_module());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `matiere` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Matiers a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `matiere` SET `nom`=?,`coefficient`=? ,`cheffmodule`=? WHERE `id`=?");
        PS.setString(1,a.getNom());
        PS.setDouble(2, a.getCoefficient());
        PS.setString(3,a.getChef_module());
        PS.setInt(4,id);
        PS.executeUpdate();
    }
    
    

    @Override
    public List<Matiers> readAll() throws SQLException {
        List<Matiers> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from matiere");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            int coeffition = rs.getInt(3);
            String chefmodule = rs.getString(4);
            Matiers a = new Matiers(id, nom,coeffition , chefmodule );
            AL.add(a);
        }
        return AL;
    }
    
    
    
      
    public List<Matiers> Search(String Text) throws SQLException {
       List<Matiers> AL = new ArrayList<>();
        ste = con.createStatement();
        String Sqlrequt = "select * from matiere where nom LIKE '%" + Text + "%' or cheffmodule LIKE '%" + Text + "%'";
        ResultSet rs = ste.executeQuery(Sqlrequt);
        
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            int coeffition = rs.getInt(3);
            String chefmodule = rs.getString(4);
            Matiers a = new Matiers(id, nom,coeffition , chefmodule );
            AL.add(a);
        }
        return AL;
    }
    
}
