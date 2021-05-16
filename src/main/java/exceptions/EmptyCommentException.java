package exceptions;

public class EmptyCommentException extends Exception{
    public EmptyCommentException()
    {
        super("A comment can not be empty!");
    }
}
