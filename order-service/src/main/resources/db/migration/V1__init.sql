-- Create Orders Table if not exists
CREATE TABLE IF NOT EXISTS `t_orders`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `order_number` varchar(255) DEFAULT NULL,
    `sku_code`     varchar(255),
    `total_price`  decimal(19, 2),
    `quantity`     int(11),
    PRIMARY KEY (`id`)
);

-- Create Order Line Items Table if not exists
CREATE TABLE IF NOT EXISTS `t_order_line_items`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `price`    decimal(38, 2) DEFAULT NULL,
    `quantity` int            DEFAULT NULL,
    `sku_code` varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Create Orders to Order Line Items Mapping Table if not exists
CREATE TABLE IF NOT EXISTS `t_orders_order_line_items`
(
    `order_id`            bigint NOT NULL,
    `order_line_items_id` bigint NOT NULL,
    UNIQUE KEY `UK_dr0mag64ltmnuqo6c11iiln6o` (`order_line_items_id`),
    KEY                   `FKak6ywh7578tmaap0ru1vr85id` (`order_id`),
    CONSTRAINT `FKak6ywh7578tmaap0ru1vr85id` FOREIGN KEY (`order_id`) REFERENCES `t_orders` (`id`),
    CONSTRAINT `FKq6xv0tik4jv3vp7rgli0ea4ev` FOREIGN KEY (`order_line_items_id`) REFERENCES `t_order_line_items` (`id`)
);

-- Create Orders Sequence Table if not exists
CREATE TABLE IF NOT EXISTS `t_orders_seq`
(
    `next_val` bigint DEFAULT NULL
);

-- Initialize Orders Sequence Table
INSERT INTO `t_orders_seq` (`next_val`) VALUES (0) ON DUPLICATE KEY UPDATE `next_val`=`next_val`;