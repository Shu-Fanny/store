package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {
    /**
     * 查询商品总条数
     * @return
     */
    @Select("select count(*) from product")
    int findTotalRecords();

    /**
     * 查询当前页所有商品的详细信息
     * @param startIndex 开始条数
     * @param pageSize 总条数=结束条数-开始条数
     * @return
     */
    @Select("select * from product limit  #{startIndex},#{pageSize}")
    List<Product> findProductsByCidWithPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 根据商品id查询该商品的详细信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Select("select * from product where pid=#{pid}")
    Product findProductByPid(@Param("pid") String pid) throws Exception;

    /**
     * 根据商品id查询其价格
     * @param pid
     * @return
     * @throws Exception
     */
    @Select("select price from product where pid=#{pid}")
    Integer findProPriceByPid(String pid) throws Exception;

    /**
     * 修改商品信息
     * @param product
     * @throws Exception
     */
    @Update("update product set pname=#{pname},price=#{price},pimage=#{pimage},pdesc=#{pdesc} where pid=#{pid}")
    void updateProductByPid(Product product)throws Exception;

    /**
     * 删除商品
     * @param pid
     * @throws Exception
     */
    @Delete("delete from `product` where pid=#{pid}")
    void deleteProductByPid(@Param("pid") String pid)throws Exception;

    //查询商品销售量
    @Select("select sum(quantity) from orderitem where pid=#{pid} and oid in (select oid from `order` where statu>0)")
    Integer findSaleProductTotalByPid(@Param("pid") String pid);

    @Insert("insert product values (#{pid},#{pname},#{price},#{pimage},#{pdesc})")
    void addProduct(Product product);


    /**
     * 根据商品id以及用户id，查询其浏览详细信息
     * @param uid
     * @param pid
     * @return
     * @throws Exception
     */
    @Select("select * from browse where uid=#{uid} and pid=#{pid}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "total", column = "total"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    Browse findBrowseProduct(@Param("uid") String uid,@Param("pid") String pid)throws Exception;

    /**
     * 添加一条浏览记录
     * @param uid
     * @param pid
     */
    @Insert("insert into browse values (#{uid},#{pid},1)")
    void addBrowseProduct(@Param("uid") String uid,@Param("pid") String pid);

    /**
     * 根据用户id和商品id，更新浏览记录的总浏览次数
     * @param uid 用户id
     * @param pid 商品id
     * @param total 浏览次数
     */
    @Update("update browse set total=#{total} where uid=#{uid} and pid=#{pid}")
    void updateBrowseProductTotal(@Param("uid") String uid,@Param("pid") String pid,@Param("total") int total);

    /**
     * 先删除存在该商品的浏览记录
     * @param pid
     */
    @Delete("delete from itcast_store.browse where pid=#{pid}")
    void deleteBrowseByPid(@Param("pid") String pid);
}
