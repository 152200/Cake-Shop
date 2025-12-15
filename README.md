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
- [4. Pending Modules](#4--pending-modules)
- [5. Build & Run Instructions](#5--build--run-instructions)
  - [5.1 Compile All Source Files](#51-compile-all-source-files)
  - [5.2 Quick Manual Test](#52-quick-manual-test)
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
| `factory`    | ⏳ **Pending**  | Factory Pattern   |
| `ordering`   | ⏳ **Pending**  | Singleton Pattern |
| `observers`  | ⏳ **Pending**  | Observer Pattern  |
| `storage`    | ⏳ **Pending**  | JSON persistence  |
| `demo`       | ⏳ **Pending**  | Test runner       |

---

## 1. 🎯 Project Goals

1. Accept customer orders, build the requested cake, apply optional decorations, and hand off the finished cake for pickup.
2. Maintain exactly one connection to the central ordering processor to avoid race conditions or duplicated tickets.
3. Keep both the customer-facing dashboard (order number + cake name) and the manager dashboard (running totals per cake type) synchronized in real time.
4. Persist orders, customers, and dashboard state in JSON files that load at startup and save when the app exits.

---

## 2. 🧩 Required Design Patterns

1. **Factory** ⏳ – Create `AppleCake`, `CheeseCake`, and `ChocolateCake` instances through a dedicated factory so higher layers stay decoupled from concrete classes.
2. **Decorator** ✅ – Allow customers to stack chocolate chips, cream, and skittles on any cake using nested decorators without altering the base cake implementation.
3. **Singleton** ⏳ – Expose a single `CakeOrderingSystem` instance that routes every order to the central processor.
4. **Observer** ⏳ – Notify both dashboards each time a cake is finalized so their displays always match the latest production state.

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

## 4. ⏳ Pending Modules

1. `factory` – Cake factory or factory method that maps an order request to a concrete cake.
2. `ordering` – Singleton order processor orchestrating cake creation, decoration, and notifications.
3. `observers` – Subject/observer interfaces with Customer and Manager dashboard implementations.
4. `storage` – JSON loader/saver utilities responsible for hydrating domain objects at startup and flushing changes on shutdown.
5. `demo` – Test-drive `main` method that places several orders to demonstrate every requirement.

---

## 5. 🛠️ Build & Run Instructions

### 5.1 Compile All Source Files

From the project root directory:

```bash
# Compile all cake classes
javac cakes/*.java

# Compile all decorator classes
javac decorators/*.java

# Compile all classes at once
javac cakes/*.java decorators/*.java
```

### 5.2 Quick Manual Test

Create a simple test class to verify decorators:

```java
import cakes.*;
import decorators.*;

public class QuickTest {
    public static void main(String[] args) {
        Cake cake = new ChocolateCake(1, "Large", 12.50);
        Cake decorated = new CreamDecorator(
            new SkittlesDecorator(cake)
        );

        System.out.println(decorated.describe());
        System.out.println("Cost: $" + decorated.getCost());
    }
}
```

Compile and run:

```bash
javac -cp . QuickTest.java
java -cp . QuickTest
```

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
| ✅ Comprehensive test suite            | **Complete** |
| ✅ Complete Javadoc documentation      | **Complete** |
| ⏳ Factory pattern implemented         | **Pending**  |
| ⏳ Singleton ordering system           | **Pending**  |
| ⏳ Observer dashboards                 | **Pending**  |
| ⏳ JSON persistence                    | **Pending**  |
| ⏳ End-to-end demo                     | **Pending**  |

---

## 8. 🗓️ Next Steps

### Immediate Priority

1. ✅ **Decorator Pattern** – DONE! All decorators implemented with centralized logic
2. ⏳ **Factory Pattern** – Create factory to instantiate cake types from strings/requests
3. ⏳ **Singleton Pattern** – Implement `CakeOrderingSystem` as singleton for order processing
4. ⏳ **Observer Pattern** – Build Customer and Manager dashboards that react to completed orders

### Future Tasks

1. Wire up JSON storage helpers for data persistence
2. Create end-to-end demo with multiple sample orders

---

## 9. 📝 Code Quality Notes

### ✅ Completed

- **Javadoc**: All public classes and methods fully documented
- **Code Structure**: Clean package organization (`cakes`, `decorators`)
- **Pattern Implementation**: Decorator pattern correctly implemented with composability
- **Naming Conventions**: PascalCase for classes, camelCase for methods/fields

### 📋 TODOs in Code

- `Cake.java`: Auto-generate order IDs (currently manual)
- Decorator classes: Add constraints to setter methods (validation)
- Size constraints: Add validation for cake sizes

---

## 10. 👥 Authors

- **Amer Abuyaqob**
- **Mustafa Abu Saffaqa**
- **Ahmad Badran**

---

**Last Updated**: Current implementation covers Decorator pattern with comprehensive tests. Factory, Singleton, and Observer patterns are next in the roadmap.
