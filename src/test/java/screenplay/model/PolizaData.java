package screenplay.model;

public class PolizaData {

    private final String numeroPoliza;
    private final String asegurado;
    private final String vehiculo;
    private final String valorAsegurado;
    private final String vigenciaInicio;
    private final String vigenciaFin;

    private PolizaData(Builder builder) {
        this.numeroPoliza = builder.numeroPoliza;
        this.asegurado = builder.asegurado;
        this.vehiculo = builder.vehiculo;
        this.valorAsegurado = builder.valorAsegurado;
        this.vigenciaInicio = builder.vigenciaInicio;
        this.vigenciaFin = builder.vigenciaFin;
    }

    public String getNumeroPoliza() { return numeroPoliza; }
    public String getAsegurado() { return asegurado; }
    public String getVehiculo() { return vehiculo; }
    public String getValorAsegurado() { return valorAsegurado; }
    public String getVigenciaInicio() { return vigenciaInicio; }
    public String getVigenciaFin() { return vigenciaFin; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String numeroPoliza = "";
        private String asegurado = "";
        private String vehiculo = "";
        private String valorAsegurado = "";
        private String vigenciaInicio = "";
        private String vigenciaFin = "";

        public Builder numeroPoliza(String v) { this.numeroPoliza = v; return this; }
        public Builder asegurado(String v) { this.asegurado = v; return this; }
        public Builder vehiculo(String v) { this.vehiculo = v; return this; }
        public Builder valorAsegurado(String v) { this.valorAsegurado = v; return this; }
        public Builder vigenciaInicio(String v) { this.vigenciaInicio = v; return this; }
        public Builder vigenciaFin(String v) { this.vigenciaFin = v; return this; }
        public PolizaData build() { return new PolizaData(this); }
    }
}
