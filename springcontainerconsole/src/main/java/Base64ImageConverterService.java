import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64ImageConverterService {

    public List<String> convertImagesToBase64(List<byte[]> filesContents) {
        List stringList = new ArrayList<String>();
        for (byte[] content : filesContents
        ) {
            stringList.add(Base64.getEncoder().encodeToString(content));
        }
        return stringList;
    }

    public List<byte[]> convertBase64toImages(List<String> strings) {
        List baseList = new ArrayList<byte[]>();
        for (String content : strings
        ) {
            baseList.add(Base64.getDecoder().decode(content));
        }
        return baseList;
    }
}
