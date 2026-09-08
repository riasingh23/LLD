Design an eBay-like marketplace where sellers list products and buyers purchase them.
The major emphasis was on:
Filtering and Searching products

**REQUIREMENTS**
- Create + Manage Listing
- Searching products with name
- Filter products on  based on attributes such as category, price, brand, and condition.
- Place Order
- handle post order journey

**ENTITIES & RELATIONSHIPS**
- Seller
- ListingService
- Product
- Listing
- Category - enum
- User
- FilterService
- FilterCriteria
- SearchService
- SearchStrategy
- CheckoutService
- Order
- OrderItem

Entities:
User, Seller, Product, Listing, Order, OrderItem

Value objects:
FilterCriteria, Address

Services:
ListingService, FilterService, SearchService, CheckoutService

Strategy:
SearchStrategy

**CLASS DESIGN**
Seller
- id


ListingService
- Map<Seller, List<Listing>>
+ addListing(Listing, Seller)
+ removeListing(Listing, Seller)
+ updateListing(Listing, Seller)
+ getListings()

Product
- id
- name
- Category
- Brand
- Size

Listing
- id
- Product product
- Seller seller
- inventory
- price 
- condition

Category
- Electronics,...

User
- AccountId
- 

FilterService
- ListingService
+ filter(FilterCriteria)


FilterCriteria
- category
- brand
- minPrice
- maxPrice
- condition

SearchService
- SearchStrategy
- ListingService
+ search(value) -> List<Listing>

SearchStrategy
+ search(value, List<Listing>)

SearchNameStrategy
+ search(value, List<Listing>)

CheckoutService
+ createOrder(User user, List<OrderItem> items, Address address)


Order
- orderid
- List<OrderItem> items
- Address
- status

OrderItem
- Listing
- Status
- quantity
- price