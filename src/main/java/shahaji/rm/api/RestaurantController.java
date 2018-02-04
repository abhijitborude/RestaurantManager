package shahaji.rm.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import shahaji.rm.dao.MenuItemsRepository;
import shahaji.rm.dao.MenuRepository;
import shahaji.rm.dao.RestaurantRepository;
import shahaji.rm.model.Menu;
import shahaji.rm.model.MenuItem;
import shahaji.rm.model.MenuType;
import shahaji.rm.model.Restaurant;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Restaurant getRestaurantById(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        return restaurant;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> findRestaurantByName(@RequestParam("name") String name) {
        List<Restaurant> restaurants = restaurantRepository.findByNameLike("%" + name + "%");
        return restaurants;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteRestaurant(@RequestParam("id") Long id) {
        restaurantRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Restaurant updateRestaurant(@RequestParam("id") Long id,
                                       @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if (restaurant != null) {
            restaurant.setName(updatedRestaurant.getName());
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setTimings(updatedRestaurant.getTimings());
            restaurant = restaurantRepository.save(restaurant);
        } else {
            throw new RuntimeException("Restaurant with " + id + " does not exist");
        }
        return restaurant;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/menus")
    public List<Menu> findMenusByRestaurantId(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        return restaurant.getMenus();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/menus/{menu_id}")
    public Menu findMenuById(@PathVariable("id") Long id,
                             @PathVariable("menu_id") long menu_id) {
        Menu menu = menuRepository.findOne(menu_id);
        Restaurant restaurant = restaurantRepository.findOne(id);
        restaurant.getMenus().remove(menu);
        restaurantRepository.save(restaurant);
        //menuRepository.delete(menu_id);
        return menu;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/menus")
    public Menu addMenu(@RequestBody Menu menu,
                        @PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        restaurant.getMenus().add(menu);
        restaurantRepository.save(restaurant);
        return menu;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/menus/{menu_id}")
    public Menu editMenu(@RequestBody Menu updatedMenu,
                         @PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        Menu menu = menuRepository.findOne(updatedMenu.getId());
        restaurant.getMenus().remove(menu);
        restaurant.getMenus().add(updatedMenu);
        restaurantRepository.save(restaurant);
        return updatedMenu;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/menus/{menu_id}/menuitems")
    public List<MenuItem> findMenuItemsByMenuId(@PathVariable("id") Long id,
                                                @PathVariable("menu_id") Long menuId) {
        Menu menu = menuRepository.findOne(menuId);
        List<MenuItem> menuItems = menu.getItems();
        return menuItems;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/menus/{menu_id}/menuitems")
    public List<MenuItem> createMenuItem(@PathVariable("id") Long id,
                                         @PathVariable("menu_id") Long menuId,
                                         @RequestBody MenuItem menuItem) {
        Menu menu = menuRepository.findOne(menuId);
        List<MenuItem> menuItems = menu.getItems();
        menuItems.add(menuItem);
        Menu save = menuRepository.save(menu);
        return menuItems;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/menus/{menu_id}/menuitems/{menuitems_id}")
    public MenuItem deleteMenuItem(@PathVariable("menuitems_id") Long menuItemsId,
                                   @PathVariable("menu_id") Long menuId) {
        MenuItem menuItem = menuItemsRepository.findOne(menuItemsId);
        menuItemsRepository.delete(menuItemsId);
        return menuItem;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/menus/{menu_id}/menusitems/{menuitems_id}")
    public MenuItem updateMenuItem(@PathVariable("menuitems_id") Long menuItemsId,
                                   @RequestBody MenuItem menuItem) {
        menuItemsRepository.delete(menuItemsId);
        menuItemsRepository.save(menuItem);
        return menuItem;
    }

}
