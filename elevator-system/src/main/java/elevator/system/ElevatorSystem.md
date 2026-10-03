Design an elevator control system for a building.
The system should handle multiple elevators, floor requests, and move elevators efficiently to service requests.

# Requirement
- people inside list should be able to select floor
- people should be able to press bottom outside the lift
- create a stimulator
- invalid floor through false


# Core Entities
- Elevator
- Request
- ElevatorSystem

# Class Design
Direction
- UP
- DOWN

Request
- requestType
- floor

Elevator
- Direction direction
- List<int> destination
- int currentFloor
+ step()
+ addDestination()
+ removeDestination()
+ setDirection()
+ getDirection()
+ setCurrentFloor()
+ getCurrentFloor()

ElevatorSystem
- List<Elevator>
+ requestElevator(Request)
+ step()