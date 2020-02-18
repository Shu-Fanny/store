package com.pyp.cast.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.pyp.cast.store.dao.IOrderDao;
import com.pyp.cast.store.dao.IProductDao;
import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.service.IProductService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IOrderDao orderDao;

    /**
     * 查询当前页所有的商品信息
     * @param curNum
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findAllProductWithpage(int curNum) throws Exception {
        //查询商品总条数
        int totalRecords=productDao.findTotalRecords();
        PageModel pm=new PageModel(curNum,totalRecords,12);
        //查询当前页所有商品的详细信息
        List<Product> products=productDao.findProductsByCidWithPage(pm.getStartIndex(),pm.getPageSize());
        for (Product product:products) {
            //查询商品销售量
            product.setTotal(productDao.findSaleProductTotalByPid(product.getPid()));
        }
        pm.setList(products);
        pm.setUrl("/product/findAllProduct.do");
        return pm;
    }

    /**
     * 根据商品id以及用户id，查询其浏览次数
     * @param uid 用户id
     * @param pid 商品id
     * @return
     * @throws Exception
     */
    @Override
    public Product findProductByPid(String uid,String pid) throws Exception {
        //根据商品id以及用户id，查询其浏览详细信息
        Browse browse = productDao.findBrowseProduct(uid,pid);
        if (browse == null){
            //如果浏览次数为空，则为其添加浏览记录
            productDao.addBrowseProduct(uid,pid);
        }else {
            //如果浏览次数不为空，则为其浏览记录+1
            productDao.updateBrowseProductTotal(uid,pid,browse.getTotal()+1);
        }
        //根据商品id查询该商品的详细信息
        return productDao.findProductByPid(pid);
    }

    /**
     * 根据商品id查询该商品的详细信息
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public Product findProductByPid02(String pid) throws Exception {
        return productDao.findProductByPid(pid);
    }

    /**
     * 修改商品信息
     * @param product
     * @throws Exception
     */
    @Override
    public void updateProductByPid(Product product) throws Exception {
        productDao.updateProductByPid(product);
    }

    /**
     * 删除商品
     * @param pid
     * @throws Exception
     */
    @Override
    public void deleteProductByPid(String pid) throws Exception {
        //1、先删除存在该商品的浏览记录
        productDao.deleteBrowseByPid(pid);
        //2、再删除存在该商品的订单项
        orderDao.deleteProductByPid(pid);
        //3、最后删除商品
        productDao.deleteProductByPid(pid);
    }

    /**
     * 添加商品
     * @param product
     * @throws Exception
     */
    @Override
    public void addProduct(Product product) throws Exception {
        product.setPid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        productDao.addProduct(product);
    }
}
