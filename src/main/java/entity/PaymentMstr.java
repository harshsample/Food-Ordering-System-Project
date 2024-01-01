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
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "payment_mstr")
@NamedQueries({
    @NamedQuery(name = "PaymentMstr.findAll", query = "SELECT p FROM PaymentMstr p"),
    @NamedQuery(name = "PaymentMstr.findByPid", query = "SELECT p FROM PaymentMstr p WHERE p.pid = :pid"),
    @NamedQuery(name = "PaymentMstr.findByDop", query = "SELECT p FROM PaymentMstr p WHERE p.dop = :dop"),
    @NamedQuery(name = "PaymentMstr.findByStatus", query = "SELECT p FROM PaymentMstr p WHERE p.status = :status"),
    @NamedQuery(name = "PaymentMstr.findByPType", query = "SELECT p FROM PaymentMstr p WHERE p.pType = :pType")})
public class PaymentMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Pid")
    private Integer pid;
    @Column(name = "DOP")
    @Temporal(TemporalType.DATE)
    private Date dop;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "pType")
    private String pType;
    @JoinColumn(name = "Oid", referencedColumnName = "Oid")
    @ManyToOne
    private OrderMstr oid;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;

    public PaymentMstr() {
    }

    public PaymentMstr(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPType() {
        return pType;
    }

    public void setPType(String pType) {
        this.pType = pType;
    }

    public OrderMstr getOid() {
        return oid;
    }

    public void setOid(OrderMstr oid) {
        this.oid = oid;
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
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMstr)) {
            return false;
        }
        PaymentMstr other = (PaymentMstr) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PaymentMstr[ pid=" + pid + " ]";
    }
    
}
