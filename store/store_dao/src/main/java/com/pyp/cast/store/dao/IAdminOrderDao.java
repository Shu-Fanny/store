package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IAdminOrderDao {
    @Select("select count(*) from `order` where statu=#{statu}")
    int findTotalRecords(String statu)throws Exception;

    /**
     * 查找该订单下所有的订单项
     * @param oid 订单id，查找条件
     * @return
     * @throws Exception
     */
    @Select("select * from `orderitem` where oid=#{oid}")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findOrderItemsByOid(@Param("oid") String oid) throws Exception;

    /**
     * 查找订单状态为statu的所有订单信息
     * @param statu 订单状态
     * @param startIndex 开始条数
     * @param pageSize 总查询条数
     * @return
     * @throws Exception
     */
    @Select("select * from `order` where statu=#{statu} limit #{startIndex},#{pageSize}")
    List<Order> findProductsByCidWithPage(@Param("statu") String statu,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize)throws Exception;

    /**
     * 根据订单id，修改他的订单状态
     * @param statu 待修改的订单状态
     * @param oid 查询条件订单id
     * @throws Exception
     */
    @Update("update `order` set statu=#{statu} where oid=#{oid}")
    void updateOrderStatuByOid(@Param("statu") int statu,@Param("oid") String oid)throws Exception;

    //根据订单id，查找该订单的收货人邮箱
    @Select("select email from `order` where oid=#{oid}")
    String findEmailByOid(@Param("oid") String oid)throws Exception;
}
