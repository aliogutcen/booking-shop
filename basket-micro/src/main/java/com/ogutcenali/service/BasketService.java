package com.ogutcenali.service;

import com.ogutcenali.dto.request.AddToCartRequestDto;
import com.ogutcenali.rabbitmq.model.OrderAdd;
import com.ogutcenali.rabbitmq.model.OrderSuccess;
import com.ogutcenali.rabbitmq.producer.OrderProducer;
import com.ogutcenali.repository.ICartRepository;
import com.ogutcenali.repository.entity.Cart;
import com.ogutcenali.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
@RequiredArgsConstructor
public class BasketService {

    private final ICartRepository cartRepository;
    private final JwtTokenManager jwtTokenManager;
    private final OrderProducer orderProducer;

    public Cart addItemToCart(AddToCartRequestDto addToCartRequestDto) {
        Long userId = jwtTokenManager.decodeToken(addToCartRequestDto.getToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        Cart cart = cartRepository.findById(String.valueOf(userId))
                .orElseGet(() -> cartRepository.save(new Cart(String.valueOf(userId))));

        cart.addProduct(addToCartRequestDto.getBookid(), addToCartRequestDto.getQuantity());
        cartRepository.save(cart);

        return cart;
    }
    public Map<Long, Integer> getCartItem(String cartid) {
        Optional<Cart> cart = cartRepository.findById(cartid);
        return cart.get().getProducts();

    }
    public Boolean addToOrder(String cartid) {
        Optional<Cart> cart = cartRepository.findById(cartid);
        orderProducer.orderAdd(OrderAdd.builder()
                .authid(Long.valueOf(cartid))
                .bookList(cart.get().getProducts())
                .build());
        return true;
    }
    public Boolean removeCart(String cartid) {
        Optional<Cart> cart = cartRepository.findById(cartid);
        cart.get().clear();
        cartRepository.save(cart.get());
        return true;
    }

    public void successOrderRemoveBasket(OrderSuccess orderSuccess) {
        Optional<Cart> cart= cartRepository.findById(String.valueOf(orderSuccess.getAuthid()));
        cart.get().clear();
        cartRepository.save(cart.get());
    }
}

