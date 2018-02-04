package shahaji.rm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String timings;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    private List<Menu> menus = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String address, String timings, String description) {
        this.name = name;
        this.address = address;
        this.timings = timings;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", timings='" + timings + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
