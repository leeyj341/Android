package multi.android.material_design_pro.circleexam;
//RecyclerView의 한 항목을 구성하는 데이터를 저장하는 객체
public class CircleItem2 {
    int imgCode;
    String content;


    public CircleItem2(int imgCode, String content) {
        this.imgCode = imgCode;
        this.content = content;
    }

    public int getImgCode() {
        return imgCode;
    }

    public void setImgCode(int imgCode) {
        this.imgCode = imgCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
