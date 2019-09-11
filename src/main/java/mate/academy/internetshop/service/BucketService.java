package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;

public interface BucketService {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void deleteById(Long id);

    void deleteByBucket(Bucket bucket);
}
