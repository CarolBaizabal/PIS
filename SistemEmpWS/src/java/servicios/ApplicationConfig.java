package servicios;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(servicios.CatalogoPrendaWS.class);
        resources.add(servicios.CatalogoWS.class);
        resources.add(servicios.CategoriaPrendaWS.class);
        resources.add(servicios.CategoriaWS.class);
        resources.add(servicios.ClienteWS.class);
        resources.add(servicios.ComercializacionWS.class);
        resources.add(servicios.ContratoWS.class);
        resources.add(servicios.EgresoWS.class);
        resources.add(servicios.EmpWS.class);
        resources.add(servicios.IngresoWS.class);
        resources.add(servicios.LoginWS.class);
        resources.add(servicios.PrendaWS.class);
        resources.add(servicios.RolWS.class);
        resources.add(servicios.UsuarioWS.class);
        resources.add(servicios.VentasRematesWS.class);

    }
    
}
