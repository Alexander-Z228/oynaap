package kz.muit.oynaap.repository;

import kz.muit.oynaap.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


@Repository
public class OrderRepository implements SQLCOMMAND {

    @Autowired
    private JdbcTemplate template;

    public Integer insertOrder(final Order order) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ORDER,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, order.getTotal_amount());
            ps.setDate(2, order.getOrder_date());
            ps.setString(3, order.getUsername());
            return ps;
        }, keyholder);

        BigInteger bigint = (BigInteger) keyholder.getKey();
        return bigint.intValue();
    }

    public boolean updateOrderCartItem(Integer cartId, Integer orderId) {

        int count = template.update(SQL_UPDATE_ORDER_CART_ITEMS,
                orderId, cartId);

        return count == 1;
    }

    public boolean updateOrderPayment(Order order) {

        int count = template.update(SQL_UPDATE_ORDER_PAYMENT,
                order.getPayment_intent(),
                order.getPayment_intent_client_secret(),
                order.getOrder_id());

        return count == 1;
    }

    public List<Order> selectAllUserPaidOrders(String username) {
        List<Order> orders = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_PAID_ORDERS, username);
        while (rs.next()) {
            Order order = Order.convert(rs);
            orders.add(order);
        }

        return orders;
    }

    public List<Order> selectAllPaidOrders() {
        List<Order> orders = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_PAID_ORDERS);
        while (rs.next()) {
            Order order = Order.convert(rs);
            orders.add(order);
        }

        return orders;
    }


}