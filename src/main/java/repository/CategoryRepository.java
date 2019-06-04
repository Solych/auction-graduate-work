package repository;

import model.Category;
import model.dto.CategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, value = "SELECT COUNT(THING.CATEGORY_ID) as counts, CATEGORY_NAME as cname FROM THING" +
            " RIGHT JOIN CATEGORY C on THING.CATEGORY_ID = C.CATEGORY_ID AND OWNER_ID = ?1 GROUP BY C.CATEGORY_ID")
    List<CategoryCount> findCategoriesWithCountForUser(Integer buyerId);
}
