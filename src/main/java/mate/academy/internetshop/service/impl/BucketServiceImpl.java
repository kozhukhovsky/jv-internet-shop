package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.lib.annotation.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

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
        return bucketDao.addItem(bucketId, itemId);
    }

    @Override
    public Bucket clear(Long bucketId) {
        return bucketDao.clear(bucketId);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.getAllItems(bucketId);
    }

    @Override
    public Bucket removeItemFromBucket(Long bucketId, Long itemId) {
        return bucketDao.removeItemFromBucket(bucketId, itemId);
    }
}
