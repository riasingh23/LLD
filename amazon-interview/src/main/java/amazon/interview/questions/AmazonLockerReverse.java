package amazon.interview.questions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// =========================
// Location
// =========================

class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}


// =========================
// Package Status
// =========================

enum PackageStatus {
    PENDING,
    DROP_OFF,
    RETURN
}


// =========================
// Pricing Strategy
// =========================

interface PricingStrategy {
    double calculatePrice(Package pkg);
}


class FlatPricingStrategy implements PricingStrategy {

    private final double price;

    public FlatPricingStrategy(double price) {
        this.price = price;
    }

    @Override
    public double calculatePrice(Package pkg) {
        return price;
    }
}


// =========================
// Package Validator
// =========================

class PackageValidator {

    public boolean validatePackage(Package pkg) {
        // Add actual validation rules here.
        // For example: package dimensions, label, return eligibility, etc.
        return pkg != null;
    }
}


// =========================
// Slot
// =========================

class Slot {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final int capacity;
    private int occupiedCapacity;

    public Slot(
            LocalDateTime startTime,
            LocalDateTime endTime,
            int capacity) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.occupiedCapacity = 0;
    }

    public boolean isAvailable() {
        return occupiedCapacity < capacity;
    }

    public boolean reserve() {

        if (!isAvailable()) {
            return false;
        }

        occupiedCapacity++;
        return true;
    }

    public void release() {

        if (occupiedCapacity > 0) {
            occupiedCapacity--;
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedCapacity() {
        return occupiedCapacity;
    }
}


// =========================
// Package State
// =========================

interface PackageState {

    void drop(Package pkg);

    void cancel(Package pkg);
}


// =========================
// Pending State
// =========================

class PendingState implements PackageState {

    @Override
    public void drop(Package pkg) {

        pkg.setState(new DropOffState());

        System.out.println(
                "Package dropped successfully: "
                        + pkg.getId());
    }

    @Override
    public void cancel(Package pkg) {

        Slot slot = pkg.getSlot();

        if (slot != null) {
            slot.release();
        }

        pkg.setState(new ReturnState());

        System.out.println(
                "Package booking cancelled: "
                        + pkg.getId());
    }
}


// =========================
// Drop Off State
// =========================

class DropOffState implements PackageState {

    @Override
    public void drop(Package pkg) {

        System.out.println(
                "Package already dropped: "
                        + pkg.getId());
    }

    @Override
    public void cancel(Package pkg) {

        System.out.println(
                "Cannot cancel an already dropped package: "
                        + pkg.getId());
    }
}


// =========================
// Return State
// =========================

class ReturnState implements PackageState {

    @Override
    public void drop(Package pkg) {

        System.out.println(
                "Cannot drop a returned package: "
                        + pkg.getId());
    }

    @Override
    public void cancel(Package pkg) {

        System.out.println(
                "Package is already returned: "
                        + pkg.getId());
    }
}


// =========================
// Package
// =========================

class Package {

    private final String id;

    private Slot slot;

    private PackageState state;

    public Package(String id) {
        this.id = id;
        this.state = new PendingState();
    }

    public void drop() {
        state.drop(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void setState(PackageState state) {
        this.state = state;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public String getId() {
        return id;
    }

    public PackageState getState() {
        return state;
    }
}


// =========================
// Drop Store
// =========================

class DropStore {

    private final String id;

    private final Location location;

    private final List<Slot> slots;

    private final PricingStrategy pricingStrategy;

    public DropStore(
            String id,
            Location location,
            List<Slot> slots,
            PricingStrategy pricingStrategy) {

        this.id = id;
        this.location = location;
        this.slots = slots;
        this.pricingStrategy = pricingStrategy;
    }

    public List<Slot> getAvailableSlots() {

        List<Slot> availableSlots = new ArrayList<>();

        for (Slot slot : slots) {
            if (slot.isAvailable()) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }

    public Package bookSlot(
            Package pkg,
            Slot slot) {

        if (!slots.contains(slot)) {
            throw new IllegalArgumentException(
                    "Slot does not belong to this store");
        }

        if (!slot.reserve()) {
            throw new IllegalStateException(
                    "Slot is full");
        }

        pkg.setSlot(slot);

        return pkg;
    }

    public void cancelSlot(Package pkg) {

        pkg.cancel();
    }

    public double calculatePrice(Package pkg) {

        return pricingStrategy.calculatePrice(pkg);
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }
}


// =========================
// Drop Off System
// =========================

class DropOffSystem {

    private final List<DropStore> dropStores;

    private final PackageValidator packageValidator;

    public DropOffSystem(
            List<DropStore> dropStores,
            PackageValidator packageValidator) {

        this.dropStores = dropStores;
        this.packageValidator = packageValidator;
    }

    public DropStore findDropStore(Location location) {

        // Simplified for interview.
        // In real system, find nearest store using geo search.

        DropStore nearestStore = null;
        double minDistance = Double.MAX_VALUE;

        for (DropStore store : dropStores) {

            double distance =
                    calculateDistance(
                            location,
                            store.getLocation());

            if (distance < minDistance) {
                minDistance = distance;
                nearestStore = store;
            }
        }

        return nearestStore;
    }

    public Package bookSlotInDropStore(
            DropStore store,
            Slot slot,
            Package pkg) {

        return store.bookSlot(pkg, slot);
    }

    public void dropPackage(Package pkg) {

        boolean valid =
                packageValidator.validatePackage(pkg);

        if (!valid) {
            pkg.cancel();
            return;
        }

        pkg.drop();
    }

    public void cancelBooking(Package pkg) {

        pkg.cancel();
    }

    private double calculateDistance(
            Location a,
            Location b) {

        double dx =
                a.getLatitude() - b.getLatitude();

        double dy =
                a.getLongitude() - b.getLongitude();

        return Math.sqrt(dx * dx + dy * dy);
    }
}


// =========================
// Demo
// =========================

public class AmazonLockerReverse {

    public static void main(String[] args) {

        // -------------------------
        // Create slots
        // -------------------------

        Slot slot1 = new Slot(
                LocalDateTime.of(2026, 9, 7, 10, 0),
                LocalDateTime.of(2026, 9, 7, 11, 0),
                2);

        Slot slot2 = new Slot(
                LocalDateTime.of(2026, 9, 7, 11, 0),
                LocalDateTime.of(2026, 9, 7, 12, 0),
                2);

        List<Slot> slots = new ArrayList<>();

        slots.add(slot1);
        slots.add(slot2);


        // -------------------------
        // Pricing Strategy
        // -------------------------

        PricingStrategy pricingStrategy =
                new FlatPricingStrategy(50);


        // -------------------------
        // Create Drop Store
        // -------------------------

        DropStore store = new DropStore(
                "STORE_1",
                new Location(12.9716, 77.5946),
                slots,
                pricingStrategy);


        // -------------------------
        // Package Validator
        // -------------------------

        PackageValidator validator =
                new PackageValidator();


        // -------------------------
        // Drop Off System
        // -------------------------

        List<DropStore> stores =
                new ArrayList<>();

        stores.add(store);

        DropOffSystem system =
                new DropOffSystem(
                        stores,
                        validator);


        // -------------------------
        // Find Store
        // -------------------------

        DropStore nearestStore =
                system.findDropStore(
                        new Location(12.9700, 77.5900));


        // -------------------------
        // Create Package
        // -------------------------

        Package pkg =
                new Package("PKG_1");


        // -------------------------
        // Book Slot
        // -------------------------

        system.bookSlotInDropStore(
                nearestStore,
                slot1,
                pkg);


        System.out.println(
                "Occupied capacity: "
                        + slot1.getOccupiedCapacity());


        // -------------------------
        // Price
        // -------------------------

        double price =
                nearestStore.calculatePrice(pkg);

        System.out.println(
                "Price: " + price);


        // -------------------------
        // Drop Package
        // -------------------------

        system.dropPackage(pkg);


        // -------------------------
        // Try cancelling
        // -------------------------

        system.cancelBooking(pkg);
    }
}