package com.vasslatam.sakila.repository;

import com.vasslatam.sakila.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego Silva Limaco <diego.silva at apuntesdejava.com>
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
