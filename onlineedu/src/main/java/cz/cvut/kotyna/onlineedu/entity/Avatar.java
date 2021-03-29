package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "avatar")
public class Avatar {
    private int id;
    private byte[] blob;
    private String fileExtension;
    private String name;
    private int pricePerMonth;
    @OneToMany(mappedBy = "avatar")
    private Collection<StudentsAvatar> studentsAvatars;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "blob", nullable = false)
    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    @Basic
    @Column(name = "file_extension", nullable = false, length = 64)
    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price_per_month", nullable = false)
    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return id == avatar.id &&
                pricePerMonth == avatar.pricePerMonth &&
                Arrays.equals(blob, avatar.blob) &&
                Objects.equals(fileExtension, avatar.fileExtension) &&
                Objects.equals(name, avatar.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileExtension, name, pricePerMonth);
        result = 31 * result + Arrays.hashCode(blob);
        return result;
    }

    public Collection<StudentsAvatar> getStudentsAvatars() {
        return studentsAvatars;
    }

    public void setStudentsAvatars(Collection<StudentsAvatar> studentsAvatars) {
        this.studentsAvatars = studentsAvatars;
    }
}
