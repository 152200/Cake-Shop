# 🍰 Cake Shop Ordering System

> 🚧 **Under Construction** – This project is actively being developed. Some features are complete, while others are still in progress. See the [Implementation Status](#-implementation-status) section for details.

Java console application that walks through the entire cake-ordering journey—from order placement to pickup—while demonstrating four classic design patterns described in `project_description.pdf`.

---

## 📑 Table of Contents

- [Implementation Status](#-implementation-status)
- [1. Project Goals](#1--project-goals)
- [2. Required Design Patterns](#2--required-design-patterns)
- [3. Completed Modules](#3--completed-modules)
  - [3.1 `cakes` Module](#31-cakes-module-)
  - [3.2 `decorators` Module](#32-decorators-module-)
  - [3.3 `factory` Module](#33-factory-module-)
  - [3.4 `ordering` Module](#34-ordering-module-)
  - [3.5 `observers` Module](#35-observers-module-)
  - [3.6 `demo` Module](#36-demo-module-)
- [4. Pending Modules](#4--pending-modules)
- [5. Build & Run Instructions](#5--build--run-instructions)
  - [5.1 Compile All Source Files](#51-compile-all-source-files)
  - [5.2 Complete System Example](#52-complete-system-example)
- [6. JSON Storage Flow](#6--json-storage-flow-pending)
- [7. Definition of Done for MVP](#7--definition-of-done-for-mvp)
- [8. Next Steps](#8--next-steps)
- [9. Code Quality Notes](#9--code-quality-notes)
- [10. Authors](#10--authors)

---

## 📊 Implementation Status

| Module       | Status          | Pattern           |
| ------------ | --------------- | ----------------- |
| `cakes`      | ✅ **Complete** | Base classes      |
| `decorators` | ✅ **Complete** | Decorator Pattern |
| `factory`    | ✅ **Complete** | Factory Pattern   |
| `ordering`   | ✅ **Complete** | Singleton Pattern |
| `observers`  | ✅ **Complete** | Observer Pattern  |
| `demo`       | ✅ **Complete** | Test runner       |
| `storage`    | ⏳ **Pending**  | JSON persistence  |

---

## 1. 🎯 Project Goals

1. Accept customer orders, build the requested cake, apply optional decorations, and hand off the finished cake for pickup.
2. Maintain exactly one connection to the central ordering processor to avoid race conditions or duplicated tickets.
3. Keep both the customer-facing dashboard (order number + cake name) and the manager dashboard (running totals per cake type) synchronized in real time.
4. Persist orders, customers, and dashboard state in JSON files that load at startup and save when the app exits.

---

## 2. 🧩 Required Design Patterns

1. **Factory** ✅ – Create `AppleCake`, `CheeseCake`, and `ChocolateCake` instances through a dedicated factory so higher layers stay decoupled from concrete classes.
2. **Decorator** ✅ – Allow customers to stack chocolate chips, cream, and skittles on any cake using nested decorators without altering the base cake implementation.
3. **Singleton** ✅ – Expose a single `CakeOrderingSystem` instance that routes every order to the central processor.
4. **Observer** ✅ – Notify both dashboards each time a cake is finalized so their displays always match the latest production state.

---

## 3. ✅ Completed Modules

### 3.1 `cakes` Module ✅

**Status**: Fully implemented with complete Javadoc documentation.

**Files**:

- `cakes/Cake.java` – Abstract base class with order ID, base name, size, and base price management
- `cakes/AppleCake.java` – Concrete apple cake implementation
- `cakes/CheeseCake.java` – Concrete cheese cake implementation
- `cakes/ChocolateCake.java` – Concrete chocolate cake implementation

**Features**:

- Abstract `Cake` class with `describe()` and `getCost()` methods
- Three concrete cake types ready for decoration
- Each cake maintains order ID, size, and base price
- Full Javadoc documentation for all classes and methods

---

### 3.2 `decorators` Module ✅

**Status**: Fully implemented with centralized logic and snapshot pattern.

**Files**:

- `decorators/CakeDecorator.java` – Abstract decorator base class with centralized grammar and cost logic
- `decorators/ChocolateChipsDecorator.java` – Adds chocolate chips (cost: $2.50)
- `decorators/CreamDecorator.java` – Adds cream (cost: $2.00)
- `decorators/SkittlesDecorator.java` – Adds skittles (cost: $1.50)

**Features**:

- ✅ **Centralized Grammar Logic** – Smart description handling:
  - 1st decoration: `"Cake with [Decoration]"`
  - 2nd decoration: `"Cake with X and [Decoration]"`
  - 3rd+ decorations: `"Cake with X, Y, and [Decoration]"` (Oxford comma style)
- ✅ **Snapshot Pattern** – Decoration cost/name captured at construction time
  - Existing objects preserve original prices even if static fields change
  - New objects use updated prices
- ✅ **Composable Decorators** – Decorators can be chained together
- ✅ **Centralized Cost Calculation** – All cost logic in base class
- ✅ **Configurable Prices** – Static setters/getters for future price changes

**Example Usage**:

```java
// Single decoration
Cake base = new ChocolateCake(1, "Large", 12.50);
Cake decorated = new CreamDecorator(base);
// Result: "Order #1: Chocolate Cake (Large) with Cream"
// Cost: 12.50 + 2.00 = 14.50

// Multiple decorations
Cake fullyDecorated = new ChocolateChipsDecorator(
    new SkittlesDecorator(
        new CreamDecorator(base)
    )
);
// Result: "Order #1: Chocolate Cake (Large) with Cream, Skittles, and Chocolate Chips"
// Cost: 12.50 + 2.00 + 1.50 + 2.50 = 18.50
```

---

### 3.3 `factory` Module ✅

**Status**: Fully implemented with automatic order ID generation and pricing management.

**Files**:

- `factory/CakeFactory.java` – Static factory class for creating cake instances

**Features**:

- ✅ **Static Factory Method** – `createCake(CakeType, CakeSize)` creates appropriate cake instances
- ✅ **Automatic Order ID Generation** – Format: `APP-L-001`, `CHE-M-002`, `CHO-S-003`
  - Type-specific counters maintain independent numbering per cake type
  - Format: `[3-letter type code]-[1-letter size code]-[3-digit sequential number]`
- ✅ **Pricing Table** – Embedded pricing data structure with default prices:
  - Apple Cake: Small $8.00, Medium $10.00, Large $12.00
  - Cheese Cake: Small $10.50, Medium $12.50, Large $15.00
  - Chocolate Cake: Small $10.50, Medium $12.50, Large $15.00
- ✅ **Price Management** – Public methods for querying and updating prices:
  - `getPrice(CakeType, CakeSize)` – Query current price
  - `setBasePrice(CakeType, CakeSize, double)` – Update price dynamically
  - `resetPricesToDefault()` – Restore original pricing
- ✅ **Type Counter Access** – `getCountForType(CakeType)` for dashboard statistics
- ✅ **Full Javadoc Documentation** – Complete documentation for all methods

**Example Usage**:

```java
// Create cakes using factory
Cake appleCake = CakeFactory.createCake(CakeType.APPLE, CakeSize.MEDIUM);
// Order ID: APP-M-001, Price: $10.00

Cake cheeseCake = CakeFactory.createCake(CakeType.CHEESE, CakeSize.LARGE);
// Order ID: CHE-L-001, Price: $15.00

// Query prices
double price = CakeFactory.getPrice(CakeType.CHOCOLATE, CakeSize.SMALL);
// Returns: 10.50

// Get count for manager dashboard
int count = CakeFactory.getCountForType(CakeType.APPLE);
// Returns: number of Apple cakes created (next order number)
```

---

### 3.4 `ordering` Module ✅

**Status**: Fully implemented with thread-safe singleton and observer integration.

**Files**:

- `ordering/CakeOrderingSystem.java` – Singleton ordering system orchestrating the entire order flow

**Features**:

- ✅ **Singleton Pattern** – Thread-safe double-checked locking implementation
  - Single instance accessible via `getInstance()`
  - Prevents multiple ordering system instances
- ✅ **Observer Registration** – Register/unregister observers for order completion notifications
  - `registerObserver(OrderObserver)` – Add observer to notification list
  - `removeObserver(OrderObserver)` – Remove observer from notifications
- ✅ **Order Placement** – `placeOrder()` method orchestrates the complete flow:
  1. Creates base cake using `CakeFactory`
  2. Applies decorations in sequence using Decorator pattern
  3. Notifies all registered observers
  4. Returns fully decorated cake
- ✅ **Decoration Application** – Maps `Decoration` enum to appropriate decorator classes
- ✅ **Integration** – Seamlessly integrates Factory, Decorator, and Observer patterns
- ✅ **Full Javadoc Documentation** – Complete documentation with usage examples

**Example Usage**:

```java
// Get singleton instance
CakeOrderingSystem system = CakeOrderingSystem.getInstance();

// Register dashboards
system.registerObserver(new CustomerDashboard());
system.registerObserver(new ManagerDashboard());

// Place an order with decorations
List<Decoration> decorations = Arrays.asList(
    Decoration.CREAM,
    Decoration.CHOCOLATE_CHIPS
);
Cake order = system.placeOrder(
    CakeType.CHOCOLATE,
    CakeSize.LARGE,
    decorations,
    "John Doe"
);
// Automatically creates cake, applies decorations, and notifies observers
```

---

### 3.5 `observers` Module ✅

**Status**: Fully implemented with Customer and Manager dashboard observers.

**Files**:

- `observers/OrderObserver.java` – Observer interface for order completion notifications
- `observers/CustomerDashboard.java` – Customer-facing dashboard displaying order details
- `observers/ManagerDashboard.java` – Manager dashboard displaying cake type statistics

**Features**:

- ✅ **Observer Interface** – `OrderObserver` defines the contract for order notifications
  - `update(Cake)` method receives completed cake objects
- ✅ **Customer Dashboard** – Displays order number and full cake description
  - Format: `"Order #APP-L-001: Apple Cake (Large) with Cream"`
  - Maintains list of all completed orders
  - Methods: `getCompletedOrders()`, `getOrderCount()`, `clear()`
- ✅ **Manager Dashboard** – Displays cake type with current count
  - Format: `"Cheese Cake – 123"`
  - Queries `CakeFactory` for type-specific counts
  - Shows only the latest sold cake type
- ✅ **Automatic Updates** – Both dashboards receive real-time notifications via Observer pattern
- ✅ **Full Javadoc Documentation** – Complete documentation for all classes

**Example Usage**:

```java
// Create dashboards
CustomerDashboard customerView = new CustomerDashboard();
ManagerDashboard managerView = new ManagerDashboard();

// Register with ordering system
CakeOrderingSystem system = CakeOrderingSystem.getInstance();
system.registerObserver(customerView);
system.registerObserver(managerView);

// When an order is placed, both dashboards automatically update:
// Customer Dashboard: "Order #CHO-M-001: Chocolate Cake (Medium) with Cream"
// Manager Dashboard: "Chocolate Cake – 1"
```

---

### 3.6 `demo` Module ✅

**Status**: Fully implemented with comprehensive end-to-end demonstration.

**Files**:

- `demo/CakeShopDemo.java` – Complete demonstration of all four design patterns working together

**Features**:

- ✅ **Complete Pattern Integration** – Demonstrates all four design patterns:
  - Factory Pattern: Cake creation via `CakeFactory`
  - Decorator Pattern: Applying decorations to cakes
  - Singleton Pattern: Single `CakeOrderingSystem` instance
  - Observer Pattern: Customer and Manager dashboards receiving notifications
- ✅ **Multiple Sample Orders** – Places 5 diverse orders showcasing:
  - Different cake types (Apple, Chocolate, Cheese)
  - Different sizes (Small, Medium, Large)
  - Various decoration combinations (single, multiple, none)
- ✅ **Real-Time Dashboard Updates** – Shows both dashboards updating automatically as orders are completed
- ✅ **Final Summary** – Displays total orders placed
- ✅ **Full Javadoc Documentation** – Complete documentation explaining the demo

**Example Output**:

The demo places orders and shows:

- Order processing details (customer name, cake type, size, decorations)
- Customer Dashboard updates (order number + full description)
- Manager Dashboard updates (cake type + count)
- Final summary with total orders

**Running the Demo**:

```bash
# Compile all modules
javac cakes/*.java decorators/*.java factory/*.java ordering/*.java observers/*.java demo/*.java

# Run the demo
java -cp . demo.CakeShopDemo
```

---

## 4. ⏳ Pending Modules

1. `storage` – JSON loader/saver utilities responsible for hydrating domain objects at startup and flushing changes on shutdown.

---

## 5. 🛠️ Build & Run Instructions

### 5.1 Compile All Source Files

From the project root directory:

```bash
# Compile all classes at once (recommended)
javac cakes/*.java decorators/*.java factory/*.java ordering/*.java observers/*.java demo/*.java

# Or compile modules individually
javac cakes/*.java
javac decorators/*.java
javac factory/*.java
javac ordering/*.java
javac observers/*.java
```

### 5.2 Run the Complete Demo

The project includes a comprehensive demo that demonstrates all four design patterns working together.

**Compile all modules**:

```bash
javac cakes/*.java decorators/*.java factory/*.java ordering/*.java observers/*.java demo/*.java
```

**Run the demo**:

```bash
java -cp . demo.CakeShopDemo
```

The demo will:

- Place 5 sample orders with different cake types, sizes, and decorations
- Show real-time updates on both Customer and Manager dashboards
- Display order processing details for each order
- Show a final summary with total orders placed

This demonstrates all four design patterns:

- **Factory Pattern**: Creating cakes with auto-generated order IDs
- **Decorator Pattern**: Applying multiple decorations in sequence
- **Singleton Pattern**: Ensuring a single ordering system instance
- **Observer Pattern**: Updating both dashboards automatically

---

## 6. 💾 JSON Storage Flow (Pending)

1. On program start, load JSON files (orders, customers, dashboard stats) into memory so the system resumes prior state automatically.
2. During runtime, all modifications go through domain services while keeping in-memory state authoritative.
3. On graceful shutdown, serialize the latest state back to the same JSON files to persist progress.

---

## 7. ✅ Definition of Done for MVP

| Requirement                            | Status       |
| -------------------------------------- | ------------ |
| ✅ Decorator pattern fully implemented | **Complete** |
| ✅ All three cake types implemented    | **Complete** |
| ✅ Factory pattern implemented         | **Complete** |
| ✅ Singleton ordering system           | **Complete** |
| ✅ Observer dashboards                 | **Complete** |
| ✅ End-to-end demo                     | **Complete** |
| ✅ Complete Javadoc documentation      | **Complete** |
| ⏳ JSON persistence                    | **Pending**  |

---

## 8. 🗓️ Next Steps

### Immediate Priority

1. ✅ **Decorator Pattern** – DONE! All decorators implemented with centralized logic
2. ✅ **Factory Pattern** – DONE! Factory creates cakes with auto-generated order IDs
3. ✅ **Singleton Pattern** – DONE! Thread-safe singleton ordering system implemented
4. ✅ **Observer Pattern** – DONE! Customer and Manager dashboards react to completed orders

### Remaining Tasks

1. ⏳ **JSON Persistence** – Wire up JSON storage helpers for data persistence
   - Load orders, customers, and dashboard state at startup
   - Save state on graceful shutdown

---

## 9. 📝 Code Quality Notes

### ✅ Completed

- **Javadoc**: All public classes and methods fully documented across all modules
- **Code Structure**: Clean package organization (`cakes`, `decorators`, `factory`, `ordering`, `observers`)
- **Pattern Implementation**: All four design patterns correctly implemented:
  - Factory Pattern: Centralized cake creation with automatic ID generation
  - Decorator Pattern: Composable decorations with centralized grammar logic
  - Singleton Pattern: Thread-safe singleton with double-checked locking
  - Observer Pattern: Real-time dashboard updates via observer notifications
- **Supporting Enums**: Type-safe enums for `CakeType`, `CakeSize`, and `Decoration`
- **Naming Conventions**: PascalCase for classes, camelCase for methods/fields

### 📋 TODOs in Code

- Decorator classes: Add constraints to setter methods (validation)
- Size constraints: Add validation for cake sizes
- JSON storage: Implement persistence layer for orders and dashboard state

---

## 10. 👥 Authors

- **Amer Abuyaqob**
- **Mustafa Abu Saffaqa**
- **Ahmad Badran**

---

**Last Updated**: Current implementation covers all four required design patterns (Factory, Decorator, Singleton, Observer) with comprehensive Javadoc documentation and a complete end-to-end demo. JSON persistence is the only remaining task.
