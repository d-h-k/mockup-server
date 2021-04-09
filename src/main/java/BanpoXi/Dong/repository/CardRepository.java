package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CardRepository extends CrudRepository<Card,Long> {
}
