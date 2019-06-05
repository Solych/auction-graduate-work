package repository;

import model.FactOverride;
import model.dto.FactOverrideDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactOverrideRepository extends JpaRepository<FactOverride, Integer> {
    @Query(nativeQuery = true, value =
            "SELECT auction.FACT_OVERRIDE.OVERRIDE_TIME as otime, auction.FACT_OVERRIDE.PRICE as price, auction.BUYER.NICKNAME as name" +
                    " FROM auction.FACT_OVERRIDE LEFT JOIN auction.BUYER ON auction.BUYER.BUYER_ID = " +
                    "auction.FACT_OVERRIDE.BUYER_ID WHERE auction.FACT_OVERRIDE.THING_ID = ?1")
    List<FactOverrideDto> findAllByThingId(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auction.FACT_OVERRIDE WHERE THING_ID = ?1 ORDER BY PRICE LIMIT 1")
    FactOverride findLastPrice(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auction.FACT_OVERRIDE WHERE BUYER_ID = ?1")
    List<FactOverride> findAllByUserId(Integer userId);
}
