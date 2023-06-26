package modelo.pojos;

public class Respuesta {

    private String mensaje;
    private Exception error;
    private boolean errorRespuesta;

    public boolean isErrorRespuesta() {
        return errorRespuesta;
    }

    public void setErrorRespuesta(boolean errorRespuesta) {
        this.errorRespuesta = errorRespuesta;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Respuesta() {
    }

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
        this.error = null;
        this.errorRespuesta= false;
    }

    public Respuesta(String mensaje, Exception error) {
        this.mensaje = mensaje;
        this.error = error;
        this.errorRespuesta= false;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
