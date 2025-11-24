package PackageTaller;

public enum TipoServicio {
    CAMBIO_ACEITE("cambio de aceite"),
    SISTEMA_FRENOS("sistema de frenos"),
    REVISION_GENERAL("revisión general"),
    OTRO("otro");

    private final String etiqueta;

    TipoServicio(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
