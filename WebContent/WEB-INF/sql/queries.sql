select * from purchase_materials order by purchase_material_id desc;
select * from purchase_orders order by purchase_order_id desc;
select * from vendor_materials where vendor_id = 100;
select * from materials;
select * from vendors;

update vendor_materials set price_per_qty = 10 where vendor_material_id = 1;
select * from users;
select * from addresses;-- where address_id = 130;

