package Dao;

import Bean.Arbitros;
import Bean.Estadios;
import Bean.Partidos;
import Bean.SeleccionesNacionales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase{

    public ArrayList<Partidos> listaDePartidos(){

        ArrayList<Partidos> partidos = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) {

            String sql = "select p.numeroJornada, p.fecha,sn.nombre,sn2.nombre, e.nombre,ar.nombre from partidos p \n" +
                    "left join seleccionesnacionales sn on p.seleccionLocal = sn.idSeleccionesNacionales \n" +
                    "left join seleccionesnacionales sn2 on p.seleccionVisitante = sn2.idSeleccionesNacionales\n" +
                    "left join estadios e on sn.estadios_idEstadios = e.idEstadios\n" +
                    "left join arbitros ar on p.arbitro = ar.idArbitros;";
            try(ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Partidos p = new Partidos();
                    p.setNumeroJornada(rs.getInt(1));
                    p.setFecha(rs.getString(2));

                    SeleccionesNacionales sn = new SeleccionesNacionales();
                    sn.setNombre(rs.getString(3));
                    p.setSeleccionLocal(sn);

                    SeleccionesNacionales sn2 = new SeleccionesNacionales();
                    sn2.setNombre(rs.getString(4));
                    p.setSeleccionVisitante(sn2);

                    sn.setEstadio((Estadios) rs.getObject(5));

                    Arbitros ar = new Arbitros();
                    ar.setNombre(rs.getString(6));
                    p.setArbitro(ar);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return partidos;
    }

    public void crearPartido(Partidos partidos){

        /*
                Inserte su código aquí
                 */
    }


}
