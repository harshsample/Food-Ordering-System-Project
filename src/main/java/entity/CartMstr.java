/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "cart_mstr")
@NamedQueries({
    @NamedQuery(name = "CartMstr.findAll", query = "SELECT c FROM CartMstr c"),
    @NamedQuery(name = "CartMstr.findByCid", query = "SELECT c FROM CartMstr c WHERE c.cid = :cid"),
    @NamedQuery(name = "CartMstr.findByQty", query = "SELECT c FROM CartMstr c WHERE c.qty = :qty"),
    @NamedQuery(name = "CartMstr.findByPrice", query = "SELECT c FROM CartMstr c WHERE c.price = :price"),
    @NamedQuery(name = "CartMstr.findByAddedat", query = "SELECT c FROM CartMstr c WHERE c.addedat = :addedat")})
public class CartMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cid")
    private Integer cid;
    @Column(name = "Qty")
    private Integer qty;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Added_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedat;
    @JoinColumn(name = "Mid", referencedColumnName = "Mid")
    @ManyToOne
    private MenuMstr mid;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;

    public CartMstr() {
    }

    public CartMstr(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getAddedat() {
        return addedat;
    }

    public void setAddedat(Date addedat) {
        this.addedat = addedat;
    }

    public MenuMstr getMid() {
        return mid;
    }

    public void setMid(MenuMstr mid) {
        this.mid = mid;
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
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartMstr)) {
            return false;
        }
        CartMstr other = (CartMstr) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CartMstr[ cid=" + cid + " ]";
    }
    
}
