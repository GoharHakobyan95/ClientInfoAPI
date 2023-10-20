package ru.unibell.clientinfoapi.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.unibell.clientinfoapi.exception.ContactValidationException;
import ru.unibell.clientinfoapi.exception.Error;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "contacts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contact_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long contactId;

    @ManyToOne
    @JoinColumn(name = "client_reference_id", referencedColumnName = "clientId", nullable = false)
    private Client client;

    private String value;

    public abstract String getContactType();


    // Factory method to create a Contact object based on the specified contactType.
    public static Contact createContact(ContactType contactType) {
        if ("PHONE".equals(contactType.name())) {
            return new PhoneContact();
        } else if ("EMAIL".equals(contactType.name())) {
            return new EmailContact();
        } else {
            throw new ContactValidationException(Error.CONTACT_TYPE_ERR);
        }
    }

}
