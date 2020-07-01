package exeption;

public class WrongIdException extends RuntimeException {
    private static final long serialVersionUID = 5162710183389028792L;
    private String id;

    public WrongIdException(){
        super();
    }

    public WrongIdException(String id){
        super("ID '"+id+"' is already exists");
        this.id = id;
    }
}
