SET SCHEMA 'p0';

INSERT INTO products(
    id,
    name,
    category,
    price
) values (
    gen_random_uuid(),
    "Gorilla Arms",
    "electronics",
    15200, 
    "These arm implants drastically increase damage in melee combat. With every melee Attack performed, charges are built up to deal increased damage with the next Strong Attack."
), (
    gen_random_uuid(),
    "Rayfield Caliburn",
    "automotive",
    127000,
    "Top Speed 211 MPH. Features a CrystalDome windshield in which it provides privacy for the passengers when being viewed from the exterior."
), (
    gen_random_uuid(),
    "NiCola Blue",
    "food",
    4.99,
    "Applies the Hydration status, increasing max Stamina by 10% and Stamina regen by 50%. The effect lasts 450 seconds."
), (
    gen_random_uuid(),
    "QianT Warp Dancer Sandevistan Mk.5",
    "electronics",
    3499.99, 
    "This cyberware implant will allow the use of the Sandevistan ability for 8 seconds on a 30 second cooldown, slowing down time to 10%. While Sandevistan is active, it will increase all damage by 15%, Crit Chance by 10%, and Crit Damage by 50%."
), (
    gen_random_uuid(),
    "Cyberpsychosis",
    "software",
    1399.99
);