package ui.ft.ccit.faculty.transaksi.karyawan.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;

/**
 * Exception thrown when attempting to create a Karyawan that already exists.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class KaryawanAlreadyExistsException extends DataAlreadyExistsException {
	
	/**
	 * Constructs a new KaryawanAlreadyExistsException with the specified employee ID.
	 * 
	 * @param id the ID of the karyawan that already exists
	 */
	public KaryawanAlreadyExistsException(String id) {
		super("Karyawan", id);
	}
}
