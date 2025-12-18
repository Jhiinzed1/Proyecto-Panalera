package arg.org.centro35.Panalera.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ventas {
private Integer id;
private Integer cliente_id;
private Integer articulo_id;
private int cantidad;
private String fecha;
private Integer total;
}
