
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


-- Table for storing available time slots
CREATE TABLE time_slot (
    slot_id        INT PRIMARY KEY AUTO_INCREMENT,
    timing         VARCHAR(50) UNIQUE NOT NULL -- Example: "10:00 AM - 10:30 AM"
);

-- Table for storing customer details
CREATE TABLE customer (
    customer_id    INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100) NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    phone          VARCHAR(15) UNIQUE NOT NULL,
    password       VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL  -- Reserved for future use
);

-- Table for storing shop owner details
CREATE TABLE shop_owner (
    owner_id       INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100) NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    phone          VARCHAR(15) UNIQUE NOT NULL,
    password       VARCHAR(255) NOT NULL, -- Hashed password for security
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL  -- Reserved for future use
);

-- Table for storing shop details
CREATE TABLE shop (
    shop_id        INT PRIMARY KEY AUTO_INCREMENT,
    owner_id       INT NOT NULL,
    name           VARCHAR(100) NOT NULL,
    description    TEXT,
    contact_number VARCHAR(15) NOT NULL,
    category_id    INT,                    -- Foreign key to the category table
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status         ENUM('Active', 'Inactive', 'Closed') DEFAULT 'Active', -- Shop status
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    FOREIGN KEY (owner_id) REFERENCES shop_owner(owner_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

-- Table for storing images related to shops
CREATE TABLE shop_images (
    image_id INT PRIMARY KEY AUTO_INCREMENT,
    shop_id INT NOT NULL,
    image_url VARCHAR(100) NOT NULL,
    image_type ENUM('Logo', 'Interior', 'Exterior', 'Menu', 'Other') DEFAULT 'Other', -- Type of image
    uploaded_by INT, -- User ID who uploaded the image
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the image was uploaded
    is_primary BOOLEAN DEFAULT FALSE, -- Marks the primary image for the shop
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);

-- Table for storing features for shops
CREATE TABLE features (
    feature_id     INT PRIMARY KEY AUTO_INCREMENT,
    shop_id        INT NOT NULL,
    feature_name   VARCHAR(255) NOT NULL,
    description    TEXT, -- Description of the feature
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date when the feature was added
    is_active      BOOLEAN DEFAULT TRUE, -- Whether the feature is active
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);

-- Table for storing ratings and reviews for shops
CREATE TABLE ratings (
    rating_id      INT PRIMARY KEY AUTO_INCREMENT,
    shop_id        INT NOT NULL,
    customer_id    INT NOT NULL,
    rating         DECIMAL(2,1) CHECK (rating >= 1.0 AND rating <= 5.0),
    review         TEXT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- Table for storing location details of shops
CREATE TABLE shop_location (
    shop_id        INT PRIMARY KEY, 
    address        VARCHAR(255) NOT NULL,
    city           VARCHAR(100) NOT NULL,
    state          VARCHAR(100) NOT NULL,
    zip_code       VARCHAR(10) NOT NULL,
    latitude       DECIMAL(10,8), -- Optional for maps
    longitude      DECIMAL(11,8), -- Optional for maps
    country        VARCHAR(100), -- Country of the shop
    region         VARCHAR(100), -- Region or area
    operating_hours TEXT, -- Shop's working hours for better customer convenience
    is_open        BOOLEAN DEFAULT TRUE, -- Shop's open/close status
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);

-- Table for storing the availability of time slots for each shop on a given day
CREATE TABLE shop_time_slots (
    shop_id        INT NOT NULL,
    slot_id        INT NOT NULL,
    day_of_week    VARCHAR(10) NOT NULL,  -- 'Monday', 'Tuesday', etc.
    available      BOOLEAN DEFAULT TRUE,  -- Whether the slot is available
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Slot creation time
    future_col_1   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_2   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_3   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_4   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    future_col_5   VARCHAR(255) DEFAULT NULL, -- Reserved for future use
    PRIMARY KEY (shop_id, slot_id, day_of_week),
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE,
    FOREIGN KEY (slot_id) REFERENCES time_slot(slot_id) ON DELETE CASCADE
);

CREATE TABLE shop_services (
    service_id       INT PRIMARY KEY AUTO_INCREMENT,
    shop_id          INT NOT NULL,
    service_name     VARCHAR(255) NOT NULL,
    service_description TEXT,
    price            DECIMAL(10,2) NOT NULL, -- Price of the service
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (shop_id) REFERENCES shop(shop_id) ON DELETE CASCADE
);
