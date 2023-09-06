package guru.springframework.services;

import guru.springframework.domain.Category;
import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();

    Category getById(Long id);

    Category findByDescription(String description);

}
