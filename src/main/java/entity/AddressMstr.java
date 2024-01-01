/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "address_mstr")
@NamedQueries({
    @NamedQuery(name = "AddressMstr.findAll", query = "SELECT a FROM AddressMstr a"),
    @NamedQuery(name = "AddressMstr.findByAid", query = "SELECT a FROM AddressMstr a WHERE a.aid = :aid"),
    @NamedQuery(name = "AddressMstr.findByState", query = "SELECT a FROM AddressMstr a WHERE a.state = :state"),
    @NamedQuery(name = "AddressMstr.findByCity", query = "SELECT a FROM AddressMstr a WHERE a.city = :city"),
    @NamedQuery(name = "AddressMstr.findByCountry", query = "SELECT a FROM AddressMstr a WHERE a.country = :country")})
public class AddressMstr implements Serializable {

    @Size(max = 255)
    @Column(name = "State")
    private String state;
    @Size(max = 50)
    @Column(name = "City")
    private String city;
    @Size(max = 50)
    @Column(name = "Country")
    private String country;
//    @OneToMany(mappedBy = "aid")
//    private Collection<OrderMstr> orderMstrCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Aid")
    private Integer aid;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;

    public AddressMstr() {
    }

    public AddressMstr(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }


    public RegisterMstr getEmail() {
        return email;
    }

    public void setEmail(RegisterMstr email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AddressMstr)) {
            return false;
        }
        AddressMstr other = (AddressMstr) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AddressMstr[ aid=" + aid + " ]";
    }


//    public Collection<OrderMstr> getOrderMstrCollection() {
//        return orderMstrCollection;
//    }
//
//    public void setOrderMstrCollection(Collection<OrderMstr> orderMstrCollection) {
//        this.orderMstrCollection = orderMstrCollection;
//    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
