package Main;

public class OpenFileException extends Exception {

    public OpenFileException(){
        super("Could not open file for input Latex for reading");
    }

}
