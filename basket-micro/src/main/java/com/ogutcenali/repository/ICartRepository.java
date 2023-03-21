package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends CrudRepository<Cart,String> {
}
