package shahaji.rm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MenuType type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<MenuItem> items = new ArrayList<>();

    public Menu() {
    }

    public Menu(MenuType type) {
        super();
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", type=" + type +
                ", items=" + items +
                '}';
    }
}
