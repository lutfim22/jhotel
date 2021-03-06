package jhotel;

import java.util.ArrayList;

/**
 * berisi database pesanan.
 *
 * @author Luthfi Musthafa_1506673656
 * @version 2018.05.14
 */
public class DatabasePesanan
{
    private static ArrayList<Pesanan> PESANAN_DATABASE = new ArrayList<>();
    private static int LAST_PESANAN_ID = 0;

    /**
     * method untuk membuat arraylist berisi pesanan
     *
     * @return PESANAN_DATABASE
     */
    public static ArrayList<Pesanan> getPesananDatabase()
    {
        return PESANAN_DATABASE;
    }

    /**
     * untuk mendapatkan ID dari pesanan terakhir
     *
     * @return LAST_PESANAN_ID
     */
    public static int getLastPesananID()
    {
        return LAST_PESANAN_ID;
    }

    /**
     * untuk menambahkan pesanan ke database.
     *
     * @param baru berisi objek pesanan.
     */
    public static boolean addPesanan(Pesanan baru) throws PesananSudahAdaException
    {
        for(Pesanan pesan : PESANAN_DATABASE){
            if(pesan.getStatusAktif() && pesan.getId() == baru.getId()){
                throw new PesananSudahAdaException(baru);
                //return false;
            }
        }
        LAST_PESANAN_ID = baru.getId();
        PESANAN_DATABASE.add(baru);
        return true;
    }

    /**
     * untuk mengambil data pemesanan.
     *
     * @param id berisi id
     * @return pesanan mengembalikan data pemesanan jika id yang dipesan telah ada.
     */
    public static Pesanan getPesanan(int id)
    {
        for(Pesanan pesanan : PESANAN_DATABASE){
            if(pesanan.getId() == id){
                return pesanan;
            }
        }
        return null;
    }
    
    /** 
    * Untuk mengambil data pemesanan.
     *
    * @return Pesanan mengembalikan data pemesanan jika Room yang dipesan telah ada.
    */
    public static Pesanan getPesananAktif(Room kamar)
    {
        for(Pesanan pesanan : PESANAN_DATABASE){
            if(pesanan.getRoom().equals(kamar) && pesanan.getStatusAktif()){
                return pesanan;
            }
        }
        return null;
    }

    /**
     * untuk mengambil data pesanan aktif
     *
     * @param pelanggan berisi objek Customer
     * @return pesanan
     */
    public static Pesanan getPesananAktif(Customer pelanggan)
    {
        for(Pesanan pesanan : PESANAN_DATABASE){
            if(pesanan.getPelanggan().equals(pelanggan) && pesanan.getStatusAktif()){
                return pesanan;
            }
        }
        return null;
    }

    /**
     * untuk menghapus pesanan dari database.
     *
     * @param cust berisi objek pesanan.
     */
    public static boolean removePesanan(Customer cust) throws PesananTidakDitemukanException
    {
        for(Pesanan pesanan : PESANAN_DATABASE){
            if(pesanan.getPelanggan().equals(cust)){
                if(pesanan.getRoom() != null){
                    Administrasi.pesananDibatalkan(pesanan);
                }
                else{
                    if(pesanan.getStatusAktif()){
                        pesanan.setStatusAktif(false);
                    }
                }
                if(PESANAN_DATABASE.remove(pesanan)){
                    return true;
                }
            }
        }
        throw new PesananTidakDitemukanException(cust);
    }
}
