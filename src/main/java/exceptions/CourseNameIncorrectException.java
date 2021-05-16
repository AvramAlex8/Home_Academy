package exceptions;

public class CourseNameIncorrectException extends Exception{
    public CourseNameIncorrectException()
    {
        super("Course name incorrect!");
    }
}
