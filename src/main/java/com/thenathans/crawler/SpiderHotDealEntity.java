package com.thenathans.crawler;

public class SpiderHotDealEntity {

    public int getHotTemp() {
        return hotTemp;
    }

    public void setHotTemp(int hotTemp) {
        this.hotTemp = hotTemp;
    }

    public String getHottestDeal() {
        return hottestDeal;
    }

    public void setHottestDeal(String hottestDeal) {
        this.hottestDeal = hottestDeal;
    }

    String hottestDeal;
    int hotTemp = 0;
}
