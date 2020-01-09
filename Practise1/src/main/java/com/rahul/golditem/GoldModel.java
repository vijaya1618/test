package com.rahul.golditem;

public class GoldModel {
private int gold_item_id;

private String gold_item_name;
private double gold_item_market_rate;
private double gold_item_lending_rate;
private String gold_item_date;
private int gold_item_purity;

public int getGold_item_purity() {
	return gold_item_purity;
}
public void setGold_item_purity(int gold_item_purity) {
	this.gold_item_purity = gold_item_purity;
}
public String getGold_item_date() {
	return gold_item_date;
}
public void setGold_item_date(String gold_item_date) {
	this.gold_item_date = gold_item_date;
}
public int getGold_item_id() {
	return gold_item_id;
}
public void setGold_item_id(int gold_item_id) {
	this.gold_item_id = gold_item_id;
}
public String getGold_item_name() {
	return gold_item_name;
}
public void setGold_item_name(String gold_item_name) {
	this.gold_item_name = gold_item_name;
}
public double getGold_item_market_rate() {
	return gold_item_market_rate;
}
public void setGold_item_market_rate(double gold_item_market_rate) {
	this.gold_item_market_rate = gold_item_market_rate;
}
public double getGold_item_lending_rate() {
	return gold_item_lending_rate;
}
public void setGold_item_lending_rate(double gold_item_lending_rate) {
	this.gold_item_lending_rate = gold_item_lending_rate;
}
}
