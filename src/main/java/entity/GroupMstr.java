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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "group_mstr")
@NamedQueries({
    @NamedQuery(name = "GroupMstr.findAll", query = "SELECT g FROM GroupMstr g"),
    @NamedQuery(name = "GroupMstr.findByGid", query = "SELECT g FROM GroupMstr g WHERE g.gid = :gid"),
    @NamedQuery(name = "GroupMstr.findByGName", query = "SELECT g FROM GroupMstr g WHERE g.gName = :gName")})
public class GroupMstr implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "GName")
    private String gName;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Gid")
    private Integer gid;
    @OneToMany(mappedBy = "gid")
    private Collection<RegisterMstr> registerMstrCollection;

    public GroupMstr() {
    }

    public GroupMstr(Integer gid) {
        this.gid = gid;
    }

    public GroupMstr(Integer gid, String gName) {
        this.gid = gid;
        this.gName = gName;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

   @JsonbTransient
    public Collection<RegisterMstr> getRegisterMstrCollection() {
        return registerMstrCollection;
    }

    public void setRegisterMstrCollection(Collection<RegisterMstr> registerMstrCollection) {
        this.registerMstrCollection = registerMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMstr)) {
            return false;
        }
        GroupMstr other = (GroupMstr) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupMstr[ gid=" + gid + " ]";
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }
    
}
