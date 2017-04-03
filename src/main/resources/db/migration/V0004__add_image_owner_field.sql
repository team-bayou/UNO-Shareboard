ALTER TABLE images ADD COLUMN owner integer NOT NULL REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE ad_image_xref DROP COLUMN ad_id;
ALTER TABLE ad_image_xref DROP COLUMN image_id;
ALTER TABLE ad_image_xref ADD COLUMN ad_id integer REFERENCES ads(ad_id) ON DELETE CASCADE;
ALTER TABLE ad_image_xref ADD COLUMN image_id integer REFERENCES images(image_id) ON DELETE CASCADE;
