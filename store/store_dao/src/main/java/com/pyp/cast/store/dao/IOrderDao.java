package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {
    /**
     * 根据用户id查询其未支付的订单及购物车内容
     * @param uid 用户id
     * @return
     * @throws Exception
     */
    @Select("select * from `order` where uid=#{uid} and statu=0")
    Order findNoPaidOrderByUid(@Param("uid") String uid)throws Exception;

    /**
     * 添加一条新的未支付订单
     * @param oid
     * @param uid
     * @throws Exception
     */
    @Insert("insert `order`(oid,uid) values (#{oid},#{uid})")
    void addNoPaidOrderByUid(@Param("oid") String oid,@Param("uid") String uid)throws Exception;

    /**
     * 插入一条新的订单项
     * @param itemid
     * @param quantity
     * @param total_price
     * @param pid
     * @param oid
     * @throws Exception
     */
    @Insert("insert orderitem(itemid, quantity, total_price, pid, oid) VALUES (#{itemid},#{quantity},#{total_price},#{pid},#{oid});")
    void addCartItemByUid(@Param("itemid")String itemid,@Param("quantity") String quantity,@Param("total_price") int total_price,@Param("pid") String pid,@Param("oid") String oid)throws Exception;

    @Select("select * from orderitem where oid in (select oid from `order` where uid=#{uid} and statu=0)")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findNoPaidOrderItemsByUid(@Param("uid") String uid);

    /**
     * 根据商品id和订单id查找该订单项的详细信息
     * @param pid
     * @param oid
     * @return
     */
    @Select("select * from orderitem where oid=#{oid} and pid=#{pid}")
    OrderItem findOrderItemByPidAndOid(@Param("pid") String pid,@Param("oid") String oid);

    /**
     * 更新订单项的商品数量和商品总价格
     * @param num 商品数量
     * @param total 商品总价格
     * @param itemid 订单项id
     */
    @Update("update orderitem set quantity=#{quantity},total_price=#{total_price} where itemid=#{itemid}")
    void updateOrderItemByPidAndOid(@Param("quantity") Integer num,@Param("total_price") Integer total,@Param("itemid") String itemid);

    /**
     * 根据用户id，删除购物车中的商品
     * @param uid
     * @param pid
     * @throws Exception
     */
    @Delete("delete from orderitem where pid=#{pid} and oid in (select oid from `order` where uid=#{uid} and statu=0)")
    void deleteCartItemByUidAndPid(@Param("uid") String uid,@Param("pid") String pid)throws Exception;

    /**
     * 根据用户id，删除购物车中所有的商品
     * @param uid
     * @throws Exception
     */
    @Delete("delete from orderitem where oid in (select oid from `order` where uid=#{uid} and statu=0)")
    void deleteAllCartByUid(@Param("uid") String uid)throws Exception;

    /**
     * 更新订单信息
     * @param oid 订单id
     * @param ordertime 下单时间
     * @param total_price 总价格
     * @param adress 收货地址
     * @param name 收货人姓名
     * @param email 收货人邮箱
     * @param statu 订单状态
     */
    @Update("update `order` set statu=#{statu},ordertime=#{ordertime},total_price=#{total_price},adress=#{adress},name=#{name},email=#{email} where oid=#{oid}")
    void updateOrderStatuByOid(@Param("oid") String oid,@Param("ordertime") String ordertime,@Param("total_price") String total_price,@Param("adress") String adress,@Param("name") String name,@Param("email") String email, @Param("statu")String statu);

    /**
     * 查询该用户的所有订单总量
     * @param uid 用户id
     * @return
     * @throws Exception
     */
    @Select("select count(*) from `order` where uid=#{uid}")
    int findTotalRecords(String uid)throws Exception;

    /**
     * 查询该用户的当前页的所有订单内容
     * @param uid  用户id
     * @param startIndex 开始查询条数
     * @param pageSize 查询总条数
     * @return
     */
    @Select("select * from `order` where uid=#{uid} limit  #{startIndex},#{pageSize}")
    List<Order> findMyOrdersWithpage(@Param("uid")String uid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    /**
     * 查询当前订单所有的订单项
     * @param oid
     * @return
     * @throws Exception
     */
    @Select("select * from orderitem where oid=#{oid}")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findMyOrderItemsByOid(String oid)throws Exception;

    /**
     * 根据商品id，删除存在该商品的所有订单项
     * @param pid
     * @throws Exception
     */
    @Delete("delete from `orderitem` where pid=#{pid}")
    void deleteProductByPid(@Param("pid") String pid)throws Exception;
}
