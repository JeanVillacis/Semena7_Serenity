package screenplay.model;

public class AseguradoData {

    private final String nombre;
    private final String apellido;
    private final String identificacion;
    private final String correo;
    private final String telefono;
    private final String direccion;

    private AseguradoData(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.identificacion = builder.identificacion;
        this.correo = builder.correo;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getIdentificacion() { return identificacion; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String nombre = "";
        private String apellido = "";
        private String identificacion = "";
        private String correo = "";
        private String telefono = "";
        private String direccion = "";

        public Builder nombre(String nombre) { this.nombre = nombre; return this; }
        public Builder apellido(String apellido) { this.apellido = apellido; return this; }
        public Builder identificacion(String identificacion) { this.identificacion = identificacion; return this; }
        public Builder correo(String correo) { this.correo = correo; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder direccion(String direccion) { this.direccion = direccion; return this; }
        public AseguradoData build() { return new AseguradoData(this); }
    }
}
