package com.ayf.areyoufull.entity;

public class Merchandise {
    private Integer shopID;
    private Integer merchandiseID;
    private String merchandiseName;
    private Float merchandisePrice;
    private String merchandiseImgPath;
    private Byte merchandiseStatus;

    public Merchandise() {}

    public Merchandise(Integer shopID, Integer merchandiseID, String merchandiseName, Float merchandisePrice, String merchandiseImgPath, Byte merchandiseStatus) {
        this.shopID = shopID;
        this.merchandiseID = merchandiseID;
        this.merchandiseName = merchandiseName;
        this.merchandisePrice = merchandisePrice;
        this.merchandiseImgPath = merchandiseImgPath;
        this.merchandiseStatus = merchandiseStatus;
    }

    public Integer getMerchandiseID() {
        return merchandiseID;
    }

    public void setMerchandiseID(Integer merchandiseID) {
        this.merchandiseID = merchandiseID;
    }

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public Float getMerchandisePrice() {
        return merchandisePrice;
    }

    public void setMerchandisePrice(Float merchandisePrice) {
        this.merchandisePrice = merchandisePrice;
    }

    public String getMerchandiseImgPath() {
        return merchandiseImgPath;
    }

    public void setMerchandiseImgPath(String merchandiseImgPath) {
        this.merchandiseImgPath = merchandiseImgPath;
    }

    public Byte getMerchandiseStatus() {
        return merchandiseStatus;
    }

    public void setMerchandiseStatus(Byte merchandiseStatus) {
        this.merchandiseStatus = merchandiseStatus;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "merchandiseID=" + merchandiseID +
                ", shopID=" + shopID +
                ", merchandiseName='" + merchandiseName + '\'' +
                ", merchandisePrice=" + merchandisePrice +
                ", merchandiseImgPath='" + merchandiseImgPath + '\'' +
                ", merchandiseStatus=" + merchandiseStatus +
                '}';
    }
}
