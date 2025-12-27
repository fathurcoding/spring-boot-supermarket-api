package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Karyawan entity.
 * 
 * <p>Provides CRUD operations for employee (karyawan) data through
 * Spring Data JPA.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
}
