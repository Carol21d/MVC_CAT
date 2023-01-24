package api.models;

public class Message {
    // declaracion de la clase Message  luego accederemos a el y le daremos valores que no arrojara mensajes con status
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}