package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        return Storage.buckets
            .stream()
            .filter(bucket -> bucket.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id " + id));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.set(i, bucket);
                return bucket;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Bucket deleteById(Long id) {
        Bucket deletedBucket = get(id);
        Storage.buckets.removeIf(bucket -> bucket.getId().equals(id));
        return deletedBucket;
    }

    public Bucket addItem(Long bucketId, Long itemId) {
        return null;
    }

    public Bucket clear(Long bucketId) {
        return null;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return null;
    }

    public Bucket removeItemFromBucket(Long bucketId, Long itemId) {
        return null;
    }
}
