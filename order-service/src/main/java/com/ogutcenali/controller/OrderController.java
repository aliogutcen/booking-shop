package com.ogutcenali.controller;


import com.ogutcenali.dto.response.GetAllOrderResponseDto;
import com.ogutcenali.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ogutcenali.constant.RestEndPoints.ORDER;

@RestController
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @GetMapping("/{authid}")
    public ResponseEntity<List<GetAllOrderResponseDto>> getAllOrderById(@PathVariable Long authid){
        return ResponseEntity.ok(orderService.getAllOrderByUserId(authid));
    }
}
