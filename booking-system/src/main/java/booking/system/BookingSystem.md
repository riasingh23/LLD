# REQUIREMENTS
A movie ticket booking system (like Fandango) lets users search for movies, browse theaters and showtimes, select specific seats from a seat map, and reserve tickets.
The system manages seat availability across multiple theaters, each with multiple screens, and prevents two people from booking the same seat.

- users can search for movies by title using simple substring matching.
    - no match show empty
- browse theaters and showtimes
- Book Ticket by selecting seat
    - through error if the ticket is booked.
- prevent people from booking the same seat.
    - each theater has rows A through Z, with seats numbered 0 through 20.
- Users can cancel an existing reservation using its confirmation ID.
    - cant cancel already cancelled ticket

# ENTITIES & RELATIONSHIPS
BookingSystem
User
Show
Movie
Theater
Ticket
Booking

BookingSystem -> Theaters
Show->movies
Theater->Shows
Booking->Ticket
User->Booking

# CLASS DESIGN
BookingSystem
-List<Theater>
+search(text) -> shows,theater
+book(ticket, Shows) -> reservationId
+addTheater(Theater) ->
+removeTheater(Theater)
+cancelTicket(reservationId)

User
-ID
-name
-preference

Movie
-name
-movieId
-rating
-genre
-List<Show>

Show
-movie
-theater
-timeStamp
-List<ticket>
+ showAvaialeTicket()
+ book(Ticket)


Theater
-theaterId
-List<Shows>


Ticket
-ticketId
-row
-Column
-isReserved
+markReserve()
+markUnrevered()


Booking
-confirmationId
-List<ticket>
-showId
-Userid