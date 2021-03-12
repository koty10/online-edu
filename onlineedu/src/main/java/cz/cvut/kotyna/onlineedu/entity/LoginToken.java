package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.MONTHS;

@Entity
@Table(name = "login_token")
@NamedQuery(name = "LoginToken.remove", query = "DELETE FROM LoginToken lt WHERE lt.tokenHash = :tokenHash")
public class LoginToken {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    private static final int HASH_LENGTH = 32;
    public static final int IP_ADDRESS_MAXLENGTH = 45;
    public static final int DESCRIPTION_MAXLENGTH = 255;

    @Column(length = HASH_LENGTH, nullable = false, unique = true)
    private @NotNull byte[] tokenHash;

    @Column(nullable = false)
    private @NotNull Instant created;

    @Column(nullable = false)
    private @NotNull Instant expiration;

    @Column(length = IP_ADDRESS_MAXLENGTH, nullable = false)
    private @NotNull @Size(max = IP_ADDRESS_MAXLENGTH) String ipAddress;

    @Column(length = DESCRIPTION_MAXLENGTH)
    private @Size(max = DESCRIPTION_MAXLENGTH) String description;

    @ManyToOne(optional = false)
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public byte[] getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(byte[] tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    public void setTimestamps() {
        created = Instant.now();

        if (expiration == null) {
            expiration = created.plus(1, MONTHS);
        }
    }

}