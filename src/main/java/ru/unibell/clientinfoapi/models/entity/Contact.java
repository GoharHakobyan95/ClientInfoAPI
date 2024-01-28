package ru.unibell.clientinfoapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "client_ref_id", referencedColumnName = "clientId", nullable = false)
    @JsonIgnore
    private Client client;

    private String value;

    public abstract String getContactType();


    // Factory method to create a Contact object based on the specified contactType.
    public static Contact createContact(ContactType contactType) {
        if (ContactType.PHONE.equals(contactType)) {
            return new PhoneContact();
        } else if (ContactType.EMAIL.equals(contactType)) {
            return new EmailContact();
        } else {
            throw new ContactValidationException(Error.CONTACT_TYPE_ERR);
        }
    }

}
