import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    private String maSV;

    public DuLieuKhongHopLeException(String maSV) {
        this.maSV = maSV;
    }

    public String getMaSV() {
        return maSV;
    }
}

abstract class SinhVien {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maHe, String maSV, int soTinChi) {
        this.maHe = maHe;
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
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

class QuanLyHocPhi {
    private ArrayList<SinhVien> ds = new ArrayList<>();

    public void themSinhVien(String maHe, String maSV,
                             String soTinChiText)
                             throws DuLieuKhongHopLeException {

        if (!maHe.equals("CQ") && !maHe.equals("CLC")
                && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        int soTinChi;

        try {
            soTinChi = Integer.parseInt(soTinChiText);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException(maSV);
        }

        for (SinhVien sv : ds) {
            if (sv.getMaSV().equals(maSV)) {
                throw new DuLieuKhongHopLeException(maSV);
            }
        }

        SinhVien sv;

        if (maHe.equals("CQ")) {
            sv = new ChinhQuy(maSV, soTinChi);
        } else if (maHe.equals("CLC")) {
            sv = new ChatLuongCao(maSV, soTinChi);
        } else {
            sv = new LienThong(maSV, soTinChi);
        }

        ds.add(sv);
    }

    public void sapXep() {
        Collections.sort(ds, (a, b) -> {
            if (a.hocPhi() != b.hocPhi()) {
                return Long.compare(b.hocPhi(), a.hocPhi());
            }

            return a.getMaSV().compareTo(b.getMaSV());
        });
    }

    public void hienThiDanhSach() {
        for (SinhVien sv : ds) {
            System.out.println(
                sv.getMaSV() + " " + sv.getMaHe()
                + " " + sv.hocPhi()
            );
        }
    }

    public long tinhTongHocPhi() {
        long tong = 0;

        for (SinhVien sv : ds) {
            tong += sv.hocPhi();
        }

        return tong;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            String soTinChi = sc.next();

            try {
                ql.themSinhVien(maHe, maSV, soTinChi);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + e.getMaSV());
            }
        }

        ql.sapXep();
        ql.hienThiDanhSach();

        System.out.println("TONG " + ql.tinhTongHocPhi());

        sc.close();
    }
}
