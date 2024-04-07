package umkm;

import java.util.Scanner;

public class MasterBarangMenu {
    private Scanner scanner;
    public Barang[] daftarBarang;

    public MasterBarangMenu(Scanner scanner, Barang[] daftarBarang) {
        this.scanner = scanner;
        this.daftarBarang = daftarBarang;

        daftarBarang[0] = new JenisBarang1("Terong", 2000, 20, "Jenis B");
        daftarBarang[1] = new JenisBarang1("Manisa", 3000, 15, "Jenis A");
        daftarBarang[2] = new JenisBarang2("Wortel", 4000, 10, "Jenis A");
        daftarBarang[3] = new JenisBarang2("Selada", 1500, 25, "Jenis B");
        daftarBarang[4] = new JenisBarang2("Buncis", 2500, 10, "Jenis A");
    }

    // TAMBAH BARANG
    private void tambahBarang() {
        int jumlahBarang = 0;
        while (true) {
            System.out.print("Masukkan nama barang: ");
            String namaBarang = scanner.next();
            System.out.print("Masukkan harga barang: ");
            double hargaBarang = scanner.nextDouble();
            System.out.print("Masukan stok barang: ");
            int stokBarang = scanner.nextInt();
            System.out.println("Jenis A : Premium");
            System.out.println("Jenis B : Standar");
            System.out.print("Masukkan jenis barang (Jenis A atau Jenis B): ");
            String jenisBarang = scanner.next();

            // Buat objek Barang baru sesuai jenis
            Barang barangBaru;
            if (jenisBarang.equalsIgnoreCase("A")) {
                barangBaru = new JenisBarang1(namaBarang, hargaBarang, stokBarang, "Jenis A");
            } else if (jenisBarang.equalsIgnoreCase("B")) {
                barangBaru = new JenisBarang2(namaBarang, hargaBarang, stokBarang, "Jenis B");
            } else {
                System.out.println("Jenis barang tidak valid.");
                continue;
            }

            // Tambahkan objek Barang ke dalam Array
            while (jumlahBarang < daftarBarang.length) {
                if (daftarBarang[jumlahBarang] == null) {
                    daftarBarang[jumlahBarang] = barangBaru;
                    break;
                }
                jumlahBarang++;
            }
            if (jumlahBarang == daftarBarang.length) {
                System.out.println("Maaf, daftar barang sudah penuh!");
                return;
            }
            jumlahBarang++;
            System.out.println("Barang berhasil ditambahkan:");
            System.out.print("Apakah ingin menambah barang lagi? (ya/tidak): ");
            String jawaban = scanner.next();
            if (jawaban.equalsIgnoreCase("ya")) {
                continue; // Lanjutkan loop untuk menambahkan barang lagi
            } else if (jawaban.equalsIgnoreCase("tidak")) {
                break; // Keluar dari loop dan kembali ke menu utama
            }
        }
    }

    // UBAH BARANG
    private void ubahBarang() {
        boolean isDaftarBarangEmpty = true;
        System.out.println("\n===== Ubah Barang =====");
        for (int i = 0; i < daftarBarang.length; i++) {
            if (daftarBarang[i] != null) {
                System.out.println((i + 1) + ". " + daftarBarang[i]);
                isDaftarBarangEmpty = false;
            }
        }
        if (isDaftarBarangEmpty) {
            System.out.println("Belum ada barang yang ditambahkan, harap tambahkan barang terlebih dahulu!");
            return;
        }
        System.out.print("Pilih nomor barang yang ingin diubah: ");
        int nomorBarang = scanner.nextInt();
        if (nomorBarang < 1 || nomorBarang > daftarBarang.length || daftarBarang[nomorBarang - 1] == null) {
            System.out.println("Nomor barang tidak valid.");
            return;
        }
        Barang barangYangDiubah = daftarBarang[nomorBarang - 1];
        System.out.println("Barang yang dipilih: " + barangYangDiubah);
        System.out.print("Apakah anda yakin ingin merubah barang ini? (lanjut/batal): ");
        String konfirmasiUbah = scanner.next();
        if (konfirmasiUbah.equalsIgnoreCase("lanjut")) {
            System.out.print("Masukkan nama barang baru: ");
            String namaBarangBaru = scanner.next();
            System.out.print("Masukkan harga barang baru: ");
            double hargaBarangBaru = scanner.nextDouble();
            System.out.print("Masukkan stok barang baru: ");
            int stokBarangBaru = scanner.nextInt();
            barangYangDiubah.setNama(namaBarangBaru);
            barangYangDiubah.setHarga(hargaBarangBaru);
            barangYangDiubah.setStok(stokBarangBaru);
            System.out.println("Barang berhasil diubah:");
            System.out.println("Informasi barang setelah diubah: " + barangYangDiubah);
        } else {
            System.out.println("Perubahan barang dibatalkan.");
        }
    }

    // HAPUS BARANG
    private void hapusBarang() {
        System.out.println("\n===== Hapus Barang =====");
        System.out.println("Daftar Barang:");
        for (int i = 0; i < daftarBarang.length; i++) {
            if (daftarBarang[i] != null) {
                System.out.println((i + 1) + ". " + daftarBarang[i]);
            }
        }
        System.out.print("Pilih nomor barang yang ingin dihapus: ");
        int nomorBarangHapus = scanner.nextInt();
        if (nomorBarangHapus < 1 || nomorBarangHapus > daftarBarang.length
                || daftarBarang[nomorBarangHapus - 1] == null) {
            System.out.println("Nomor barang tidak valid.");
        }
        Barang barangYangDihapus = daftarBarang[nomorBarangHapus - 1];
        System.out.println("Barang yang dipilih: " + barangYangDihapus);
        System.out.print("Apakah Anda yakin ingin menghapus barang ini? (lanjut/batal): ");
        String konfirmasiHapus = scanner.next();
        if (konfirmasiHapus.equalsIgnoreCase("lanjut")) {
            // Melanjutkan proses penghapusan
            // Hapus barang dari array dan geser elemen-elemen setelahnya
            for (int i = nomorBarangHapus - 1; i < daftarBarang.length - 1; i++) {
                daftarBarang[i] = daftarBarang[i + 1];
            }
            daftarBarang[daftarBarang.length - 1] = null;
            System.out.println("Barang berhasil dihapus: " + barangYangDihapus);
        } else {
            System.out.println("Penghapusan barang dibatalkan.");
        }

    }

    // LIHAT DAFTAR BARANG
    private void lihatBarang() {
        System.out.println("\n===== Daftar Barang =====");
        boolean isDaftarBarangEmpty = true;
        for (int i = 0; i < daftarBarang.length; i++) {
            if (daftarBarang[i] != null) {
                System.out.println((i + 1) + ". " + daftarBarang[i]);
                isDaftarBarangEmpty = false;
            }
        }
        if (isDaftarBarangEmpty) {
            System.out.println("Daftar barang kosong.");
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n==== Master Barang ====");
            System.out.println("| 1. Tambah Barang    |");
            System.out.println("| 2. Ubah Barang      |");
            System.out.println("| 3. Hapus Barang     |");
            System.out.println("| 4. Lihat Barang     |");
            System.out.println("| 5. Kembali          |");
            System.out.println("=======================");
            System.out.print("Pilih (1-5): ");
            int pilihMenu = scanner.nextInt();
            switch (pilihMenu) {
                case 1:
                    tambahBarang();
                    break;

                case 2:
                    ubahBarang();
                    break;

                case 3:
                    hapusBarang();
                    break;

                case 4:
                    lihatBarang();
                    break;

                case 5:
                    return; // Kembali ke menu utama

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }
}
