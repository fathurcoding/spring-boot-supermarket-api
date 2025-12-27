package ui.ft.ccit.faculty.transaksi.karyawan.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import java.util.List;

/**
 * Service layer for Karyawan business logic.
 * 
 * <p>Provides CRUD operations for employee management with validation
 * and exception handling.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Service
public class KaryawanService {
	
	private final KaryawanRepository repository;
	
	/**
	 * Constructor with dependency injection.
	 * 
	 * @param repository the karyawan repository
	 */
	public KaryawanService(KaryawanRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Retrieves all employees.
	 * 
	 * @return list of all karyawan
	 */
	public List<Karyawan> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Retrieves an employee by ID.
	 * 
	 * @param id the employee ID
	 * @return the karyawan
	 * @throws KaryawanNotFoundException if karyawan not found
	 */
	public Karyawan findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new KaryawanNotFoundException(id));
	}
	
	/**
	 * Creates a new employee.
	 * 
	 * @param karyawan the employee to create
	 * @return the created karyawan
	 * @throws KaryawanAlreadyExistsException if ID already exists
	 */
	public Karyawan create(Karyawan karyawan) {
		if (repository.existsById(karyawan.getIdKaryawan())) {
			throw new KaryawanAlreadyExistsException(karyawan.getIdKaryawan());
		}
		return repository.save(karyawan);
	}
	
	/**
	 * Updates an existing employee.
	 * 
	 * @param id the employee ID to update
	 * @param karyawan the updated employee data
	 * @return the updated karyawan
	 * @throws KaryawanNotFoundException if karyawan not found
	 */
	public Karyawan update(String id, Karyawan karyawan) {
		findById(id); // Verify exists
		karyawan.setIdKaryawan(id);
		return repository.save(karyawan);
	}
	
	/**
	 * Deletes an employee by ID.
	 * 
	 * @param id the employee ID to delete
	 * @throws KaryawanNotFoundException if karyawan not found
	 */
	public void delete(String id) {
		findById(id); // Verify exists
		repository.deleteById(id);
	}
}
