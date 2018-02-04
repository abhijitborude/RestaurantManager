package shahaji.rm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shahaji.rm.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findByName(String name);
    List<Restaurant> findByNameLike(String name);
}
