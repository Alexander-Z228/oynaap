package kz.muit.oynaap.repository;

import kz.muit.oynaap.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class CartRepository implements SQLCOMMAND {

    @Autowired
    private JdbcTemplate template;

    public boolean insertCartItem(Cart cart) {

        int count = template.update(SQL_INSERT_CART,
                cart.getPrice(),
                cart.getQuantity(),
                cart.getStatus(),
                cart.getGm_id(),
                cart.getUsername(),
                cart.getGame_name());

        return count == 1;
    }

    public List<Cart> selectAllCartItems(Cart cart) {
        List<Cart> cartItems = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CART_ITEMS, cart.getUsername());
        while (rs.next()) {
            Cart cartItem = Cart.convert(rs);
            cartItems.add(cartItem);
        }

        return cartItems;
    }

    public List<Integer> selectAllCartItemsId(Cart cart) {
        List<Integer> cartItemsIdList = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CART_ITEMS_ID, cart.getUsername());
        while (rs.next()) {
            Integer cartItemsId = rs.getInt("cart_id");

            cartItemsIdList.add(cartItemsId);
        }

        return cartItemsIdList;
    }


    public boolean deleteCartItem(int cart_id) {
        int count = template.update(SQL_REMOVE_CART_ITEM,
                cart_id);

        return count == 1;
    }

    public boolean updateCartItem(Cart cart) {

        int count = template.update(SQL_UPDATE_CART_ITEM,
                cart.getQuantity(),
                cart.getCart_id());

        return count == 1;
    }

    public Double grandTotal(Cart cart) {
        Double grandTotal = 0.0;
        SqlRowSet rs = template.queryForRowSet(SQL_GRAND_TOTAL, cart.getUsername());
        while (rs.next()) {
            Double subTotal = rs.getDouble("subtotal");
            grandTotal += subTotal;
        }

        return grandTotal;
    }

    public List<Cart> selectUserPaidCartItems(Integer order_id) {
        List<Cart> cartItems = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_PAID_CART_ITEMS, order_id);
        while (rs.next()) {
            Cart cartItem = Cart.convert(rs);
            cartItems.add(cartItem);
        }

        return cartItems;
    }

}
