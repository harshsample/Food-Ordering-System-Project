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
@Table(name = "restaurant_mstr")
@NamedQueries({
    @NamedQuery(name = "RestaurantMstr.findAll", query = "SELECT r FROM RestaurantMstr r"),
    @NamedQuery(name = "RestaurantMstr.findByRid", query = "SELECT r FROM RestaurantMstr r WHERE r.rid = :rid"),
    @NamedQuery(name = "RestaurantMstr.findByName", query = "SELECT r FROM RestaurantMstr r WHERE r.name = :name"),
    @NamedQuery(name = "RestaurantMstr.findByAddress", query = "SELECT r FROM RestaurantMstr r WHERE r.address = :address"),
    @NamedQuery(name = "RestaurantMstr.findByDescription", query = "SELECT r FROM RestaurantMstr r WHERE r.description = :description"),
    @NamedQuery(name = "RestaurantMstr.findByImg", query = "SELECT r FROM RestaurantMstr r WHERE r.img = :img"),
    @NamedQuery(name = "RestaurantMstr.findByStatus", query = "SELECT r FROM RestaurantMstr r WHERE r.status = :status")})
public class RestaurantMstr implements Serializable {

    @Size(max = 50)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Address")
    private String address;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Size(max = 50)
    @Column(name = "Img")
    private String img;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Rid")
    private Integer rid;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private RegisterMstr email;
    @OneToMany(mappedBy = "rid")
    private Collection<MenuMstr> menuMstrCollection;

    public RestaurantMstr() {
    }

    public RestaurantMstr(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }


    public RegisterMstr getEmail() {
        return email;
    }

    public void setEmail(RegisterMstr email) {
        this.email = email;
    }
    @JsonbTransient
    public Collection<MenuMstr> getMenuMstrCollection() {
        return menuMstrCollection;
    }

    public void setMenuMstrCollection(Collection<MenuMstr> menuMstrCollection) {
        this.menuMstrCollection = menuMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantMstr)) {
            return false;
        }
        RestaurantMstr other = (RestaurantMstr) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantMstr[ rid=" + rid + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
