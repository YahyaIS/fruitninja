
package fruitninja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class userauthentication {

    public userauthentication() {
    }
    
    public boolean validate(String username,String password) throws FileNotFoundException, IOException{
        File fileN = new File("playername.txt");
        File fileP = new File("playerpassword.txt");
        BufferedReader readerN;
        readerN = new BufferedReader(new FileReader(fileN));
        BufferedReader readerP;
        readerP = new BufferedReader(new FileReader(fileP));
        String readlineN = "";
        String readlineP= "";
        if(fileN.exists()&&fileP.exists()){
            while((readlineN=readerN.readLine())!=null && (readlineP=readerP.readLine())!=null){
                if(readlineN.equals(username)&& readlineP.equals(password)){
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
