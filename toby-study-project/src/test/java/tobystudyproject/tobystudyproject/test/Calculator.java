package tobystudyproject.tobystudyproject.test;

import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Calculator {

    public Integer calcSum(String filePath) throws IOException {
        LineCallback sumCallback = new LineCallback() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public Integer calcMultiply(String filePath) throws IOException {
        BufferedReaderCallback multiplyCallback = new BufferedReaderCallback(){
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer multiply = 1;
                String line = null;
                while((line = br.readLine()) != null){
                    multiply *= Integer.valueOf(line);
                }
                return multiply;
            }
        };
        return fileReadTemplate(filePath, multiplyCallback);
    }

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            Integer res = initVal;
            String line = null;
            while((line = br.readLine())!=null){
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        }catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }finally {
            if(br!= null){
                try {
                    br.close();
                }catch (IOException e){
                }
            }
        }
    }

    public <T> T lineReadTemplate(String filePath, LineCallbackWithGeneric<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            T res = initVal;
            String line = null;
            while((line = br.readLine()) != null){
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }finally {
            if(br!= null){
                try {
                    br.close();
                }catch (IOException e){
                }
            }
        }
    }

    public String concatenate(String filepath) throws IOException {
        LineCallbackWithGeneric<String> contatenateCallback = new LineCallbackWithGeneric<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        };
        return lineReadTemplate(filepath, contatenateCallback, "");
    }
}
