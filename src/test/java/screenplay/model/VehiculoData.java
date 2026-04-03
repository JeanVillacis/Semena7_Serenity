package screenplay.model;

public class VehiculoData {

    private final String marca;
    private final String modelo;
    private final String anio;
    private final String placa;

    private VehiculoData(Builder builder) {
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.anio = builder.anio;
        this.placa = builder.placa;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getAnio() { return anio; }
    public String getPlaca() { return placa; }

    public String getDescripcion() {
        return marca + " " + modelo;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String marca = "";
        private String modelo = "";
        private String anio = "";
        private String placa = "";

        public Builder marca(String marca) { this.marca = marca; return this; }
        public Builder modelo(String modelo) { this.modelo = modelo; return this; }
        public Builder anio(String anio) { this.anio = anio; return this; }
        public Builder placa(String placa) { this.placa = placa; return this; }
        public VehiculoData build() { return new VehiculoData(this); }
    }
}
