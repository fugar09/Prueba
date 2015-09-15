/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author B38603
 */
public class ConexionBase {
 
    int conexion_correcta = 0;
    Connection base = null;

    public ConexionBase(){
       try {
           Class.forName("org.postgresql.Driver");
           String url = "jdbc:postgresql://basedatos:5432/b38603";
            base = DriverManager.getConnection(url, "b38603","b38603");
           conexion_correcta = 1;
       } catch (ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
           conexion_correcta = -1;
       }
    }
    public int getConexionCorrecta(){
        return conexion_correcta;
    }
    public int cerrarConexion(){
        try {
            base.close();
            return 1;
        } catch (SQLException ex) {
            return -1;
        }
    }
    
    public String [][] getDatosConsulta(String sql){
        try {
            PreparedStatement sentencia = base.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado != null){
                return getMatrizResultado(resultado);
            }else{
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String[][] getMatrizResultado(ResultSet resultado){
        List lista = new ArrayList();
        try {
            ResultSetMetaData metadata = resultado.getMetaData();
            int columnas = metadata.getColumnCount();
            while (resultado.next()) {
                String[] datos = new String[columnas];
                for(int x =0;x<columnas;x++){
                    datos[x] = resultado.getString(x+1);
                }
            lista.add(datos);
        }
            resultado.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return aMatriz(lista);
 }
 
    private String[][] aMatriz(List lista) {
        if (lista.size() > 0) {
            String[][] matriz = new String[lista.size()][];
            for (int i = 0; i < lista.size(); i++) {
                matriz[i] = (String[]) lista.get(i);
            }
            return matriz;
        } else {
            return null;
        }
    }


    
}
