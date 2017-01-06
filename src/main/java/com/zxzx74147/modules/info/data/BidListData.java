package com.zxzx74147.modules.info.data;

import java.util.List;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class BidListData {


    /**
     * auctionStatus : 2
     * bidCount : 8
     * bidList : [{"bidTime":1482215158316,"bidTime2":1482215158,"bidTimeStr":"14:25","bidTimeStr1":"2016-12-20  14:25:58","bidTimeStr2":"48分钟前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":303,"priceStr":"303.00","productId":2266735,"userArea":"","userLevel":"61","userName1":"jd_70c6366439374","username":"****1mbk"},{"bidTime":1482215088737,"bidTime2":1482215088,"bidTimeStr":"14:24","bidTimeStr1":"2016-12-20  14:24:48","bidTimeStr2":"49分钟前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":302,"priceStr":"302.00","productId":2266735,"userArea":"","userLevel":"61","userName1":"小瘦黑头","username":"****头"},{"bidTime":1482215051029,"bidTime2":1482215051,"bidTimeStr":"14:24","bidTimeStr1":"2016-12-20  14:24:11","bidTimeStr2":"50分钟前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":301,"priceStr":"301.00","productId":2266735,"userArea":"","userLevel":"62","userName1":"jd_594450776ed55","username":"****风小霸王"},{"bidTime":1482215045423,"bidTime2":1482215045,"bidTimeStr":"14:24","bidTimeStr1":"2016-12-20  14:24:05","bidTimeStr2":"50分钟前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":300,"priceStr":"300.00","productId":2266735,"userArea":"","userLevel":"105","userName1":"bianhongxing","username":"****星星__"},{"bidTime":1482215013545,"bidTime2":1482215013,"bidTimeStr":"14:23","bidTimeStr1":"2016-12-20  14:23:33","bidTimeStr2":"50分钟前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":200,"priceStr":"200.00","productId":2266735,"userArea":"","userLevel":"62","userName1":"jd_594450776ed55","username":"****风小霸王"},{"bidTime":1482214303672,"bidTime2":1482214303,"bidTimeStr":"14:11","bidTimeStr1":"2016-12-20  14:11:43","bidTimeStr2":"1小时前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":150,"priceStr":"150.00","productId":2266735,"userArea":"","userLevel":"62","userName1":"jd_61c7cb3f19ef7","username":"****麒"},{"bidTime":1482214142571,"bidTime2":1482214142,"bidTimeStr":"14:09","bidTimeStr1":"2016-12-20  14:09:02","bidTimeStr2":"1小时前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":3,"priceStr":"3.00","productId":2266735,"userArea":"","userLevel":"61","userName1":"221191435-587102","username":"****qian"},{"bidTime":1482214012936,"bidTime2":1482214012,"bidTimeStr":"14:06","bidTimeStr1":"2016-12-20  14:06:52","bidTimeStr2":"1小时前","icon":"//static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg","paimaiId":14088009,"platType":0,"price":2,"priceStr":"2.00","productId":2266735,"userArea":"","userLevel":"56","userName1":"jd_53b39be081bb9","username":"****king"}]
     * currentNum : 1
     * currentPrice : 303
     * nextPrice : 0
     * nextPriceStr : 0
     * orderStatus : 0
     * refreshTime : 1000
     * remainTime : -1
     * stockNum : 1
     */

    private int auctionStatus;
    private int bidCount;
    private int currentNum;
    private float currentPrice;
    private String nextPrice;
    private String nextPriceStr;
    private int orderStatus;
    private int refreshTime;
    private String remainTime;
    private String stockNum;
    private List<BidData> bidList;

    public int getAuctionStatus() {
        return auctionStatus;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public int getCurrentNum() {
        return currentNum;
    }


    public float getCurrentPrice() {
        return currentPrice;
    }


    public String getNextPrice() {
        return nextPrice;
    }

    public void setNextPrice(String nextPrice) {
        this.nextPrice = nextPrice;
    }

    public String getNextPriceStr() {
        return nextPriceStr;
    }

    public void setNextPriceStr(String nextPriceStr) {
        this.nextPriceStr = nextPriceStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public List<BidData> getBidList() {
        return bidList;
    }

    public void setBidList(List<BidData> bidList) {
        this.bidList = bidList;
    }

}
