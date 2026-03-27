# 🚗 Parking Management System (LLD)-

## 📌 Requirements

- Able to park the Vehicle (Car/Bike)
- Know at any moment number of available/blocked Slots
- Charge the vehicle on the basis of time spend in parking lot

## 🧩 Entities (Actors / Nouns)

### 🚙 Vehicle (Car, Bike)

- `registrationNumber` , `parkingTicket`

### 🏢 Parking Area

- `name`, `parkingId`, `address`, `List<ParkingSlot>`, `entryGate`, `exitGate`
- `numberOfEmptySlots`, `numberOfFilledSlots`

### 🎟️ Ticket

- `ticketId`, `timeStamp`, `vehicleType` (Enum: `CAR`, `BIKE`)

### 🅿️ Parking Slot

- `id`, `type` (Enum: `CAR`, `BIKE`), `isBlocked` (Boolean)

### 📍 Address

- `id`, `pincode`, `street1`, `street2`, `city`, `country`

### 🚪 Gate

#### Entry Gate

- `gateId`

#### Exit Gate

- `gateId`
- `collectParkingCharge(ticket, paymentSystem)`

### 👤 User

- `name`, `Address`

#### Customer

- `vehicle`

#### Admin

- `vehicle`, `parkingArea`
- `manageParkingArea()`

### Payment System

- `takePayment(Ticket ticket)`

---

## ⚙️ System Flow

- Iniate the parking place
    - Make parking slots object
    - Create Address
    - Create Gate - Exit Gate, Entry Gate
    - Making Parking Area object using Address. Adding Parking slots to Parking area
    - Create the payment system object
- Intiate Ticket object
- Create Users object
    - Create Vehicle object inside User object
- Whenever Parking is done
    - Update the parking place available and unavailable parking slots
- Exit Process
    - Vehicle exits through **Exit Gate**
    - Calculate parking duration
    - Generate parking charge
    - Call:
      ```java
      paymentSystem.takePayment(ticket);