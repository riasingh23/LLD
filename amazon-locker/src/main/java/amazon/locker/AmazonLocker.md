**REQUIREMENTS**
1. Match the parcel size to the same size compartment and store.
   - Generate Code and return when the parcel is locked in.
   - if not no compartment is found return false or error.
2. Give the parcel to customer.
   - Validate code given by customer.
   - if not correct or expired or already used through error.
3. code expiry in 7 days
   - staff removes them all at once
4. No multiple locker stations

Out of scope:
- How the package gets to the locker (delivery logistics)
- How the access token reaches the customer (SMS/email notification)
- Lockout after failed access token attempts
- UI/rendering layer
- Multiple locker stations
- Payment or pricing

**ENTITIES & RELATIONSHIPS**

Locker, Compartment, CompartmentSize, AuthToken

- Locker → list of Compartment
- Locker → AuthToken
- AuthToken → compartment


**CLASS DESIGN**
Locker
- compartments:List
- codeAccessTokenMapping: Map<String,AccessToken>

+ Locker(compartments)
+ generateCodeAndPutPackage(CompartmentSize size) → String
+ validateCodeAndRemovePackage(String code) → String
+ openExpiredCompartment() → void


Compartment
- id:String
- isOccupied:Bool
- size:CompartmentSize

+ getId() -> String
+ isOccupied() → Bool
+ getSize() → int
+ putPackage() → Bool
+ removePackage() → Bool


CompartmentSize
- SMALL, MEDIUM, LARGE

AuthToken
- code:String
- creationTime:long
- compartment:Compartment

+ AuthToken(code)
+ getCompartment() → Compartment
+ isExpired() → bool
+ getCode() → String