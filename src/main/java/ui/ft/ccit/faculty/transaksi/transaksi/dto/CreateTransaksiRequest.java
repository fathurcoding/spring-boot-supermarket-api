package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import jakarta.validation.constraints.*;
<<<<<<< HEAD
import java.time.LocalDate;

public class CreateTransaksiRequest {
	
	@NotBlank(message = "Kode transaksi tidak boleh kosong")
	@Size(max = 4, message = "Kode transaksi maksimal 4 karakter")
	@Pattern(regexp = "J\\d{3}", message = "Kode transaksi harus format J###")
	private String kodeTransaksi;
	
	@NotNull(message = "Tanggal transaksi tidak boleh kosong")
	private LocalDate tglTransaksi;
	
	@NotBlank(message = "ID karyawan tidak boleh kosong")
	private String idKaryawan;
	
	@NotBlank(message = "ID pelanggan tidak boleh kosong")
	private String idPelanggan;
	
	public CreateTransaksiRequest() {}
	
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	
	public LocalDate getTglTransaksi() { return tglTransaksi; }
	public void setTglTransaksi(LocalDate tglTransaksi) { this.tglTransaksi = tglTransaksi; }
	
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
	
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
=======
import java.time.LocalDateTime;

public class CreateTransaksiRequest {
	@NotBlank(message = "Kode transaksi tidak boleh kosong")
	@Pattern(regexp = "J\\d{3}")
	private String kodeTransaksi;

	@NotNull
	private LocalDateTime tglTransaksi;

	@NotBlank
	private String idPelanggan;

	@NotBlank
	private String idKaryawan;

	// Getters/Setters
	public String getKodeTransaksi() { return kodeTransaksi; }
	public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
	public LocalDateTime getTglTransaksi() { return tglTransaksi; }
	public void setTglTransaksi(LocalDateTime tglTransaksi) { this.tglTransaksi = tglTransaksi; }
	public String getIdPelanggan() { return idPelanggan; }
	public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
	public String getIdKaryawan() { return idKaryawan; }
	public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
>>>>>>> feature/pagination-implementation
}
