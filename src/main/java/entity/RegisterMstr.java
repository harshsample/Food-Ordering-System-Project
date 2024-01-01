/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "register_mstr")
@NamedQueries({
    @NamedQuery(name = "RegisterMstr.findAll", query = "SELECT r FROM RegisterMstr r"),
    @NamedQuery(name = "RegisterMstr.findByEmail", query = "SELECT r FROM RegisterMstr r WHERE r.email = :email"),
    @NamedQuery(name = "RegisterMstr.findByName", query = "SELECT r FROM RegisterMstr r WHERE r.name = :name"),
    @NamedQuery(name = "RegisterMstr.findByPassword", query = "SELECT r FROM RegisterMstr r WHERE r.password = :password"),
    @NamedQuery(name = "RegisterMstr.findByPhone", query = "SELECT r FROM RegisterMstr r WHERE r.phone = :phone")})
public class RegisterMstr implements Serializable {

    @Size(max = 50)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Password")
    private String password;
    @OneToMany(mappedBy = "email")
    private Collection<PaymentMstr> paymentMstrCollection;
    @OneToMany(mappedBy = "email")
    private Collection<OrderMstr> orderMstrCollection;

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private Integer phone;
    @OneToMany(mappedBy = "email")
    private Collection<AddressMstr> addressMstrCollection;
    @JoinColumn(name = "Gid", referencedColumnName = "Gid")
    @ManyToOne
//    @JsonbTransient
    private GroupMstr gid; // Property causing the issue
    @OneToMany(mappedBy = "email")
    private Collection<RestaurantMstr> restaurantMstrCollection;
    @OneToMany(mappedBy = "email")
    private Collection<FeedbackMstr> feedbackMstrCollection;
    @OneToMany(mappedBy = "email")
    private Collection<CartMstr> cartMstrCollection;

    public RegisterMstr() {
    }

    public RegisterMstr(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    @JsonbTransient
    public Collection<AddressMstr> getAddressMstrCollection() {
        return addressMstrCollection;
    }

    public void setAddressMstrCollection(Collection<AddressMstr> addressMstrCollection) {
        this.addressMstrCollection = addressMstrCollection;
    }

    public GroupMstr getGid() {
        return gid;
    }

    public void setGid(GroupMstr gid) {
        this.gid = gid;
    }
    @JsonbTransient
    public Collection<RestaurantMstr> getRestaurantMstrCollection() {
        return restaurantMstrCollection;
    }

    public void setRestaurantMstrCollection(Collection<RestaurantMstr> restaurantMstrCollection) {
        this.restaurantMstrCollection = restaurantMstrCollection;
    }
    @JsonbTransient
    public Collection<FeedbackMstr> getFeedbackMstrCollection() {
        return feedbackMstrCollection;
    }

    public void setFeedbackMstrCollection(Collection<FeedbackMstr> feedbackMstrCollection) {
        this.feedbackMstrCollection = feedbackMstrCollection;
    }
    @JsonbTransient
    public Collection<CartMstr> getCartMstrCollection() {
        return cartMstrCollection;
    }

    public void setCartMstrCollection(Collection<CartMstr> cartMstrCollection) {
        this.cartMstrCollection = cartMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisterMstr)) {
            return false;
        }
        RegisterMstr other = (RegisterMstr) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegisterMstr[ email=" + email + " ]";
    }

    @JsonbTransient
    public Collection<OrderMstr> getOrderMstrCollection() {
        return orderMstrCollection;
    }

    public void setOrderMstrCollection(Collection<OrderMstr> orderMstrCollection) {
        this.orderMstrCollection = orderMstrCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonbTransient
    public Collection<PaymentMstr> getPaymentMstrCollection() {
        return paymentMstrCollection;
    }

    public void setPaymentMstrCollection(Collection<PaymentMstr> paymentMstrCollection) {
        this.paymentMstrCollection = paymentMstrCollection;
    }
    
}
