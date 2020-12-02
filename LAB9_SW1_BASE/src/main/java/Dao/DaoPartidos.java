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

                    Estadios e = new Estadios();
                    e.setNombre(rs.getString(5));
                    sn.setEstadio(e);
                    p.setSeleccionLocal(sn);

                    Arbitros ar = new Arbitros();
                    ar.setNombre(rs.getString(6));
                    p.setArbitro(ar);

                    partidos.add(p);
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
    public ArrayList<SeleccionesNacionales> listaSelecciones(){
        ArrayList<SeleccionesNacionales> selecciones = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) {

            String sql = "select nombre, idSeleccionesNacionales from seleccionesnacionales;";
            try(ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    SeleccionesNacionales sn = new SeleccionesNacionales();
                    sn.setNombre(rs.getString(1));
                    sn.setIdSeleccionesNacionales(rs.getInt(2));

                    selecciones.add(sn);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return selecciones;
    }

    public ArrayList<Arbitros> listasArbitros(){
        ArrayList<Arbitros> arbitros = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) {

            String sql = "select nombre, idArbitros from arbitros;";
            try(ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Arbitros ar = new Arbitros();
                    ar.setNombre(rs.getString(1));
                    ar.setIdArbitros(rs.getInt(2));

                    arbitros.add(ar);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arbitros;
    }


}
