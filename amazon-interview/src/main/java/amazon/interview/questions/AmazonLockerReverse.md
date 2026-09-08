****REQUIREMENTS**
- customer finds a nearby drop store
- books a time slot
  - store has limited capacity per time slot

[//]: # (  - Size of package matters)
  - if not store available return proper response
  - Cancel the slot.
- physically drops a package
  - validates the package
  - accepts it or reject it
  - updates the return/drop-off status
- pricing strategy

**ENTITIES & RELATIONSHIPS**
- DropOffSystem
- DropStore
- Slot
- Package
- PackageValidator
- PackageState
- PriceStrategy

**CLASS DESIGN**
DropOffSystem
- List<DropStore>
- PackageValidator
+ findDropStore(Location) -> proper response if slot not available, DropStore
+ bookSlotInDropStore(DropStore, Slot, Package) -> Package
+ dropPackage(Package)
+ cancelBooking(Package)

DropStore
- Location
- id
- List<Slot> slots
- PricingStrategy
+ getAvialableSlots()
+ bookSlot(Package, Slot) -> package
+ cancelSLot(Package)
+ price(Package)

PackageValidator
+ validatePackage(Package) -> boolean

Package
- Slot
- PackageState state
+ setState(PackageState)
+ drop()
+ cancel()

PackageState
+ drop(Package)
+ cancel(Package)

PendingState
+ drop(Package)
+ cancel(Package)

DropOffState
+ drop(Package)
+ cancel(Package)

ReturnState
+ drop(Package)
+ cancel(Package)

Slot
- startTime
- endTime
- Capacity
- occupiedCapacity
+ release()
+ reserve()


I'm using Strategy Pattern for pricing because pricing algorithms can vary independently. 
I'm using State Pattern for the package lifecycle because package behavior depends on its current state. 
I'm also using dependency injection for PackageValidator and PricingStrategy to keep the classes loosely coupled and testable.**


