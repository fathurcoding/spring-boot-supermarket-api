package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Karyawan entity.
 * 
 * <p>
 * Provides CRUD operations for employee (karyawan) data through
 * Spring Data JPA.
 * </p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public interface KaryawanRepository extends JpaRepository<Karyawan, String> {

    /**
     * Finds employees whose name contains the given string (case-insensitive).
     * 
     * @param nama     the name fragment to search for
     * @param pageable pagination information
     * @return a page of matching employees
     */
    Page<Karyawan> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
