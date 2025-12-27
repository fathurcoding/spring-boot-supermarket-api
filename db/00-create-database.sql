-- ==========================================
-- Quick Database Setup Script
-- ==========================================
-- This script creates the database and grants privileges
-- Run this BEFORE importing db/penjualan.sql

-- Create database
CREATE DATABASE IF NOT EXISTS penjualan 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_general_ci;

-- Grant privileges to root user
GRANT ALL PRIVILEGES ON penjualan.* TO 'root'@'localhost';

-- Flush privileges
FLUSH PRIVILEGES;

-- Use the database
USE penjualan;

-- Confirm database is selected
SELECT DATABASE();
