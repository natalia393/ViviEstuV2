package pe.edu.upc.viviestu.dto;

public class RegistroPublicoDTO {
    private String username;
    private String password;
    private String correo;
    private String universidad;
    private Double presupuestoMensual;
    private String medioTransporte;
    private String cicloEstudio;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Double getPresupuestoMensual() {
        return presupuestoMensual;
    }

    public void setPresupuestoMensual(Double presupuestoMensual) {
        this.presupuestoMensual = presupuestoMensual;
    }

    public String getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(String medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public String getCicloEstudio() {
        return cicloEstudio;
    }

    public void setCicloEstudio(String cicloEstudio) {
        this.cicloEstudio = cicloEstudio;
    }
}
