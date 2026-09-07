### QnA
- Assume the cache capacity is fixed and provided when the cache is initialized. It does not change at runtime.
- For simplicity, assume both keys and values are strings. You can make the design generic later if needed.
- The cache should support the following operations: get(key) to retrieve a value, put(key, value) to insert or update a value, and automatically evict the least recently used item when the cache reaches its capacity.
- If the key is not present in the cache, get(key) should return null (or an equivalent indication of a cache miss).

### Requirement
- Client should be able to get(key)
  - if the key is not present then provide null
  - if key is not present return null
- New key to be attach in front and removed from back
- During intialisation the size of cache is finalised 
- if size is full remove the elements from last and push the new key in front
- it should be thread safe

### Core Entities
- Pair
- Cache
- 