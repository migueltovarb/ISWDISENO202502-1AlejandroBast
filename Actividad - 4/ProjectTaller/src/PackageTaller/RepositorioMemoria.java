package PackageTaller;

import java.util.HashMap;
import java.util.Map;

public class RepositorioMemoria {
    private final Map<String, Propietario> propietarios = new HashMap<>();
    private final Map<String, Vehiculo> vehiculos = new HashMap<>();

    public boolean existePlaca(String placa) {
        return vehiculos.containsKey(placa);
    }

    public Propietario obtenerPropietario(String propietarioId) {
        return propietarios.get(propietarioId);
    }

    public Vehiculo obtenerVehiculo(String placa) {
        return vehiculos.get(placa);
    }

    public void guardarPropietario(Propietario p) {
        propietarios.put(p.getId(), p);
    }

    public void guardarVehiculo(Vehiculo v) {
        vehiculos.put(v.getPlaca(), v);
    }

    public Map<String, Vehiculo> vehiculos() { return vehiculos; }
}
