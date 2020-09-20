package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "public", catalog = "onlineedu")
public class MessageModel {
    private int id;
    private int chatId;
    private int userAccountId;
    private String role;
    private String text;
    private Timestamp time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chat_id", nullable = false)
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Basic
    @Column(name = "user_account_id", nullable = false)
    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 64)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 1028)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageModel that = (MessageModel) o;

        if (id != that.id) return false;
        if (chatId != that.chatId) return false;
        if (userAccountId != that.userAccountId) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + chatId;
        result = 31 * result + userAccountId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
