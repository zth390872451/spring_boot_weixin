package main.com.domain;

/**
 * Created by Administrator on 2016/8/2 0002.
 * 复杂按钮（父按钮)
 */
public class ComplexButton extends Button {

    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

}
