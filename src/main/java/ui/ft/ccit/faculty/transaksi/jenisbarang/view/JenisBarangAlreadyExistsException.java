package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

public class JenisBarangAlreadyExistsException extends RuntimeException {

    public JenisBarangAlreadyExistsException(String idJenisBarang) {
        super("Jenis Barang dengan id " + idJenisBarang + " sudah ada");
    }
}
