/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitasMedicasDB;

/**
 *
 * @author ACER
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB {
    // Ajustar estos valores dependiendo de quien lo este usando
    private static final String HOST = "localhost";
    private static final String PORT = "1433"; //ESTO CAMBIA SEGUN LA PC
    private static final String DB = "CitasMedicasDB";
    private static final String USER = "sa"; //ESTO CAMBIA
    private static final String PASS = "root"; //ESTO CAMBIA

    //URL recomendado para local asi evitar problemas con el ssl (Secure Sockets Layer)
    //SSL: protocolo de seguridad que cifra el tráfico de datos entre un navegador y un sitio web
    private static final String URL =
        "jdbc:sqlserver://" + HOST + ":" + PORT + ";" +
        "databaseName=" + DB + ";" +
        "encrypt=false;trustServerCertificate=true";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Falta el driver mssql-jdbc en Libraries", e);
        }
    }

    private DB() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}