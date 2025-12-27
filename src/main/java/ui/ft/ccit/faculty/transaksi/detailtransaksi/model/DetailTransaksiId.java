package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for DetailTransaksi entity.
 * 
 * <p>Combines kodeTransaksi and idBarang as a compound key.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Embeddable
public class DetailTransaksiId implements Serializable {
	
	@Column(name = "kode_transaksi", length = 4)
	private String kodeTransaksi;
	
	@Column(name = "id_barang", length = 4)
	private String idBarang;
	
	public DetailTransaksiId() {
	}
	
	public DetailTransaksiId(String kodeTransaksi, String idBarang) {
		this.kodeTransaksi = kodeTransaksi;
		this.idBarang = idBarang;
	}
	
	// Getters and Setters
	
	public String getKodeTransaksi() {
		return kodeTransaksi;
	}
	
	public void setKodeTransaksi(String kodeTransaksi) {
		this.kodeTransaksi = kodeTransaksi;
	}
	
	public String getIdBarang() {
		return idBarang;
	}
	
	public void setIdBarang(String idBarang) {
		this.idBarang = idBarang;
	}
	
	// equals and hashCode required for composite key
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DetailTransaksiId that = (DetailTransaksiId) o;
		return Objects.equals(kodeTransaksi, that.kodeTransaksi) &&
				Objects.equals(idBarang, that.idBarang);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(kodeTransaksi, idBarang);
	}
}
