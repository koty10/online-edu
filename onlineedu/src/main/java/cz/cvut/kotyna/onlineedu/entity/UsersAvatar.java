package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "users_avatar", schema = "public", catalog = "onlineedu")
public class UsersAvatar {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "time_to", nullable = false)
    private LocalDateTime timeTo;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "user_account", referencedColumnName = "id", nullable = false)
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "avatar", referencedColumnName = "id", nullable = false)
    private Avatar avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public String getTimeToFormatted() {
        if (timeTo == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy HH:mm");
        return timeTo.format(formatter);
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAvatar that = (UsersAvatar) o;
        return active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(timeTo, that.timeTo) &&
                Objects.equals(userAccount, that.userAccount) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeTo, active, userAccount, avatar);
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
