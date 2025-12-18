package arg.org.centro35.Panalera.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import arg.org.centro35.Panalera.connectors.Connector;
import arg.org.centro35.Panalera.entities.articulos;
import arg.org.centro35.Panalera.entities.proveedores;

public class ProveedoresRepository {
    private Connection conn;
    
    public ProveedoresRepository(){
        this.conn=Connector.getConeConnection();
    }
    public void save(proveedores proveedor) throws SQLException{
        if (proveedor==null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into proveedores (nombre, apellido, empresa, direccion) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            )) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setString(3, proveedor.getEmpresa());
            ps.setString(4, proveedor.getDireccion());
            
                  
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void remove(proveedores proveedor){
        if(proveedor==null) return;
        //try (PreparedStatement ps=conn.prepareStatement("delete from proveedores where id=?")){
        try (PreparedStatement ps=conn.prepareStatement("update proveedores set activo=salse where id=?")){
            ps.setInt(1, proveedor.getId());
            ps.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public List<proveedores>getAll(){
            List<proveedores>list=new ArrayList<>();
            try (ResultSet rs=conn.createStatement().executeQuery("select * from proveedores where activo=true")){
                while(rs.next()){
                    list.add(new proveedores(
                        rs.getInt("id"),
                        //id
                        rs.getString("nombre"),
                        //nombre
                        rs.getString("apellido"),
                        //apellido
                        rs.getString("empresa"),
                        //empresa
                        rs.getString("direccion")
                        //direccion
                    ));
                }
            } catch (Exception e){
                System.out.println(e);
            }
            return list;
    }
}
