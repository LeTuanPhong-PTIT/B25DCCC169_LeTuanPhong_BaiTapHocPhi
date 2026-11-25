import java.util.Scanner;

abstract class SinhVien {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maHe, String maSV, int soTinChi) {
        this.maHe = maHe;
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String maSV, int soTinChi) {
        super("CQ", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return 450000L * soTinChi;
    }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String maSV, int soTinChi) {
        super("CLC", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return 675000L * soTinChi;
    }
}

class LienThong extends SinhVien {
    public LienThong(String maSV, int soTinChi) {
        super("LT", maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return 450000L * soTinChi + 2000000L;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            if (maHe.equals("CQ")) {
                ds[i] = new ChinhQuy(maSV, soTinChi);
            } else if (maHe.equals("CLC")) {
                ds[i] = new ChatLuongCao(maSV, soTinChi);
            } else if (maHe.equals("LT")) {
                ds[i] = new LienThong(maSV, soTinChi);
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(
                sv.maSV + " " + sv.maHe + " " + sv.hocPhi()
            );
        }

        sc.close();
    }
}
