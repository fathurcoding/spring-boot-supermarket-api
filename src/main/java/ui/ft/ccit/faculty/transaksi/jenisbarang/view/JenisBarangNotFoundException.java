package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

public class JenisBarangNotFoundException extends RuntimeException {

    public JenisBarangNotFoundException(String idJenisBarang) {
        super("Barang dengan id " + idJenisBarang + " tidak ditemukan");
    }
}
