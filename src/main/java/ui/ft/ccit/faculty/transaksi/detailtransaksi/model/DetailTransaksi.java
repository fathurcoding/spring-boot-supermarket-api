package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import jakarta.persistence.*;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;

/**
 * Entity representing transaction details (detail_transaksi).
 * 
 * <p>This entity uses a composite key and has relationships with
 * Transaksi and Barang entities.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Entity
@Table(name = "detail_transaksi")
public class DetailTransaksi {
	
	@EmbeddedId
	private DetailTransaksiId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kode_transaksi", insertable = false, updatable = false)
	private Transaksi transaksi;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_barang", insertable = false, updatable = false)
	private Barang barang;
	
	@Column(name = "jumlah", nullable = false)
	private Short jumlah;
	
	public DetailTransaksi() {
	}
	
	public DetailTransaksi(DetailTransaksiId id, Transaksi transaksi, 
	                       Barang barang, Short jumlah) {
		this.id = id;
		this.transaksi = transaksi;
		this.barang = barang;
		this.jumlah = jumlah;
	}
	
	// Getters and Setters
	
	public DetailTransaksiId getId() {
		return id;
	}
	
	public void setId(DetailTransaksiId id) {
		this.id = id;
	}
	
	public Transaksi getTransaksi() {
		return transaksi;
	}
	
	public void setTransaksi(Transaksi transaksi) {
		this.transaksi = transaksi;
	}
	
	public Barang getBarang() {
		return barang;
	}
	
	public void setBarang(Barang barang) {
		this.barang = barang;
	}
	
	public Short getJumlah() {
		return jumlah;
	}
	
	public void setJumlah(Short jumlah) {
		this.jumlah = jumlah;
	}
}
