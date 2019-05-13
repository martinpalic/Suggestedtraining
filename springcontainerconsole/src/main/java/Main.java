import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ImageFileScanner scanner = context.getBean(ImageFileScanner.class);
        Base64ImageConverterService converter = context.getBean(Base64ImageConverterService.class);


        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            switch (inputScanner.next()) {
                case "exit":
                case "x":
                    System.exit(0);
                    break;
                case "read":
                case "r":
                    printImageStringsFromFolder(converter, scanner.scanCurrentFolder());
                    System.out.println("Reading finished");
                    break;
                case "write":
                case "w":
                    System.out.println("Insert Base64 strings, end loading with entering 'x'");
                    createImageFilesFromStringsInput(converter,scanner,inputScanner);
                    System.out.println("Writing finished");
                    break;
                case "readDir":
                    printImageStringsFromFolder(converter, scanner.scanDirectory(inputScanner.next()));
                    System.out.println("Reading finished");
                    break;
            }
        }

    }

    private static void printImageStringsFromFolder(Base64ImageConverterService converter, List<byte[]> bytes){
        for (String out : converter.convertImagesToBase64(bytes)
        ) {
            System.out.println(out);
        }
    }

    private static void createImageFilesFromStringsInput(Base64ImageConverterService converter, ImageFileScanner scanner, Scanner inputScanner) throws IOException {
        List<String> stringList = new ArrayList<>();
        String bs64str = inputScanner.next();

        while (!bs64str.equals("x")) {
            stringList.add(bs64str);
            bs64str = inputScanner.next();
        }
        List<byte[]> bytes = converter.convertBase64toImages(stringList);
        for (int i = 0; i < bytes.size(); i++) {
            FileUtils.writeByteArrayToFile(new File(i+"." + scanner.getImageExtension()), bytes.get(i));
        }

    }

}
