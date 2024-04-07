package umkm;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaksiPenjualanMenu {
    private static int nomorTransaksiTerakhir = 1000;
    private Scanner scanner;
    private TransaksiPenjualan transaksiPenjualan;
    private Barang[] daftarBarang;
    private Barang[] belanjaan = new Barang[100]; // Menyimpan daftar barang yang ditambahkan ke dalam daftar belanja
    private int[] jumlahBelanjaan = new int[100]; // Menyimpan jumlah barang yang ditambahkan
    private int index = 0;

    public TransaksiPenjualanMenu(Scanner scanner, TransaksiPenjualan transaksiPenjualan, Barang[] daftarBarang) {
        this.scanner = scanner;
        this.transaksiPenjualan = new TransaksiPenjualan();
        this.daftarBarang = daftarBarang;
    }

    // TAMBAH BARANG
    private void tambahBarang() {
        int[] totalBiayaSementara = new int[100]; // Array untuk menyimpan total biaya sementara setiap kali menambah
        while (true) {
            System.out.println("\n===== Daftar Barang Yang Tersedia =====");
            for (int i = 0; i < daftarBarang.length; i++) {
                if (daftarBarang[i] != null) {
                    System.out.println(
                            (i + 1) + ". " + daftarBarang[i].getNama() + " - Harga: " + daftarBarang[i].getHarga());
                }
            }
            System.out.print("Masukkan nomor barang yang ingin dibeli (0 untuk selesai): ");
            int nomorBarang = scanner.nextInt();
            if (nomorBarang == 0) {
                break; // Keluar dari perulangan jika pengguna memilih 0
            }
            System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
            int jumlah = scanner.nextInt();
            // Memastikan nomor barang yang dimasukkan valid
            if (nomorBarang < 1 || nomorBarang > daftarBarang.length || daftarBarang[nomorBarang - 1] == null) {
                System.out.println("Nomor barang tidak valid.");
                continue; // Melanjutkan perulangan untuk meminta input yang valid
            }
            Barang barang = daftarBarang[nomorBarang - 1];
            int hargaBarang = (int) barang.getHarga();
            if (jumlah > barang.getStok()) { // Periksa apakah stok mencukupi
                System.out.println("Stok barang tidak mencukupi.");
                continue;
            }
            barang.setStok(barang.getStok() - jumlah); // Kurangi stok barang
            belanjaan[index] = barang;
            jumlahBelanjaan[index] = jumlah;
            totalBiayaSementara[index] = hargaBarang * jumlah; // Menyimpan total biaya sementara untuk penambahan
            index++; // Meningkatkan indeks untuk menyimpan barang berikutnya
            System.out.println("Barang berhasil ditambahkan ke daftar belanja.");
            System.out.println("Total Biaya Sementara: " + (hargaBarang * jumlah)); // Menampilkan total biaya sementara
        }

        // Menampilkan daftar belanja
        System.out.println("\n===== Daftar Belanja =====");
        int totalBiayaKeseluruhan = 0;
        for (int i = 0; i < index; i++) {
            Barang barang = belanjaan[i];
            int jumlah = jumlahBelanjaan[i];
            int totalBiayaBarang = (int) (barang.getHarga() * jumlah);
            totalBiayaKeseluruhan += totalBiayaBarang;
            System.out.println(
                    (i + 1) + ". " + barang.getNama() + " - Jumlah: " + jumlah + " - Total Harga: " + totalBiayaBarang);
        }
        System.out.println("Total Biaya Keseluruhan: " + totalBiayaKeseluruhan);
    }

    // HAPUS BARANG
    private void hapusBarang() {
        // Cek apakah daftar belanja kosong
        if (index == 0) {
            System.out.println("Daftar belanja kosong. Tidak ada barang yang dapat dihapus.");
            return;
        }
        System.out.println("\n===== Daftar Belanja =====");
        for (int i = 0; i < index; i++) {
            Barang barang = belanjaan[i];
            int jumlah = jumlahBelanjaan[i];
            // Menampilkan harga per unit barang
            System.out.println(
                    (i + 1) + ". " + barang.getNama() + " - Harga: " + barang.getHarga() + " - Jumlah: " + jumlah);
        }
        System.out.print("Masukkan nomor barang yang ingin dihapus: ");
        int nomorBarang = scanner.nextInt();

        // Cek apakah nomor barang valid
        if (nomorBarang < 1 || nomorBarang > index) {
            System.out.println("Nomor barang tidak valid.");
            return;
        }

        // Konfirmasi penghapusan barang
        Barang barangHapus = belanjaan[nomorBarang - 1];
        System.out.println("Anda akan menghapus " + barangHapus.getNama() + " dari daftar belanja.");

        // Lakukan penghapusan barang dari daftar belanjaan
        for (int i = nomorBarang - 1; i < index - 1; i++) {
            belanjaan[i] = belanjaan[i + 1];
            jumlahBelanjaan[i] = jumlahBelanjaan[i + 1];
        }
        belanjaan[index - 1] = null;
        jumlahBelanjaan[index - 1] = 0;
        index--;

        System.out.println("Barang berhasil dihapus dari daftar belanja.");
    }

    // PEMBAYARAN
    private void bayar(String namaPembeli) {
        // Lakukan pembayaran menggunakan transaksiPenjualan
        int nomorTransaksi = ++nomorTransaksiTerakhir;
        transaksiPenjualan.bayar(nomorTransaksi, namaPembeli);
        LocalDateTime waktuTransaksi = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedWaktu = waktuTransaksi.format(formatter);

        // Hitung total akhir dari daftar belanja
        int totalBiayaKeseluruhan = hitungTotalBiayaKeseluruhan();

        // Proses pembayaran
        System.out.println("Total biaya : " + totalBiayaKeseluruhan);
        System.out.print("Masukkan uang pembayaran: ");
        int uangPembayaran = scanner.nextInt();

        // Tampilkan struk pembayaran
        System.out.println("\n============= Kios Sayur Segar =============");
        System.out.println("|      Jl. Wonoasri No. 24, Kanigoro,      |");
        System.out.println("|       Kec. Kartoharjo Kota Madiun,       |");
        System.out.println("|             Jawa Timur 61254             |");
        System.out.println("============================================");
        System.out.printf("| Waktu Transaksi: %23s |\n", formattedWaktu);
        System.out.printf("| Nomor Transaksi: %23d |\n", nomorTransaksi);
        System.out.printf("| Nama Pembeli   : %23s |\n", namaPembeli);
        System.out.println("============================================");
        System.out.println("|   Nama   |  Harga  | Jumlah |  Subtotal  |");
        System.out.println("============================================");
        // Tampilkan detail pembelian dalam tabel
        for (int i = 0; i < index; i++) {
            Barang barang = belanjaan[i];
            int jumlah = jumlahBelanjaan[i];
            int harga = (int) barang.getHarga();
            int subtotal = harga * jumlah;
            System.out.printf("| %-8s | %7d | %6d | %10d |\n", barang.getNama(), harga, jumlah, subtotal);
        }
        System.out.println("============================================");
        // Tampilkan total biaya keseluruhan
        System.out.printf("| Total Biaya Keseluruhan:  %14d |\n", totalBiayaKeseluruhan);
        System.out.println("============================================");
        // Hitung kembalian
        int kembalian = uangPembayaran - totalBiayaKeseluruhan;
        System.out.printf("| Kembalian: %29d |\n", kembalian);
        System.out.println("============================================");
        System.out.println("|               Terima kasih               |");
        System.out.println("|   Telah berbelanja di Kios Sayur Segar   |");
        System.out.println("|          Silahkan datang kembali         |");
        System.out.println("============================================");
    }

    // Method untuk menghitung total biaya keseluruhan dari daftar belanja
    private int hitungTotalBiayaKeseluruhan() {
        int totalBiayaKeseluruhan = 0;
        for (int i = 0; i < index; i++) {
            Barang barang = belanjaan[i];
            int jumlah = jumlahBelanjaan[i];
            int totalBiayaBarang = (int) (barang.getHarga() * jumlah);
            totalBiayaKeseluruhan += totalBiayaBarang;
        }
        return totalBiayaKeseluruhan;
    }

    public void displayMenu() {
        scanner.nextLine();
        String namaPembeli;
        System.out.print("Masukkan nama pembeli: ");
        namaPembeli = scanner.nextLine();
        int pilihMenu;
        do {
            System.out.println("\n===== Submenu Transaksi Penjualan =====");
            System.out.println("| 1. Tambah Barang                    |");
            System.out.println("| 2. Hapus Barang                     |");
            System.out.println("| 3. Bayar                            |");
            System.out.println("| 4. Kembali                          |");
            System.out.println("=======================================");
            System.out.print("Pilih menu (1-4): ");
            pilihMenu = scanner.nextInt();
            switch (pilihMenu) {
                case 1:
                    tambahBarang();
                    break;
                    
                case 2:
                    hapusBarang();
                    break;
                    
                case 3:
                    bayar(namaPembeli);
                    break;

                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (pilihMenu != 4);
    }
}
