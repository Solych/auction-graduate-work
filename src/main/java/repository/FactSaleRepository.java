package repository;

import model.FactSale;
import model.dto.WinningsThingOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactSaleRepository extends JpaRepository<FactSale, Integer> {
    @Query(nativeQuery = true, value = "SELECT FACT_SALE.PRICE as price, FACT_SALE.SALE_TIME as stime, THING.PICTURE as" +
            " picture, THING.NAME as name FROM FACT_SALE LEFT JOIN THING ON FACT_SALE.THING_ID = THING.THING_ID WHERE FACT_SALE.BUYER_ID = ?1")
    List<WinningsThingOwner> getData(Integer buyerId);
}
