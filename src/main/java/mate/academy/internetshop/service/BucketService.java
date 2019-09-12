package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Bucket;

public interface BucketService {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket deleteById(Long id);

    Bucket deleteByBucket(Bucket bucket);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List getAllItems(Long bucketId);
}
