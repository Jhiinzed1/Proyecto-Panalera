package arg.org.centro35.Panalera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import arg.org.centro35.Panalera.connectors.Connector;
import arg.org.centro35.Panalera.entities.articulos;
import arg.org.centro35.Panalera.entities.clientes;

public class ClientesRepository {
    private Connection conn;
    
    public ClientesRepository(){
        this.conn=Connector.getConeConnection();
    }
    public void save(clientes cliente) throws SQLException{
        if (cliente==null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into clientes (nombre, apellido, direccion) values(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            )) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            
                  
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void remove(clientes cliente){
        if(cliente==null) return;
        //try (PreparedStatement ps=conn.prepareStatement("delete from clientes where id=?")){
        try (PreparedStatement ps=conn.prepareStatement("update clientes set activo=salse where id=?")){
            ps.setInt(1, cliente.getId());
            ps.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public List<clientes>getAll(){
            List<clientes>list=new ArrayList<>();
            try (ResultSet rs=conn.createStatement().executeQuery("select * from cliente where activo=true")){
                while(rs.next()){
                    list.add(new clientes(
                        rs.getInt("id"),
                        //id
                        rs.getString("nombre"),
                        //nombre
                        rs.getString("apellido"),
                        //apellido
                        rs.getString("direccion")//direccion
                        
                    ));
                }
            } catch (Exception e){
                System.out.println(e);
            }
            return list;
    }
}
