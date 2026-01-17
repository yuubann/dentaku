import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {
    private JTextField display;
    private CalculatorModel model;

    public CalculatorView(){
        // 1. 画面(フレーム)の設定
        setTitle("Java電卓");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. 表示欄の作成
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // 3. ボタンを並べるパネルの作成
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // 4. ボタンのラベルを用意
        String[] labels = {
            "%", "C", "BS", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        // 5. ループでボタンを作ってパネルに追加
        model = new CalculatorModel(); // ループの前に計算機をひとつだけ生成する
        for(String label : labels){
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));

            button.addActionListener(e ->{
                model.processCommand(label, this);
            });
            buttonPanel.add(button);
        }

        // 6. パネルを中央に配置
        add(buttonPanel, BorderLayout.CENTER);
    }

    // 表示欄を更新するためのメソッド(Modelから呼ばれる想定)
    public void setDisplayText(String text){
        display.setText(text);
    }

    // 今の表示を所得するメソッド
    public String getDisplayText(){
        return display.getText();
    }
}



    
    

