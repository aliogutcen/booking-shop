package com.ogutcenali.repository;

import com.ogutcenali.repository.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByAuthid(Long authid);
}
