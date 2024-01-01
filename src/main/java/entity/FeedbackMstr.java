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
@Table(name = "feedback_mstr")
@NamedQueries({
    @NamedQuery(name = "FeedbackMstr.findAll", query = "SELECT f FROM FeedbackMstr f"),
    @NamedQuery(name = "FeedbackMstr.findByFid", query = "SELECT f FROM FeedbackMstr f WHERE f.fid = :fid"),
    @NamedQuery(name = "FeedbackMstr.findByDescription", query = "SELECT f FROM FeedbackMstr f WHERE f.description = :description"),
    @NamedQuery(name = "FeedbackMstr.findByDate", query = "SELECT f FROM FeedbackMstr f WHERE f.date = :date")})
public class FeedbackMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Fid")
    private Integer fid;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;

    public FeedbackMstr() {
    }

    public FeedbackMstr(Integer fid) {
        this.fid = fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackMstr)) {
            return false;
        }
        FeedbackMstr other = (FeedbackMstr) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FeedbackMstr[ fid=" + fid + " ]";
    }
    
}
