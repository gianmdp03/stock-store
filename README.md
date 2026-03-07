# 🛒 Stock-Store Backend API

A comprehensive E-commerce and Inventory Management system developed in **Spring Boot**. This RESTful API not only manages the internal product catalog and physical stock but also supports full online store operations: shopping carts, wishlists, payment gateway integration, supplier management, and an advanced security/role system.

---

# 🚀 Technologies and Tools

- **Core:** Java 25, Spring Boot 4.0.1  
- **Database:** MySQL (Spring Data JPA / Hibernate)  
- **Security:** Spring Security, OAuth2 Authorization Server, JJWT (0.12.6)  
- **Payment Gateway:** Mercado Pago SDK Java (2.8.0)  
- **Mailing:** Spring Boot Starter Mail (For password recovery and notifications)  
- **Data Mapping:** MapStruct 1.6.3 & Lombok  
- **Testing:** JUnit 5, Testcontainers (MySQL), Spring Boot Test  
- **Documentation:** SpringDoc OpenAPI 3.0.0 (Swagger UI)

---

# 🏗️ Advanced Architecture and Security

This project implements advanced software design and security patterns:

- **JWT via HttpOnly Cookies:** Unlike sending the token in the response body, the API configures a strict `accessToken` cookie (`HttpOnly`, `SameSite=Strict`), significantly mitigating XSS attacks on the frontend.
  
- **RBAC (Role-Based Access Control):** Multi-level system with distinct roles:
  - `USER`
  - `EMPLOYEE`
  - `ADMIN`

- **Domain Modules:** The code is strategically segmented into packages:
  - `security`
  - `online` (e-commerce)
  - `inventory` (suppliers and purchases)
  - `shared` (products and stock)

- **User State Management:** Support for banning users (`toggleBan`), recovering passwords via email codes, and promoting account roles.

---

# 📡 Main Endpoints (Postman Guide)

> Most listing endpoints support pagination via:
> ?page=0&size=10&sort=field,desc
---

## 🔐 1. Authentication and Users (`/api/auth`)

Full lifecycle management of users and security.

### Public Endpoints

- `POST /api/auth/register`  
  Register a new user (Returns the Auth cookie).

- `POST /api/auth/login`  
  Login (Creates the `accessToken` cookie).

- `POST /api/auth/forgot`  
  Request a password recovery code via Email.

- `POST /api/auth/verify/{code}`  
  Validate the temporary email code.

- `PATCH /api/auth/forgot/change`  
  Change password after recovering the account.

- `PATCH /api/auth/logged/password`  
  Change password while logged in.

- `DELETE /api/auth/logout`  
  Logout (Invalidates and deletes the HttpOnly cookie).

- `GET /api/auth/profile`  
  Get the currently logged-in user's profile.

### Admin Endpoints

- `GET /api/auth/admin`  
  List all users.

- `GET /api/auth/admin/banned`  
  List suspended users.

- `POST /api/auth/admin/promote/employee/{id}`  
  Promote a user to Employee.

- `PATCH /api/auth/admin/promote/admin/{id}`  
  Promote an employee to Admin.

- `POST /api/auth/admin/ban/{id}`  
  Ban/Unban a user.

---

## 💳 2. Payments / Mercado Pago (`/api/mp`)

Integration with the official SDK to generate payment intents.

- `POST /api/mp/single`  
  Creates a payment preference for direct purchases (one-click), skipping the cart.  
  Returns the `sandboxInitPoint` (Payment URL).

- `POST /api/mp/cart`  
  Converts the currently logged-in user's shopping cart into a Mercado Pago payment preference.

---

## 🛍️ 3. Online Orders (`/api/online-orders`)

Management of finalized customer purchases.

- `POST /api/online-orders/{saleDate}`  
  Registers a new online sale with its respective items (Automatically deducts stock).

- `GET /api/online-orders`  
  Paginated history of all orders.

- `GET /api/online-orders/id/{id}`  
  Full detail of a specific order.

- `GET /api/online-orders/{startDate}/{endDate}`  
  Sales report filtered by date range.

---

## 📦 4. Catalog and Store (`/api/products` & `/api/categories`) *(Shared)*

Endpoints to populate the e-commerce.

- Supports standard CRUD operations for **Products** and **Categories**.
- Products are linked to the **physical inventory module** to synchronize what is shown online with the actual warehouse reality.

---

## 🛒 5. Cart and Wishlist (`/api/cart` & `/api/wishlist`)

- Add/Remove items from the user's `CartItem`.
- Save products for later in the `Wishlist`.

---

## 🏭 6. Suppliers and Purchases (`/api/suppliers`) *(Inventory)*

Internal backoffice module for restocking.

- Management of the `Supplier` database.
- Creation of purchase orders (`SupplierOrder`) to enter new merchandise into the system.

---

# 🛠️ Environment Variables and Secure Configuration

To protect sensitive data (such as database passwords, JWT secrets, and API tokens), this project separates standard configuration from secrets.

⚠️ **Do NOT place sensitive data directly into `application.properties`.**

---

## Step 1 — Update `application.properties`

Ensure your `src/main/resources/application.properties` imports a secondary secrets file:

```properties
spring.config.import=optional:classpath:secrets.properties
```
## Step 2 — Create `secrets.properties`
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/stockstore_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

# JWT Security
application.security.jwt.secret-key=YOUR_LONG_BASE64_SECRET_KEY

# Mail Configuration (Spring Mail - e.g., Gmail SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password

# Mercado Pago Integration
mercadopago.access-token=YOUR_PROD_OR_TEST_ACCESS_TOKEN

## Step 3 — Gitignore 🛑
# Secrets
secrets.properties

## 🚀 How to Run Locally

1. Clone the repository.
2. Create your `secrets.properties` file as explained above.
3. Compile and run the application
