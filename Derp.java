package deletetemps;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Derp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tempDir = System.getenv("TEMP");
        System.out.println("TEMP dir found at: " + tempDir);
        instructions();
        String input = in.nextLine();
        while (!input.equals("0") && !input.equals("1")) {
            System.out.println("Usage: ");
            instructions();
            input = in.nextLine();
        }
        Pattern p = Pattern.compile(".*");
        if (input.equals("0")) {
            System.out.println("Clearing out Windows Updates");
            p = Pattern.compile("\\{?[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*-[A-Z0-9]*\\}?");
        }
        if (input.equals("1")) {
            System.out.println("Clearing out TEMP folder");
        }
        File parent = new File(tempDir);
        Pattern finalP = p;
        String[] directories = parent.list((current, name) -> {
            File check = new File(current, name);
            final Pattern pp = finalP;
            boolean isDir = check.isDirectory();
            boolean isMatch = finalP.matcher(check.getName()).matches();
            return isDir && isMatch;
        });
        System.out.println("Found " + directories.length + " files to delete:");
        System.out.println(Arrays.toString(directories));
        for (int i = 0; i < directories.length; i++) {
            String fullName = parent.toString() + "\\" + directories[i];
            System.out.println("deleting directory " + (i+1) + "/" + directories.length + fullName);
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

    private static void instructions() {
        System.out.println("Press 0 to search and delete Windows Updates folders in the TEMP directory.");
        System.out.println("Press 1 to clear out the entire TEMP directory.");
    }
}
