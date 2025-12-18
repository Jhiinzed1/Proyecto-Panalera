package arg.org.centro35.Panalera.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class articulos {
    public articulos(int int1, String string, String string2, String string3, String string4, int int2, int int3) {
        //TODO Auto-generated constructor stub
    }
    private Integer id;
    private String Tipo;
    private String marca;
    private Integer peso;
    private String calidad;
    private int precio;
    private Integer proveedor_id;
    private boolean stock;
}
