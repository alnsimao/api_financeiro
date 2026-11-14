package aln.finance.system.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex){
       return createProblem(
               HttpStatus.NOT_FOUND,
               "User Not Found",
               ex.getMessage()
        );
    }



    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException ex){
        return createProblem(
                HttpStatus.CONFLICT,
                "Email Already Exists",
                ex.getMessage()
        );
    }



    private ProblemDetail createProblem(
            HttpStatus status,
            String title,
            String detail) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                detail
        );

        problem.setTitle(title);
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }


}
