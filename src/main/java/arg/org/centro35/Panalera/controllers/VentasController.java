package arg.org.centro35.Panalera.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arg.org.centro35.Panalera.entities.proveedores;
import arg.org.centro35.Panalera.entities.ventas;
import arg.org.centro35.Panalera.repositories.ProveedoresRepository;
import arg.org.centro35.Panalera.repositories.VentasRepository;

@Controller
public class VentasController {
    private String mensaje="Ingrese una nueva venta";
    private VentasRepository v=new VentasRepository();

    @GetMapping("/Ventas")
    public String getVentas(Model model, @RequestParam(name="buscar", defaultValue = "", required = false) String buscar){
        ventas ventas=new ventas();
        ventas.setFecha("12/05/2025");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("venta", v.getAll());
        model.addAttribute("venta", v.getAll().stream().filter(a->a.getFecha().toLowerCase().contains(buscar)).toList());
        model.addAttribute("ventas", ventas);
        return "ventas.html";
    }

        @PostMapping("/saveVentas")
    public String saveVentas(@ModelAttribute ventas venta) throws SQLException{
        if(venta.getFecha().length()>1 && venta.getCantidad()>1 && venta.getArticulo_id()>0 && venta.getCliente_id()>0 && venta.getTotal()>0){
            v.save(venta);
            if(venta.getId()>0){
                mensaje="se guardo la venta id: "+venta.getId();
            }else{
                    mensaje="no se guardo la venta!";
                }
            }
            return "redirect:ventas";
        }
}
