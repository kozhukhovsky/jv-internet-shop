package me.kozhukhovsky.internetshop.service.impl;

import java.util.List;
import me.kozhukhovsky.internetshop.dao.BucketDao;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.lib.annotation.Service;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket deleteById(Long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.addItem(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItems().clear();
        return bucketDao.update(bucket);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.getAllItems(bucketId);
    }

    @Override
    public Bucket removeItemFromBucket(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.removeItem(item);
        return bucketDao.update(bucket);
    }
}
