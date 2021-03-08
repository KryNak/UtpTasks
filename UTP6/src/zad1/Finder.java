/**
 *
 *  @author Nakielski Krystian S20258
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Finder {

    private int ifCounter;
    private String input;

    public Finder(String fname) throws IOException {
        input = Files.lines(Paths.get(fname)).collect(Collectors.joining("\n"));

        String ifStrPretreatment = input.replaceAll("((?s)/\\*.*?\\*/)|(//.*)|(\".*?\")", "").trim();
        int ifCounter = 0;
        for( Matcher m = Pattern.compile("if\\s*?\\(.+?\\)").matcher(ifStrPretreatment); m.find(); m.group()){
            ifCounter++;
        }
        this.ifCounter = ifCounter;
    }

    public int getIfCount() {
        return ifCounter;
    }

    public int getStringCount(String charSequence) {
        int counter = 0;
        for(Matcher m = Pattern.compile(charSequence).matcher(input); m.find(); m.group()){
            counter++;
        }

        return counter;
    }
}
