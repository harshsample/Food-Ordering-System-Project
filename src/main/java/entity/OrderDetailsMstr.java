/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
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

/**
 *
 * @author HP
 */
@Entity
@Table(name = "order_details_mstr")
@NamedQueries({
    @NamedQuery(name = "OrderDetailsMstr.findAll", query = "SELECT o FROM OrderDetailsMstr o"),
    @NamedQuery(name = "OrderDetailsMstr.findByODid", query = "SELECT o FROM OrderDetailsMstr o WHERE o.oDid = :oDid"),
    @NamedQuery(name = "OrderDetailsMstr.findByPrice", query = "SELECT o FROM OrderDetailsMstr o WHERE o.price = :price"),
    @NamedQuery(name = "OrderDetailsMstr.findByQty", query = "SELECT o FROM OrderDetailsMstr o WHERE o.qty = :qty"),
    @NamedQuery(name = "OrderDetailsMstr.findByTotalAmt", query = "SELECT o FROM OrderDetailsMstr o WHERE o.totalAmt = :totalAmt")})
public class OrderDetailsMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ODid")
    private Integer oDid;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Qty")
    private Integer qty;
    @Column(name = "Total_Amt")
    private Integer totalAmt;
    @JoinColumn(name = "Mid", referencedColumnName = "Mid")
    @ManyToOne
    private MenuMstr mid;
    @JoinColumn(name = "Oid", referencedColumnName = "Oid")
    @ManyToOne
    private OrderMstr oid;

    public OrderDetailsMstr() {
    }

    public OrderDetailsMstr(Integer oDid) {
        this.oDid = oDid;
    }

    public Integer getODid() {
        return oDid;
    }

    public void setODid(Integer oDid) {
        this.oDid = oDid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Integer totalAmt) {
        this.totalAmt = totalAmt;
    }

    public MenuMstr getMid() {
        return mid;
    }

    public void setMid(MenuMstr mid) {
        this.mid = mid;
    }

    public OrderMstr getOid() {
        return oid;
    }

    public void setOid(OrderMstr oid) {
        this.oid = oid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oDid != null ? oDid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetailsMstr)) {
            return false;
        }
        OrderDetailsMstr other = (OrderDetailsMstr) object;
        if ((this.oDid == null && other.oDid != null) || (this.oDid != null && !this.oDid.equals(other.oDid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderDetailsMstr[ oDid=" + oDid + " ]";
    }
    
}
