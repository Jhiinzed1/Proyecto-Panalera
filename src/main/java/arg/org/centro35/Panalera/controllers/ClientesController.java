package arg.org.centro35.Panalera.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arg.org.centro35.Panalera.entities.clientes;
import arg.org.centro35.Panalera.entities.proveedores;
import arg.org.centro35.Panalera.repositories.ClientesRepository;
import arg.org.centro35.Panalera.repositories.ProveedoresRepository;

@Controller
public class ClientesController {
    private String mensaje="Ingrese un nuevo cliente";
    private ClientesRepository cl=new ClientesRepository();

    @GetMapping("/clientes")
    public String getClientes(Model model, @RequestParam(name="buscar", defaultValue = "", required = false) String buscar){
        clientes cliente=new clientes();
        cliente.setNombre("Joaquin");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("cliente", cl.getAll());
        model.addAttribute("cliente", cl.getAll().stream().filter(a->a.getNombre().toLowerCase().contains(buscar)).toList());
        model.addAttribute("cliente", cliente);
        return "clientes.html";
    }

     @PostMapping("/saveCliente")
    public String saveCliente(@ModelAttribute clientes cliente) throws SQLException{
        if(cliente.getNombre().length()>3 && cliente.getApellido().length()>3 && cliente.getDireccion().length()>3){
            cl.save(cliente);
            if(cliente.getId()>0){
                mensaje="se guardo el cliente id: "+cliente.getId();
            }else{
                    mensaje="no se guardo el cliente!";
                }
            }
            return "redirect:cliente";
        }
}
