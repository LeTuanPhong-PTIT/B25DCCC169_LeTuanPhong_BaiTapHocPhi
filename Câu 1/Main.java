import java.util.*;

class SinhVien {
    private String msv, hoTen, ngaySinh;

    public SinhVien (String msv, String hoTen, String ngaySinh) {
        this.msv = msv;
        this.hoTen = chuanHoaHoTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaHoTen(String hoTen) {
        hoTen = hoTen.trim().toLowerCase();
        String[] tens = hoTen.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String ten : tens){
            String chuanHoa = ten.substring(0 , 1).toUpperCase() + ten.substring(1);
            if (result.length() > 0) {
                result.append(" ");
            }
            
            result.append(chuanHoa);
        }

        return result.toString();
    }

    private String chuanHoaNgaySinh(String ngay) {
        String[] parts = ngay.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public void hienThi() {
        System.out.println(msv + " " + hoTen + " " + ngaySinh);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String msv = sc.nextLine();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine();

            ds[i] = new SinhVien(msv, hoTen, ngaySinh);
        }

        for (SinhVien sv : ds) {
            sv.hienThi();
        }
        sc.close();
    }
}
