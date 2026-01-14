package aln.finance.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @Email
    String email;
    @Column(name = "password", nullable = false, length = 255)
    String password;
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
}
