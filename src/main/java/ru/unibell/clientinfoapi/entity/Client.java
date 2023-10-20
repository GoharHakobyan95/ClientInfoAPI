package ru.unibell.clientinfoapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long clientId;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.JOIN)
    private List<EmailContact> emailContacts = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.JOIN)
    private List<PhoneContact> phoneContacts = new ArrayList<>();


}
