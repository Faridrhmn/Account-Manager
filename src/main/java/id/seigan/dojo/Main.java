package id.seigan.dojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        ObjectSaver.fileName = "D:/Bootcamp/programming-temp/account_manager/account.txt";

        Scanner masuk = new Scanner(System.in);
        int pilih;
        do {
            System.out.println("Menu operasi");
            System.out.println("1. Tampil daftar akun \n2. Tampil akun\n3. tambah akun\n4. Ubah password\n5. Riwayat perubahan password\n6. Hapus akun\n0. Keluar");
            System.out.printf("Masukkan pilihan : ");
            pilih = masuk.nextInt();
            switch (pilih){
                case 1 :
                    Object object = ObjectSaver.retrieveObject();
                    if(object instanceof List){
                        List<Account> accounts = (List<Account>)object;
                        for(Account acc: accounts){
                            System.out.println(acc.getAccountName()+ " - " +acc.getPassword()+" - "+ acc.getUsername());
                        }
                    }
                    break;

                case 2 :
                    System.out.println("dua");
                    break;

                case 3 :
                    System.out.println("Tambah akun");
                    Account gMail = new Account("Gmail", "udin@gmail.com", "sedunia");
                    Account livinMandiri = new Account("Livin", "livinUdin","seduniaLivin");
                    Account ML = new Account("ikaeda", "udin", "1234");

                    List<Account> accountList = new ArrayList<>();
                    accountList.add(gMail);
                    accountList.add(livinMandiri);
                    accountList.add(ML);


                    System.out.println(accountList.contains(gMail));
                    ObjectSaver.saveObject(accountList);
                    break;

                case 4 :
                    System.out.println("empat");
                    break;

                case 5 :
                    System.out.println("lima");
                    break;

                case 6 :
                    System.out.println("enam");
                    break;

                case 0:
                    System.out.println("Terimakasih sudah berkunjung!");
                    break;

                default:
                    System.out.println("Maaf inputan salah perhatikan perintah, 1-6 untuk menu, 0 untuk keluar");
            }
            try {
                // Print the OS name for debugging
                System.out.println("OS: " + System.getProperty("os.name"));

                // Simple command to test ProcessBuilder
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "echo Hello");
                Process process = processBuilder.inheritIO().start();
                int exitCode = process.waitFor();
                System.out.println("Simple command exit code: " + exitCode);

                // Attempt to clear the console
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                process = processBuilder.inheritIO().start();
                exitCode = process.waitFor();
                System.out.println("Clear command exit code: " + exitCode);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(pilih != 0);
    }
}