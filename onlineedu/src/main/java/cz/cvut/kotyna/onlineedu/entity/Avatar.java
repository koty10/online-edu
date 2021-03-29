package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "avatar")
@NamedQueries({
        @NamedQuery(name = Avatar.FIND_ALL, query = "SELECT a FROM Avatar a")
})
public class Avatar {

    public static final String FIND_ALL = "Avatar.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "blob", nullable = false)
    private byte[] blob;
    @Basic
    @Column(name = "file_extension", nullable = false, length = 64)
    private String fileExtension;
    @Basic
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    @Basic
    @Column(name = "price_per_month", nullable = false)
    private Integer pricePerMonth;
    @OneToMany(mappedBy = "avatar")
    private Collection<StudentsAvatar> studentsAvatars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(Integer pricePerMonth) {
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
