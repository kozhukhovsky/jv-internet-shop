package mate.academy.internetshop.dao.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

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
    public void deleteById(Long id) {
        Storage.buckets.removeIf(bucket -> bucket.getId().equals(id));
    }

    @Override
    public void deleteByBucket(Bucket bucket) {
        Storage.buckets.removeIf(sourceBucket -> sourceBucket.getId().equals(bucket.getId()));
    }
}
