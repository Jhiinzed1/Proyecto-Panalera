package arg.org.centro35.Panalera.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arg.org.centro35.Panalera.entities.articulos;
import arg.org.centro35.Panalera.entities.proveedores;
import arg.org.centro35.Panalera.repositories.ArticulosRepository;
import arg.org.centro35.Panalera.repositories.ProveedoresRepository;

@Controller
public class ProveedorController {
    private String mensaje="Ingrese un nuevo proveedor";
    private ProveedoresRepository pr=new ProveedoresRepository();

    @GetMapping("/proveedores")
    public String getArticulos(Model model, @RequestParam(name="buscar", defaultValue = "", required = false) String buscar){
        proveedores proveedor=new proveedores();
        proveedor.setNombre("Jhoson");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("proveedor", pr.getAll());
        model.addAttribute("Proveedor", pr.getAll().stream().filter(a->a.getEmpresa().toLowerCase().contains(buscar)).toList());
        model.addAttribute("proveedor", proveedor);
        return "proveedores.html";
    }
    @PostMapping("/saveProveedor")
    public String saveProveedor(@ModelAttribute proveedores proveedor) throws SQLException{
        if(proveedor.getNombre().length()>3 && proveedor.getApellido().length()>3 && proveedor.getDireccion().length()>3 && proveedor.getEmpresa().length()>3){
            pr.save(proveedor);
            if(proveedor.getId()>0){
                mensaje="se guardo el proveedor id: "+proveedor.getId();
            }else{
                    mensaje="no se guardo el proveedor!";
                }
            }
            return "redirect:proveedores";
        }
}
