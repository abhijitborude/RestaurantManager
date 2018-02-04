package shahaji.rm.dao;
import shahaji.rm.model.MenuItem;
import org.springframework.data.repository.CrudRepository;


public interface MenuItemsRepository extends CrudRepository<MenuItem, Long> {
}
