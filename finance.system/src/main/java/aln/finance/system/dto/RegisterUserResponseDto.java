package aln.finance.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDto{
    private Long id;
    private String email;
    private LocalDateTime createdAt;
}


