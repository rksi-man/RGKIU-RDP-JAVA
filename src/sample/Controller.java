package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;


public class Controller {


    @FXML
    private TextArea Out_L;

    public void On_Click() throws InterruptedException, IOException {
        Toolkit.getDefaultToolkit().beep();
        if(isWindows()){


            //String win_ver1 = getOSVerion();
            double win_ver = Double.parseDouble(getOSVerion());



            if (win_ver >= 4) {
                TimeUnit.SECONDS.sleep(10);
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream pbk_file = classloader.getResourceAsStream("resources/connection.pbk");
                Out_L.setText(win_ver + "\n" + pbk_file.available() + "\n");
                String Con_Dial = ("cmd @echo off /c rasdial \"RKIU-VPN\" vpn rkiuvpn /phonebook:\"resources/connection.pbk\"");
                Runtime.getRuntime().exec(Con_Dial);
                System.out.println(Con_Dial);//Вывод строки Con_Dial
            }

            else
            {
                Out_L.setText("Версия не поддерживается");
            }


        }else if(isMac()){
            Out_L.setText("Mac "+getOSVerion());
        }else if(isUnix ()){
            Out_L.setText("Unix/Linux "+getOSVerion());
        }else{
            Out_L.setText("Unknown OS");
        }
    }
    public static boolean isWindows(){

        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.indexOf( "win" ) >= 0);

    }

    public static boolean isMac(){

        String os = System.getProperty("os.name").toLowerCase();
        //Mac
        return (os.indexOf( "mac" ) >= 0);

    }

    public static boolean isUnix (){

        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);

    }
    public static String getOSVerion() {

        String os = System.getProperty("os.version");
        return os;
    }



}
