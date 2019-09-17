package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Bucket;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket deleteById(Long id);

    Bucket deleteByBucket(Bucket bucket);
}
