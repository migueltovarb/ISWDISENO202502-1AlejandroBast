package PackageTaller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Vehiculo {
    private final String placa;     
    private final String marca;
    private final String modelo;
    private final Propietario propietario;
    private final List<Servicio> servicios = new ArrayList<>();

    public Vehiculo(String placa, String marca, String modelo, Propietario propietario) {
        if (placa == null || placa.trim().isEmpty())
            throw new IllegalArgumentException("La placa es obligatoria.");
        if (propietario == null)
            throw new IllegalArgumentException("El propietario es obligatorio.");

        this.placa = placa.trim().toUpperCase(Locale.ROOT);
        this.marca = (marca == null) ? "" : marca.trim();
        this.modelo = (modelo == null) ? "" : modelo.trim();
        this.propietario = propietario;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Propietario getPropietario() { return propietario; }

    public void agregarServicio(Servicio servicio) {
        if (servicio == null) throw new IllegalArgumentException("Servicio no puede ser null.");
        if (servicio.getCosto() <= 0) throw new IllegalArgumentException("El costo del servicio debe ser mayor a 0.");
        servicios.add(servicio);
    }

    public List<Servicio> historialServicios() {
        return Collections.unmodifiableList(servicios);
    }

    public double totalGastado() {
        return servicios.stream().mapToDouble(Servicio::getCosto).sum();
    }
}
