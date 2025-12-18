package arg.org.centro35.Panalera.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arg.org.centro35.Panalera.connectors.*;
import arg.org.centro35.Panalera.entities.articulos;
import arg.org.centro35.Panalera.entities.proveedores;
import arg.org.centro35.Panalera.repositories.ArticulosRepository;
import arg.org.centro35.Panalera.repositories.ProveedoresRepository;


@Controller
public class ArticulosController {
    private String mensaje="Ingrese un nuevo articulo";
    private ArticulosRepository ar=new ArticulosRepository();
    private ProveedoresRepository pr=new ProveedoresRepository();

    @GetMapping("/articulos")
    public String getArticulos(Model model, @RequestParam(name="buscar", defaultValue = "", required = false) String buscar){
        articulos articulo=new articulos();
        articulo.setMarca("Pampers");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("proveedor", pr.getAll());
        model.addAttribute("articulos", ar.getAll().stream().filter(a->a.getCalidad().toLowerCase().contains(buscar)).toList());
        model.addAttribute("articulo", articulo);
        return "articulos.html";

    }
    @PostMapping("/saveArticulo")
    public String saveArticulo(@ModelAttribute articulos articulo) throws SQLException{
        if(articulo.getMarca().length()>3 && articulo.getTipo().length()>3 && articulo.getCalidad().length()>3 && articulo.getPeso()>0 && articulo.getPrecio()>2){
            ar.save(articulo);
            if(articulo.getId()>0){
                mensaje="se guardo el articulo id: "+articulo.getId();
            }else{
                    mensaje="no se guardo el articulo!";
                }
            }
            return "redirect:articulos";
        }
    }

