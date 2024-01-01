/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "order_mstr")
@NamedQueries({
    @NamedQuery(name = "OrderMstr.findAll", query = "SELECT o FROM OrderMstr o"),
    @NamedQuery(name = "OrderMstr.findByOid", query = "SELECT o FROM OrderMstr o WHERE o.oid = :oid"),
    @NamedQuery(name = "OrderMstr.findByOrderdate", query = "SELECT o FROM OrderMstr o WHERE o.orderdate = :orderdate"),
    @NamedQuery(name = "OrderMstr.findByTotalAmt", query = "SELECT o FROM OrderMstr o WHERE o.totalAmt = :totalAmt"),
    @NamedQuery(name = "OrderMstr.findByStatus", query = "SELECT o FROM OrderMstr o WHERE o.status = :status")})
public class OrderMstr implements Serializable {

    @Size(max = 50)
    @Column(name = "Status")
    private String status;
    @OneToMany(mappedBy = "oid")
    private Collection<PaymentMstr> paymentMstrCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Oid")
    private Integer oid;
    @Column(name = "Order_date")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Column(name = "Total_Amt")
    private Integer totalAmt;
    @OneToMany(mappedBy = "oid")
    private Collection<OrderDetailsMstr> orderDetailsMstrCollection;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;

    public OrderMstr() {
    }

    public OrderMstr(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Integer totalAmt) {
        this.totalAmt = totalAmt;
    }

    @JsonbTransient
    public Collection<OrderDetailsMstr> getOrderDetailsMstrCollection() {
        return orderDetailsMstrCollection;
    }

    public void setOrderDetailsMstrCollection(Collection<OrderDetailsMstr> orderDetailsMstrCollection) {
        this.orderDetailsMstrCollection = orderDetailsMstrCollection;
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
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderMstr)) {
            return false;
        }
        OrderMstr other = (OrderMstr) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderMstr[ oid=" + oid + " ]";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonbTransient
    public Collection<PaymentMstr> getPaymentMstrCollection() {
        return paymentMstrCollection;
    }

    public void setPaymentMstrCollection(Collection<PaymentMstr> paymentMstrCollection) {
        this.paymentMstrCollection = paymentMstrCollection;
    }
    
}
