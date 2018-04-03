package deletetemps;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Derp {

    public static void main(String[] args) {
        System.out.println("running deletetemps");
        Pattern p = Pattern.compile("[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*");
        File parent = new File("C://Users//qtdwp0//AppData//Local//Temp");
        String[] directories = parent.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                File check = new File(current, name);
                boolean isDir = check.isDirectory();
                boolean isMatch = p.matcher(check.getName()).matches();
                return isDir && isMatch;
            }
        });
        System.out.println(directories.length);
        System.out.println(Arrays.toString(directories));
        for (int i = 0; i < directories.length; i++) {
            String fullName = parent.toString() + "\\" + directories[i];
            System.out.println("deleting directory " + fullName + " " + i + "/" + directories.length);
            deleteDir(new File(fullName));
        }

    }

    private static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

}
