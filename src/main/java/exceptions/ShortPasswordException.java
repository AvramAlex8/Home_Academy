package exceptions;

public class ShortPasswordException extends Exception{
    public ShortPasswordException()
    {
        super("Password must contain at least 8 characters!");
    }
}
