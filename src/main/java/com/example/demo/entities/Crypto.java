package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Crypto {

    @SequenceGenerator(name = "crypto_sequence", sequenceName = "crypto_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crypto_sequence")
    private Long id;

    @Column
    private String symbol;

    @Column
    private Long price;

    @ManyToMany(mappedBy = "crypto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppUser> users;

}
