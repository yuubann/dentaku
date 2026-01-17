import javax.swing.*;
import java.awt.*;

public class CalculatorModel{
    private double result = 0;
    private String operator = "";
    private boolean isStartOfNumber = true;

    public void processCommand(String cmd, CalculatorView view){
        String current = view.getDisplayText();

        if("0123456789".contains(cmd)){

            if(isStartOfNumber || current.equals("0")){
                view.setDisplayText(cmd); // 最初が0なら上書き
            }else{
                view.setDisplayText(current + cmd);
            }

            isStartOfNumber = false;

        }else if(cmd.equals("C")){
            view.setDisplayText("0");
            this.result = 0;
            this.operator = "";
            isStartOfNumber = true;

        }else if(cmd.equals("BS")){
            if(isStartOfNumber || current.equals("0")){
                return;

            }else if(current.length() == 1){
                view.setDisplayText("0");
                isStartOfNumber = true;

            }else{
                StringBuilder sb = new StringBuilder(current);
                sb.setLength(sb.length() - 1);
                view.setDisplayText(sb.toString());

            }
        }else if(cmd.equals("%")){
            Double d = Double.valueOf(current);
            double result1 = d / 100;

            if(result1 == (long)result1){
                view.setDisplayText(String.valueOf((long)result1));
            }else{
                view.setDisplayText(String.valueOf(result1));
            }

        }else if(cmd.equals("±")){
            Double d = Double.valueOf(current);
            double result2 = d * -1.0;

            if(result2 == (long)result2){
                view.setDisplayText(String.valueOf((long)result2));
            }else{
                view.setDisplayText(String.valueOf(result2));
            }

        }else if(cmd.equals(".")){
            current = view.getDisplayText();

            if(isStartOfNumber){
                view.setDisplayText("0.");
                isStartOfNumber = false;

            }else if(!current.contains(".")){
                view.setDisplayText(current + ".");
            }

        }else{
            double currentVal = Double.parseDouble(current);
            calculate(currentVal); //別途作った計算メソッドを呼ぶ

            String displayValue;
            if(result == (long)result){
                displayValue = String.valueOf((long)result);

            }else{
                displayValue = String.valueOf(result);

            }
            view.setDisplayText(displayValue);

            if(cmd.equals("=")){
                operator = ""; // 計算終了

            }else{
                operator = cmd; // 次の計算のために演算子を保存
            }
            isStartOfNumber = true;
        }

    }

        private void calculate(double n) {
        if (operator.equals("+")) result += n;
        else if (operator.equals("-")) result -= n;
        else if (operator.equals("*")) result *= n;
        else if (n == 0 && operator.equals("/")) result /= n;
        else result = n; // まだ演算子がない場合は、今の値をそのまま結果にする
        }

}