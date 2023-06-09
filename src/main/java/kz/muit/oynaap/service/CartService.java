package kz.muit.oynaap.service;

import kz.muit.oynaap.models.Cart;
import kz.muit.oynaap.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;

    public void addNewCartItem(Cart cart) {
        cartRepo.insertCartItem(cart);
    }

    public List<Cart> getAllCartItems(Cart cart) {
        return cartRepo.selectAllCartItems(cart);
    }

    public void removeCartItem(int cart_id) {
        cartRepo.deleteCartItem(cart_id);
    }

    public void updateCartItem(Cart cart) {
        cartRepo.updateCartItem(cart);
    }

    public Double grandTotal(Cart cart) {
        Double grandTotal = cartRepo.grandTotal(cart);
        return grandTotal;
    }

    public List<Integer> selectAllCartItemsId(Cart cart) {
        List<Integer> cartItemsIdList = new LinkedList<>();
        cartItemsIdList = cartRepo.selectAllCartItemsId(cart);
        return cartItemsIdList;
    }

    public List<Cart> selectUserPaidCartItems(Integer order_id) {
        return cartRepo.selectUserPaidCartItems(order_id);
    }

}
