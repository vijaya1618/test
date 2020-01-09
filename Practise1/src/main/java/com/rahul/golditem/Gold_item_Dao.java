package com.rahul.golditem;

import java.util.List;

public interface Gold_item_Dao {
public double get_lending_gold_rate(double gold_market_rate);
public List<GoldModel> getallitems();
public int add_gold_item(String item_name,int purity,double gold_market_rate,double gold_lending_rate);
public GoldModel get_Golditem(int id);
public int update_itemDetails(GoldModel gm);
}
