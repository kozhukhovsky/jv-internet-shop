package me.kozhukhovsky.internetshop.dao;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Item;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket deleteById(Long id);

    List<Item> getAllItems(Long bucketId);
}
