package repository;

import model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ThingRepository extends JpaRepository<Thing, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM auction.THING WHERE OWNER_ID = ?1")
    List<Thing> findAllByOwnerId(Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM auction.thing WHERE TIME_FOR_SELLING > ?1 LIMIT 24")
    List<Thing> findRandom24(Date now);

    @Query(nativeQuery = true, value =
            "SELECT * FROM auction.thing WHERE TIME_FOR_SELLING > ?1 AND CATEGORY_ID = ?2 SKIP ?3*24 LIMIT 24")
    List<Thing> findByCategoryPageable(Date now, Integer categoryId, Integer page);

    Thing findByThingId(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auction.thing where auction.thing.NAME LIKE ?1 AND TIME_FOR_SELLING > ?2")
    List<Thing> getThingsByNameLike(String name, Date now);
}
