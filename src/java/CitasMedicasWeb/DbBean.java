/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitasMedicasWeb;

/**
 *
 * @author ACER
 */
import CitasMedicasDB.DB;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name ="dbBean")
@RequestScoped
public class DbBean {
    private String status = "Sin probar"; //PARCIALMENTE PARA PROBAR
    private List<UsuarioRow> usuarios = new ArrayList<>();

    @PostConstruct
    public void init() {
        String sql = "SELECT TOP 5 UsuarioID, Username, NombreCompleto FROM Usuario ORDER BY UsuarioID";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new UsuarioRow(
                        rs.getInt("UsuarioID"),
                        rs.getString("Username"),
                        rs.getString("NombreCompleto")));
            }
            status = "Conexión OK (" + usuarios.size() + " filas)";
        } catch (SQLException e) {
            status = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }
    }

    public String getStatus() { return status; }
    public List<UsuarioRow> getUsuarios() { return usuarios; }

    public static class UsuarioRow {
        public final int id; public final String user; public final String nombre;
        public UsuarioRow(int id, String user, String nombre) {
            this.id = id; this.user = user; this.nombre = nombre;
        }
        public int getId() { return id; }
        public String getUser() { return user; }
        public String getNombre() { return nombre; }
    }
}