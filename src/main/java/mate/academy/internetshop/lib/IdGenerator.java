package mate.academy.internetshop.lib;

public class IdGenerator {
    private static long nextBucketId;
    private static long nextItemId;
    private static long nextOrderId;
    private static long nextUserId;
    private static long nextRoleId;

    private IdGenerator() {
    }

    public static long getNextBucketId() {
        return nextBucketId++;
    }

    public static long getNextItemId() {
        return nextItemId++;
    }

    public static long getNextOrderId() {
        return nextOrderId++;
    }

    public static long getNextUserId() {
        return nextUserId++;
    }

    public static long getNextRoleId() {
        return nextRoleId;
    }
}
