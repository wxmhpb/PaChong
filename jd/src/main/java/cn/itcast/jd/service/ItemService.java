package cn.itcast.jd.service;

import cn.itcast.jd.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    //保存商品
   void  save(Item item);

   //根据条件查询商品
    List<Item> findAll(Item item);
}
