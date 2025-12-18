package arg.org.centro35.Panalera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import arg.org.centro35.Panalera.connectors.Connector;
import arg.org.centro35.Panalera.entities.articulos;
import arg.org.centro35.Panalera.entities.ventas;

public class VentasRepository {
    private Connection conn;
    
    public VentasRepository(){
        this.conn=Connector.getConeConnection();
    }
    public void save(ventas ventas) throws SQLException{
        if (ventas==null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into ventas (cliente_id, articulo_id, cantidad, fecha, total) values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            )) {
            ps.setInt(1, ventas.getCliente_id());
            ps.setInt(2, ventas.getArticulo_id());
            ps.setInt(3, ventas.getCantidad());
            ps.setString(4, ventas.getFecha());
            ps.setInt(5, ventas.getTotal());
            
                  
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void remove(ventas ventas){
        if(ventas==null) return;
        //try (PreparedStatement ps=conn.prepareStatement("delete from ventas where id=?")){
        try (PreparedStatement ps=conn.prepareStatement("update ventas set activo=salse where id=?")){
            ps.setInt(1, ventas.getId());
            ps.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public List<ventas>getAll(){
            List<ventas>list=new ArrayList<>();
            try (ResultSet rs=conn.createStatement().executeQuery("select * from ventas where activo=true")){
                while(rs.next()){
                    list.add(new ventas(
                        rs.getInt("id"),
                        //id
                        rs.getInt("Cliente_id"),
                        //Cliente_id
                        rs.getInt("Articulo_id"),
                        //Articulo_id
                        rs.getInt("cantidad"),
                        //cantidad
                        rs.getString("fecha"),
                        //fecha
                        rs.getInt("total")
                        //total
                    ));
                }
            } catch (Exception e){
                System.out.println(e);
            }
            return list;
    }
}
