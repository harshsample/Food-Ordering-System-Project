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
@Table(name = "menu_mstr")
@NamedQueries({
    @NamedQuery(name = "MenuMstr.findAll", query = "SELECT m FROM MenuMstr m"),
    @NamedQuery(name = "MenuMstr.findByMid", query = "SELECT m FROM MenuMstr m WHERE m.mid = :mid"),
    @NamedQuery(name = "MenuMstr.findByName", query = "SELECT m FROM MenuMstr m WHERE m.name = :name"),
    @NamedQuery(name = "MenuMstr.findByDescription", query = "SELECT m FROM MenuMstr m WHERE m.description = :description"),
    @NamedQuery(name = "MenuMstr.findByImg", query = "SELECT m FROM MenuMstr m WHERE m.img = :img"),
    @NamedQuery(name = "MenuMstr.findByPrice", query = "SELECT m FROM MenuMstr m WHERE m.price = :price"),
    @NamedQuery(name = "MenuMstr.findByQty", query = "SELECT m FROM MenuMstr m WHERE m.qty = :qty"),
    @NamedQuery(name = "MenuMstr.findByStatus", query = "SELECT m FROM MenuMstr m WHERE m.status = :status")})
public class MenuMstr implements Serializable {

    @Size(max = 50)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Size(max = 50)
    @Column(name = "Img")
    private String img;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;
    @OneToMany(mappedBy = "mid")
    private Collection<OrderDetailsMstr> orderDetailsMstrCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Mid")
    private Integer mid;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Qty")
    private Integer qty;
    @JoinColumn(name = "Rid", referencedColumnName = "Rid")
    @ManyToOne
    private RestaurantMstr rid;
    @OneToMany(mappedBy = "mid")
    private Collection<CartMstr> cartMstrCollection;

    public MenuMstr() {
    }

    public MenuMstr(Integer mid) {
        this.mid = mid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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


    public RestaurantMstr getRid() {
        return rid;
    }

    public void setRid(RestaurantMstr rid) {
        this.rid = rid;
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
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuMstr)) {
            return false;
        }
        MenuMstr other = (MenuMstr) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MenuMstr[ mid=" + mid + " ]";
    }
    
    @JsonbTransient
    public Collection<OrderDetailsMstr> getOrderDetailsMstrCollection() {
        return orderDetailsMstrCollection;
    }
    public void setOrderDetailsMstrCollection(Collection<OrderDetailsMstr> orderDetailsMstrCollection) {
        this.orderDetailsMstrCollection = orderDetailsMstrCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
