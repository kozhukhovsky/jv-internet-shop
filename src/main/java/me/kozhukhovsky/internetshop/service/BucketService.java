package me.kozhukhovsky.internetshop.service;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Item;

public interface BucketService {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket deleteById(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

    Bucket removeItemFromBucket(Long bucketId, Long itemId);
}
