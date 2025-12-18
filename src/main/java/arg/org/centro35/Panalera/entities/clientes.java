package arg.org.centro35.Panalera.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class clientes {
private Integer id;
private String nombre;
private String apellido;
private String direccion;
}
