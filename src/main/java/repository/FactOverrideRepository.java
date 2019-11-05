package repository;

import model.FactOverride;
import model.dto.FactOverrideDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactOverrideRepository extends JpaRepository<FactOverride, Integer> {
    @Query(nativeQuery = true, value =
            "SELECT auctionSchema.FACT_OVERRIDE.OVERRIDE_TIME as otime, auctionSchema.FACT_OVERRIDE.PRICE as price, auctionSchema.BUYER.NICKNAME as name" +
                    " FROM auctionSchema.FACT_OVERRIDE LEFT JOIN auctionSchema.BUYER ON auctionSchema.BUYER.BUYER_ID = " +
                    "auctionSchema.FACT_OVERRIDE.BUYER_ID WHERE auctionSchema.FACT_OVERRIDE.THING_ID = ?1")
    List<FactOverrideDto> findAllByThingId(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.FACT_OVERRIDE WHERE THING_ID = ?1 ORDER BY PRICE LIMIT 1")
    FactOverride findLastPrice(Integer thingId);
}
