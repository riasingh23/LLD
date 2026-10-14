package lru.cache;

/**
 * Hello world!
 *
 */
public class LruCacheMain
{
    public static void main( String[] args )
    {
        LruCache cache = new LruCache(2);
//        System.out.println(cache.get(0));
        System.out.println(cache.put(1,1000));
//        System.out.println(cache.get(0));
        System.out.println(cache.get(1));
        System.out.println(cache.put(2,2000));
        System.out.println(cache.get(2));
        System.out.println(cache.put(3,3000));
        System.out.println(cache.get(1));
    }
}
