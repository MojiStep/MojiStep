-- V1__bootstrap.sql
-- Seed roles and permissions
INSERT INTO permissions (id, code, name) VALUES (gen_random_uuid(), 'LESSON_WRITE', 'Create/Update/Delete Lessons')
ON CONFLICT DO NOTHING;

INSERT INTO roles (id, code, name) VALUES (gen_random_uuid(), 'ROLE_USER', 'Standard User')
ON CONFLICT DO NOTHING;

