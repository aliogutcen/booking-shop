package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AddToCartRequestDto;
import com.ogutcenali.repository.entity.Cart;
import com.ogutcenali.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sepet")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @PostMapping("/urunekle")
    public ResponseEntity<Cart> addToBook(@RequestBody AddToCartRequestDto addToCartRequestDto) {
        return ResponseEntity.ok(basketService.addItemToCart(addToCartRequestDto));
    }

    @PostMapping("/siparisver/{cartid}")
    public ResponseEntity<Boolean> addToOrder(@PathVariable String cartid){
        return ResponseEntity.ok(basketService.addToOrder(cartid));
    }



    @GetMapping("/{cartid}")
    public ResponseEntity<Map<Long, Integer>> getCartItem(@PathVariable String cartid) {
        Map<Long, Integer> cart = basketService.getCartItem(cartid);
        System.out.println(cart);
        return ResponseEntity.ok(cart);
    }


  @GetMapping("/temizle/{cartid}")
  public ResponseEntity<Boolean> removeCart(@PathVariable String cartid)  {
        return ResponseEntity.ok(basketService.removeCart(cartid));
  }

}
