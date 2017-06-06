package com.zxzx74147.modules_dbd.info.data;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class BidData {
    /**
     * bidTime : 1482215158316
     * bidTime2 : 1482215158
     * bidTimeStr : 14:25
     * bidTimeStr1 : 2016-12-20  14:25:58
     * bidTimeStr2 : 48分钟前
     * icon : //static.jd.com/ppms/img/20160127/pcd_2016012753238148167772728074444.jpg
     * paimaiId : 14088009
     * platType : 0
     * price : 303
     * priceStr : 303.00
     * productId : 2266735
     * userArea :
     * userLevel : 61
     * userName1 : jd_70c6366439374
     * username : ****1mbk
     */

    private long bidTime;
    private int bidTime2;
    private String bidTimeStr;
    private String bidTimeStr1;
    private String bidTimeStr2;
    private String icon;
    private int paimaiId;
    private int platType;
    private int price;
    private String priceStr;
    private long productId;
    private String userArea;
    private String userLevel;
    private String userName1;
    private String username;

    public long getBidTime() {
        return bidTime;
    }

    public void setBidTime(long bidTime) {
        this.bidTime = bidTime;
    }

    public int getBidTime2() {
        return bidTime2;
    }

    public void setBidTime2(int bidTime2) {
        this.bidTime2 = bidTime2;
    }

    public String getBidTimeStr() {
        return bidTimeStr;
    }

    public void setBidTimeStr(String bidTimeStr) {
        this.bidTimeStr = bidTimeStr;
    }

    public String getBidTimeStr1() {
        return bidTimeStr1;
    }

    public void setBidTimeStr1(String bidTimeStr1) {
        this.bidTimeStr1 = bidTimeStr1;
    }

    public String getBidTimeStr2() {
        return bidTimeStr2;
    }

    public void setBidTimeStr2(String bidTimeStr2) {
        this.bidTimeStr2 = bidTimeStr2;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPaimaiId() {
        return paimaiId;
    }

    public void setPaimaiId(int paimaiId) {
        this.paimaiId = paimaiId;
    }

    public int getPlatType() {
        return platType;
    }

    public void setPlatType(int platType) {
        this.platType = platType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName1() {
        return userName1==null? "":userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
