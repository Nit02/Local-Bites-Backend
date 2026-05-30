-- Seed data for Local Bites
-- Uses INSERT IGNORE so it won't fail on re-runs
INSERT IGNORE INTO foods (id, name, description, price, image_url) VALUES
(1, 'Margherita Pizza',    'Classic cheese pizza with tomato base',        199.00, 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400'),
(2, 'Veg Burger',          'Crispy veggie patty with fresh lettuce',         99.00, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400'),
(3, 'Chicken Biryani',     'Aromatic basmati rice with spices',             249.00, 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=400'),
(4, 'White Sauce Pasta',   'Creamy pasta with Italian herbs',               179.00, 'https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=400'),
(5, 'Chocolate Ice Cream', 'Rich chocolate scoop dessert',                   79.00, 'https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=400'),
(6, 'Mountain Momos',      'Steamed dumplings with spicy chutney',           80.00, 'https://images.unsplash.com/photo-1534422298391-e4f8c172dddb?w=400'),
(7, 'Himalayan Thukpa',    'Hearty noodle soup with veggies',               120.00, 'https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=400'),
(8, 'Fresh Pakoras',       'Crispy gram flour fritters',                     60.00, 'https://images.unsplash.com/photo-1601050690597-df0568f70950?w=400');
