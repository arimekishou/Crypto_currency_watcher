package com.example.demo.entities;

import lombok.*;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.util.List;

@ComponentScan("com.example.demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class AppUser {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence")
    private Long id;

    @Column
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "crypto_id")
    @ToString.Exclude
    private List<Crypto> crypto;

}
