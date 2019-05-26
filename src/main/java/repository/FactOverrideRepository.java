package repository;

import model.FactOverride;
import model.dto.FactOverrideDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactOverrideRepository extends JpaRepository<FactOverride, Integer> {
    @Query(nativeQuery = true, value =
            "SELECT auction.fact_override.OVERRIDE_TIME as otime, auction.fact_override.PRICE as price, auction.buyer.NICKNAME as name" +
                    " FROM auction.fact_override LEFT JOIN auction.buyer ON auction.buyer.BUYER_ID = " +
                    "auction.fact_override.BUYER_ID WHERE auction.fact_override.THING_ID = ?1")
    List<FactOverrideDto> findAllByThingId(Integer thingId);
}
