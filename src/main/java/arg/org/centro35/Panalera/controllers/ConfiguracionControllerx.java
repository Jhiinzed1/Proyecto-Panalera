package arg.org.centro35.Panalera.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import arg.org.centro35.Panalera.connectors.Connector;

@Controller
public class ConfiguracionControllerx {
    @GetMapping("/configuracion")
    public String getConfiguracion(Model model) {
        model.addAttribute("so", System.getProperty("os.name")+ " "+System.getProperty("os.version")+" "+System.getProperty("os.arch"));
        model.addAttribute("java", System.getProperty("java.vm.name")+" "+System.getProperty("java.vm.version")+" "+System.getProperty("java.version.date"));
        model.addAttribute("user", System.getProperty("user.name"));
        model.addAttribute("db", Connector.getUrl());
        return "configuracion.html";
    }
}
