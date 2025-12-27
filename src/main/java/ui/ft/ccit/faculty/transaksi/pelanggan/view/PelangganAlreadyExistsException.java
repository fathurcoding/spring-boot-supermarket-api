package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;

public class PelangganAlreadyExistsException extends DataAlreadyExistsException {
	public PelangganAlreadyExistsException(String id) {
		super("Pelanggan", id);
	}
}
