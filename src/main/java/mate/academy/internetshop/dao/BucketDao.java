package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket deleteById(Long id);

    List<Item> getAllItems(Long bucketId);
}
