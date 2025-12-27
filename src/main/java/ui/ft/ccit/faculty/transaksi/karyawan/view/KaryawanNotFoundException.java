package ui.ft.ccit.faculty.transaksi.karyawan.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;

/**
 * Exception thrown when a Karyawan is not found in the database.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class KaryawanNotFoundException extends DataNotFoundException {
	
	/**
	 * Constructs a new KaryawanNotFoundException with the specified employee ID.
	 * 
	 * @param id the ID of the karyawan that was not found
	 */
	public KaryawanNotFoundException(String id) {
		super("Karyawan", id);
	}
}
