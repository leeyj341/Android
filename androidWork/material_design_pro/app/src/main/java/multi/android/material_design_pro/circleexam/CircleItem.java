package multi.android.material_design_pro.circleexam;
//RecyclerView의 한 항목을 구성하는 데이터를 저장하는 객체
public class CircleItem {
    int imgCode;


    public CircleItem(int imgCode) {
        this.imgCode = imgCode;
    }

    public int getData() {
        return imgCode;
    }

    public void setData(int imgCode) {
        this.imgCode = imgCode;
    }
}
