package arg.org.centro35.Panalera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import arg.org.centro35.Panalera.connectors.Connector;
import arg.org.centro35.Panalera.entities.articulos;

public class ArticulosRepository {
    private Connection conn;
    
    public ArticulosRepository(){
        this.conn=Connector.getConeConnection();
    }
    public void save(articulos articulo) throws SQLException{
        if (articulo==null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into articulos (tipo, marca, peso, calidad, precio, proveedor_id) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            )) {
            ps.setString(1, articulo.getTipo());
            ps.setString(2, articulo.getMarca());
            ps.setInt(3, articulo.getPeso());
            ps.setString(4, articulo.getCalidad());
            ps.setInt(5, articulo.getPrecio());
            ps.setInt(6, articulo.getProveedor_id());
                  
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void remove(articulos articulo){
        if(articulo==null) return;
        //try (PreparedStatement ps=conn.prepareStatement("delete from articulos where id=?")){
        try (PreparedStatement ps=conn.prepareStatement("update articulos set activo=salse where id=?")){
            ps.setInt(1, articulo.getId());
            ps.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public List<articulos>getAll(){
            List<articulos>list=new ArrayList<>();
            try (ResultSet rs=conn.createStatement().executeQuery("select * from articulos where activo=true")){
                while(rs.next()){
                    list.add(new articulos(
                        rs.getInt("id"),
                        //id
                        rs.getString("tipo"),
                        //tipo
                        rs.getString("marca"),
                        //marca
                        rs.getString("peso"),//peso
                        rs.getString("calidad"),//calidad
                        rs.getInt("precio"),//precio
                        rs.getInt("proveedor_id")
                    ));
                }
            } catch (Exception e){
                System.out.println(e);
            }
            return list;
    }
}
