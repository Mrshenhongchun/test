package com.tedu.sp01.service;

import java.util.List;

import com.tedu.sp01.pojo.Item;

public interface ItemService {
	List<Item> getItems(String orderId);	//通过订单ip获取商品列表
	void decreaseNumbers(List<Item> list);	//减少商品库存
}
