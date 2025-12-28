package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jenis_barang")
public class JenisBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jenis_brg")
    private Integer idJenisBarang;

    @Column(name = "nama_jenis_brg", length = 255)
    private String namaJenis;

    public JenisBarang() {
    }

    public JenisBarang(Integer idJenisBarang, String namaJenis) {
        this.idJenisBarang = idJenisBarang;
        this.namaJenis = namaJenis;
    }

    public Integer getIdJenisBarang() {
        return idJenisBarang;
    }

    public void setIdJenisBarang(Integer idJenisBarang) {
        this.idJenisBarang = idJenisBarang;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }
}
