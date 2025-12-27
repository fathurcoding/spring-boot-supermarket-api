package ui.ft.ccit.faculty.transaksi.transaksi.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;

public class TransaksiAlreadyExistsException extends DataAlreadyExistsException {
	public TransaksiAlreadyExistsException(String kode) {
		super("Transaksi", kode);
	}
}
