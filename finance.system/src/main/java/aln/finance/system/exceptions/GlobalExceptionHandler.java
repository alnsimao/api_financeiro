package aln.finance.system.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler{
    public ProblemDetail handleUserNotFound(UserNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("User not Found");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }


}
