package com.pyp.cast.store.domain.PO;
//商品类
public class Product {
    private String pid; //商品id
    private String pname; //商品名
    private String price;//商品价格
    private String pimage; //商品图片路径
    private String pdesc; //商品描述信息
    private Integer total; //商品总销售量

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", price='" + price + '\'' +
                ", pimage='" + pimage + '\'' +
                ", pdesc='" + pdesc + '\'' +
                ", total=" + total +
                '}';
    }
}
