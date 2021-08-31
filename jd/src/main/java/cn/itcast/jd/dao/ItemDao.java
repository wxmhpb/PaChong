package cn.itcast.jd.dao;

import cn.itcast.jd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDao extends JpaRepository<Item,Long> {
    List<Item> findAll(Item item);
}
