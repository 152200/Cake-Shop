# 🍰 Cake Shop Ordering System

Java console application that walks through the entire cake-ordering journey—from order placement to pickup—while demonstrating four classic design patterns described in `project_description.pdf`.

## 1. 🎯 Project Goals

1. Accept customer orders, build the requested cake, apply optional decorations, and hand off the finished cake for pickup.
2. Maintain exactly one connection to the central ordering processor to avoid race conditions or duplicated tickets.
3. Keep both the customer-facing dashboard (order number + cake name) and the manager dashboard (running totals per cake type) synchronized in real time.
4. Persist orders, customers, and dashboard state in JSON files that load at startup and save when the app exits.

## 2. 🧩 Required Design Patterns

1. **Factory** – Create `AppleCake`, `CheeseCake`, and `ChocolateCake` instances through a dedicated factory so higher layers stay decoupled from concrete classes.
2. **Decorator** – Allow customers to stack chocolate chips, cream, and skittles on any cake using nested decorators without altering the base cake implementation.
3. **Singleton** – Expose a single `CakeOrderingSystem` instance that routes every order to the central processor.
4. **Observer** – Notify both dashboards each time a cake is finalized so their displays always match the latest production state.

## 3. 🧱 Planned Modules

1. `cakes` – Base `Cake` interface plus concrete cake types with default descriptions/prices.
2. `decorators` – Abstract decorator + concrete embellishments (chips, cream, skittles) that wrap any `Cake`.
3. `factory` – Cake factory or factory method that maps an order request to a concrete cake.
4. `ordering` – Singleton order processor orchestrating cake creation, decoration, and notifications.
5. `observers` – Subject/observer interfaces with Customer and Manager dashboard implementations.
6. `storage` – JSON loader/saver utilities responsible for hydrating domain objects at startup and flushing changes on shutdown.
7. `demo` – Test-drive `main` method that places several orders to demonstrate every requirement.

## 4. 💾 JSON Storage Flow

1. On program start, load JSON files (orders, customers, dashboard stats) into memory so the system resumes prior state automatically.
2. During runtime, all modifications go through domain services while keeping in-memory state authoritative.
3. On graceful shutdown, serialize the latest state back to the same JSON files to persist progress.

## 5. ✅ Definition of Done for MVP

1. Command-line demo places multiple mixed orders and prints dashboard updates after each completion.
2. All four patterns are exercised and easy to trace in code comments/Javadoc.
3. JSON state auto-loads on startup and is written back on exit without manual steps.
4. README stays updated with build/run instructions and example outputs.
5. `.gitignore` prevents IDE/build clutter plus `commands/` and `.cursorrules` from entering version control.

## 6. 🗓️ Discussion & Next Steps

1. Implement the Factory + Decorator layers first to unlock end-to-end cake customization.
2. Wrap the ordering workflow inside the Singleton and connect observer dashboards.
3. Wire up the JSON storage helpers so data hydrates on launch and flushes on shutdown.
4. Capture console screenshots/logs for the discussion meeting before **16/12/2025**.






Cake => Decorator => Factory => Singleton => Observer