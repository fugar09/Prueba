/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import baseDatos.ConexionBase;

/**
 *
 * @author B38603
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConexionBase base = new ConexionBase();
        if(base.getConexionCorrecta() != -1){
            String sql = "select * from cursos";
            String [][] resultado = base.getDatosConsulta(sql);
            if(resultado != null){
                for(int x = 0;x< resultado.length;x++){
                    for(int y = 0;y< resultado[0].length;y++){
                        System.out.print(resultado[x][y]);
                    }
                System.out.println("");
            }
        }else{
            System.err.println("Ha ocurrido un error al procesar la consulta o la consulta no ha traído resultados");
        }
        }else{
            System.err.println("No se ha logrado establecer conexión con la base de datos");
        }
    }
    
}
