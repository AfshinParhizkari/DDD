package com.afshin.person.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version 2
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: RootAggregate=RootEntity
*/
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity//RootAggregate=RootEntity
public class Customer {
    private Integer customerpk;
    private Integer customertypeid;
    private Integer typedetailid;
    private String nationalkey;
    private Integer booknumber;
    private String bookserial;
    private String bookserie;
    private String passportno;
    private String lastname;
    private String firstname;
    private Integer countryid;
    private Integer cityid;
    private Date birthdate;
    private Date changerdate;
    private List<Contact> contactsByCustomerpk;

    @Id
    @Column(name = "customerpk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getCustomerpk() {
        return customerpk;
    }
    public void setCustomerpk(Integer customerpk) {
        this.customerpk = customerpk;
    }

    @Basic
    @Column(name = "customertypeid")
    @NotNull(message = "نوع مشتری نمی تواند خالی باشد")
    @Max(value = 999,message = "نوع مشتری بین ۱ تا 3 رقم می باشد.") // must be an integer value lower than or equal to the number in the value element.
    public Integer getCustomertypeid() {
        return customertypeid;
    }
    public void setCustomertypeid(Integer customertypeid) {
        this.customertypeid = customertypeid;
    }

    @Basic
    @Column(name = "typedetailid")
    @Max(value = 999,message = "انواع مشتری بین ۱ تا 3 رقم می باشد.") // must be an integer value lower than or equal to the number in the value element.
    public Integer getTypedetailid() {
        return typedetailid;
    }
    public void setTypedetailid(Integer typedetailid) {
        this.typedetailid = typedetailid;
    }

    @Basic
    @Column(name = "nationalkey")
    @NotEmpty(message = "مشتری: کد یا شناسه ملی نمی تواند خالی باشد")
    @Pattern(regexp = "\\d{11}|\\d{10}|^$",message = "مشتری: کدملی 10 یا شناسه ملی 11 رقم است")
    public String getNationalkey() {
        return nationalkey;
    }
    public void setNationalkey(String nationalkey) {
        this.nationalkey = nationalkey;
    }

    @Basic
    @Column(name = "booknumber")
    public Integer getBooknumber() {
        return booknumber;
    }
    public void setBooknumber(Integer booknumber) {
        this.booknumber = booknumber;
    }

    @Basic
    @Column(name = "bookserial")
    public String getBookserial() {
        return bookserial;
    }
    public void setBookserial(String bookserial) {
        this.bookserial = bookserial;
    }

    @Basic
    @Column(name = "bookserie")
    public String getBookserie() {
        return bookserie;
    }
    public void setBookserie(String bookserie) {
        this.bookserie = bookserie;
    }

    @Basic
    @Column(name = "passportno")
    public String getPassportno() {
        return passportno;
    }
    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    @Basic
    @Column(name = "lastname")
    @NotEmpty(message = "نام شرکت/نام خانوادگی نمی تواند خالی باشد.")
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "countryid")
    @Max(value = 999,message = "کد کشور بین ۱ تا 3 رقم می باشد.") // must be an integer value lower than or equal to the number in the value element.
    public Integer getCountryid() {
        return countryid;
    }
    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    @Basic
    @Column(name = "cityid")
    @Max(value = 999,message = "کد شهر بین ۱ تا 3 رقم می باشد.") // must be an integer value lower than or equal to the number in the value element.
    public Integer getCityid() {
        return cityid;
    }
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    @Basic
    @Column(name = "birthdate")
    @Past(message = "تاریخ نمی تواند برای امروز یا آینده باشد.")
    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "changerdate")
    @UpdateTimestamp //automatically updates the changerdate last modified timestamp.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getChangerdate() {
        return changerdate;
    }
    public void setChangerdate(Date changerdate) {
        this.changerdate = changerdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        if (customerpk != null ? !customerpk.equals(customer.customerpk) : customer.customerpk != null) return false;
        if (customertypeid != null ? !customertypeid.equals(customer.customertypeid) : customer.customertypeid != null)
            return false;
        if (typedetailid != null ? !typedetailid.equals(customer.typedetailid) : customer.typedetailid != null)
            return false;
        if (nationalkey != null ? !nationalkey.equals(customer.nationalkey) : customer.nationalkey != null)
            return false;
        if (booknumber != null ? !booknumber.equals(customer.booknumber) : customer.booknumber != null) return false;
        if (bookserial != null ? !bookserial.equals(customer.bookserial) : customer.bookserial != null) return false;
        if (bookserie != null ? !bookserie.equals(customer.bookserie) : customer.bookserie != null) return false;
        if (passportno != null ? !passportno.equals(customer.passportno) : customer.passportno != null) return false;
        if (lastname != null ? !lastname.equals(customer.lastname) : customer.lastname != null) return false;
        if (firstname != null ? !firstname.equals(customer.firstname) : customer.firstname != null) return false;
        if (countryid != null ? !countryid.equals(customer.countryid) : customer.countryid != null) return false;
        if (cityid != null ? !cityid.equals(customer.cityid) : customer.cityid != null) return false;
        if (birthdate != null ? !birthdate.equals(customer.birthdate) : customer.birthdate != null) return false;
        if (changerdate != null ? !changerdate.equals(customer.changerdate) : customer.changerdate != null)
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result =(customerpk != null ? customerpk.hashCode() : 0);
        result = 31 * result + (customertypeid != null ? customertypeid.hashCode() : 0);
        result = 31 * result + (typedetailid != null ? typedetailid.hashCode() : 0);
        result = 31 * result + (nationalkey != null ? nationalkey.hashCode() : 0);
        result = 31 * result + (booknumber != null ? booknumber.hashCode() : 0);
        result = 31 * result + (bookserial != null ? bookserial.hashCode() : 0);
        result = 31 * result + (bookserie != null ? bookserie.hashCode() : 0);
        result = 31 * result + (passportno != null ? passportno.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (countryid != null ? countryid.hashCode() : 0);
        result = 31 * result + (cityid != null ? cityid.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (changerdate != null ? changerdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "customerByCustomerfk")
    public List<Contact> getContactsByCustomerpk() {
        return contactsByCustomerpk;
    }
    public void setContactsByCustomerpk(List<Contact> contactsByCustomerpk) {
        this.contactsByCustomerpk = contactsByCustomerpk;
    }
}
