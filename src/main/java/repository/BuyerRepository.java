package repository;

import model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuyerRepository extends JpaRepository<Buyer,Integer> {

    Buyer findByNickNameAndPassword(String nickName, String password);
}
