package id.seigan.dojo;

import java.io.InvalidClassException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        ObjectSaver.fileName = "D:/Bootcamp/programming-temp/account_manager/account.txt";

        Scanner masuk = new Scanner(System.in);
        int pilih;
        String namaAkun;
        do {
            System.out.println("Menu operasi");
            System.out.println("1. Tampil daftar akun \n2. Tampil akun\n3. tambah akun\n4. Ubah password\n5. Riwayat perubahan password\n6. Hapus akun\n0. Keluar");
            System.out.printf("Masukkan pilihan : ");
            pilih = masuk.nextInt();
            switch (pilih){
                case 1 :
                    int i = 1;
                    Object file = ObjectSaver.retrieveObject();
                    System.out.println("Daftar akun: ");
                    System.out.println("------------");
                    if(file instanceof List){
                        List<Account> accounts = (List<Account>)file;
                        for(Account acc: accounts){
                            System.out.println(i + ". " + acc.getAccountName());
                            i++;
                        }
                    } else{
                        System.out.println("TIdak ada akun dalam list");
                    }
                    break;

                case 2 :
                    masuk.nextLine();
                    Object file2 = ObjectSaver.retrieveObject();
                    boolean cek = false;
                    System.out.print("Masukkan nama akun yang ingin dicari : ");
                    namaAkun = masuk.nextLine();
                    if(file2 instanceof List){
                        List<Account> accounts = (List<Account>)file2;
                        for(Account acc: accounts){
                            if(namaAkun.equals(acc.getAccountName())){
                                System.out.println("Akun ditemukan dengan detail : ");
                                System.out.println("Nama akun : " + acc.getAccountName());
                                System.out.println("Url akun  : " + acc.getSiginUrl());
                                System.out.println("Username  : " + acc.getUsername());
                                System.out.println("Password  : " + dekripVignere(acc.getPassword(), "pass"));
                                cek = true;
                            }
                        }
                    }
                    if(!cek){
                        System.out.println("Akun tidak ditemukan!");
                    }

                    break;

                case 3 :
                    Object filecek = null;
                    try {
                        masuk.nextLine();
                        filecek = ObjectSaver.retrieveObject();
                        List<Account> accountList = (List<Account>)filecek;
                        addAccount(accountList);
                    } catch (RuntimeException e) {
                        if (e.getCause() instanceof InvalidClassException) {
                            System.out.println("Telah dibuat file baru!");
                            List<Account> accountList = new ArrayList<>();
                            addAccount(accountList);
                        } else {
                            throw e;
                        }
                    }
                    break;

                case 4 :
                    Object file4 = ObjectSaver.retrieveObject();
                    masuk.nextLine();
                    boolean cekUbah = false;
                    System.out.println("Masukkan nama akun yang ingin diubah passwordnya : ");
                    namaAkun = masuk.nextLine();
                    if(file4 instanceof List){
                        List<Account> accounts = (List<Account>)file4;
                        for(Account acc: accounts){
                            if(namaAkun.equals(acc.getAccountName())){
                                System.out.println("Akun ditemukan!");
                                System.out.print("Password baru : ");
                                String pass = masuk.nextLine();
                                String password = enkripVignere(pass, "pass");
                                HashMap<String, String> Password = acc.getPasswordHistory();
                                String pattern = "MM/dd/yyyy HH:mm:ss";
                                DateFormat df = new SimpleDateFormat(pattern);
                                Date waktu = new Date();
                                String waktuUbah = df.format(waktu);
                                Password.put(waktuUbah, password);
                                acc.setPassword(password, Password);
                                cekUbah = true;
                            }
                        }
                        ObjectSaver.saveObject(accounts);
                    }
                    if(!cekUbah){
                        System.out.println("Akun tidak ditemukan!");
                    }
                    break;

                case 5 :
                    Object file5 = ObjectSaver.retrieveObject();
                    masuk.nextLine();
                    boolean cekPass = false;
                    System.out.println("Masukkan nama akun untuk melihat histori password : ");
                    namaAkun = masuk.nextLine();
                    if(file5 instanceof List){
                        List<Account> accounts = (List<Account>)file5;
                        for(Account acc: accounts){
                            if(namaAkun.equals(acc.getAccountName())){
                                System.out.println("Akun ditemukan!");
                                System.out.println("Histori perubahan password : ");
                                acc.passwordHistory();
                                cekPass = true;
                            }
                        }
                    }
                    if(!cekPass){
                        System.out.println("Akun tidak ditemukan!");
                    }
                    break;

                case 6 :
                    Object file6 = ObjectSaver.retrieveObject();
                    masuk.nextLine();
                    int h = 0, x = 0;
                    boolean cekHapus = false, hapus = false;
                    System.out.println("Masukkan nama akun yang ingin dihapus : ");
                    namaAkun = masuk.nextLine();
                    if(file6 instanceof List){
                        List<Account> accounts = (List<Account>)file6;
                        for(Account acc: accounts){
                            if(namaAkun.equals(acc.getAccountName())){
                                System.out.println("Akun ditemukan!");
                                System.out.print("Akun berhasil dihapus!");
                                x = h;
                                cekHapus = true;
                                hapus = true;
                            }
                            h++;
                        }
                        if(hapus){
                            accounts.remove(x);
                        }
                        ObjectSaver.saveObject(accounts);
                    }
                    if(!cekHapus){
                        System.out.println("Akun tidak ditemukan!");
                    }
                    break;

                case 0:
                    System.out.println("Terimakasih sudah berkunjung!");
                    break;

                default:
                    System.out.println("Maaf inputan salah perhatikan perintah, 1-6 untuk menu, 0 untuk keluar");
            }
            System.out.println();
            System.out.println();
        }while(pilih != 0);
    }

    public static void addAccount(List<Account> akun){
        Scanner masuk = new Scanner(System.in);
        System.out.println("Tambah akun");
        System.out.print("Masukkan nama akun : ");
        String namaAkun = masuk.nextLine();
        System.out.print("Masukkan url akun  : ");
        String url = masuk.nextLine();
        System.out.print("Masukkan username  : ");
        String username = masuk.nextLine();
        System.out.print("Masukkan password  : ");
        String pass = masuk.nextLine();
        String password = enkripVignere(pass, "pass");
        HashMap<String, String> Password = new HashMap<String, String>();
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date waktu = new Date();
        String waktuUbah = df.format(waktu);
        Password.put(waktuUbah, password);
        Account newAccount = new Account(namaAkun, url, username, password, Password);
        akun.add(newAccount);
        ObjectSaver.saveObject(akun);
    }

    public static String enkripVignere(String plain, String key){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char hasil[] = new char[plain.length()];
        for(int i =0; i<plain.length(); i++){
            char newKey = key.charAt((i % key.length()));
            char newPlain = alpha.charAt((alpha.indexOf(newKey)+alpha.indexOf(plain.charAt(i)))%alpha.length());
            hasil[i] += newPlain;
        }
        return new String(hasil);
    }

    public static String dekripVignere(String ciphertext, String key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int keyIndex = 0;
        char hasil[] = new char[ciphertext.length()];
        for (int i = 0; i < ciphertext.length(); i++) {
            char charCipher = ciphertext.charAt(i);
            char keyChar = key.charAt(keyIndex % key.length());
            int newCharIndex = (alpha.indexOf(charCipher) - alpha.indexOf(keyChar) + alpha.length()) % alpha.length();
            char newChar = alpha.charAt(newCharIndex);
            hasil[i] += newChar;
            keyIndex++;
        }
        return new String(hasil);
    }


}