package Main;

public class FileInvalidException extends Exception{

    //default error message
    public FileInvalidException(){
        super("Error: Input file cannot be parsed due to missing information.");
    }

    //passing custom message
    public FileInvalidException(String msg){
        super(msg);
    }
}
