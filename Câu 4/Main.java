import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maHe;
    protected String maSV;
    protected int soTinChi;

    private MienGiam mienGiam;

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

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long soTienGiam() {
        if (mienGiam == null) {
            return 0;
        }

        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - soTienGiam();
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

    public void themSinhVien(SinhVien sv) {
        ds.add(sv);
    }

    public boolean ganMienGiam(String maSV, MienGiam chinhSach) {
        for (SinhVien sv : ds) {
            if (sv.getMaSV().equals(maSV)) {
                sv.setMienGiam(chinhSach);
                return true;
            }
        }

        return false;
    }

    public void hienThiDanhSach() {
        for (SinhVien sv : ds) {
            System.out.println(
                sv.getMaSV() + " " +
                sv.getMaHe() + " " +
                sv.hocPhi() + " " +
                sv.soTienGiam() + " " +
                sv.phaiNop()
            );
        }
    }

    public void thongKe() {
        int soCQ = 0, soCLC = 0, soLT = 0;
        long tongCQ = 0, tongCLC = 0, tongLT = 0;

        for (SinhVien sv : ds) {
            long phaiNop = sv.phaiNop();

            if (sv.getMaHe().equals("CQ")) {
                soCQ++;
                tongCQ += phaiNop;
            } else if (sv.getMaHe().equals("CLC")) {
                soCLC++;
                tongCLC += phaiNop;
            } else if (sv.getMaHe().equals("LT")) {
                soLT++;
                tongLT += phaiNop;
            }
        }

        if (soCQ > 0) {
            System.out.println("CQ " + soCQ + " " + tongCQ);
        }

        if (soCLC > 0) {
            System.out.println("CLC " + soCLC + " " + tongCLC);
        }

        if (soLT > 0) {
            System.out.println("LT " + soLT + " " + tongLT);
        }
    }
}

public class Main {

    public static MienGiam taoChinhSach(String ma) {
        if (ma.equals("CS")) {
            return new ConChinhSach();
        } else if (ma.equals("HN")) {
            return new HoNgheo();
        } else if (ma.equals("HB")) {
            return new HocBong();
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int soTinChi = sc.nextInt();

            SinhVien sv;

            if (maHe.equals("CQ")) {
                sv = new ChinhQuy(maSV, soTinChi);
            } else if (maHe.equals("CLC")) {
                sv = new ChatLuongCao(maSV, soTinChi);
            } else {
                sv = new LienThong(maSV, soTinChi);
            }

            ql.themSinhVien(sv);
        }

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maChinhSach = sc.next();

            MienGiam chinhSach = taoChinhSach(maChinhSach);

            boolean found = ql.ganMienGiam(maSV, chinhSach);

            if (!found) {
                System.out.println("NOT FOUND " + maSV);
            }
        }

        ql.hienThiDanhSach();
        ql.thongKe();

        sc.close();
    }
}
