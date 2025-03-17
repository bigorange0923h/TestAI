package org.bigorange.testai.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCodeUtils {
    /**
     * 从文本中提取第一个代码块
     */
    public static String extractFirstCodeBlock(String text) {
        // 匹配 ```任意语言 代码内容 ```
        String pattern = "```(?:\\w+)?\\n(.*?)\\n```";
        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = r.matcher(text);

        if (m.find()) {
            return m.group(1).trim();
        }
        return "未找到代码块";
    }

    /**
     * 提取所有代码块
     */
    public static List<String> extractAllCodeBlocks(String text) {
        List<String> codes = new ArrayList<>();
        String pattern = "```(?:\\w+)?\\n(.*?)\\n```";
        Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = r.matcher(text);

        while (m.find()) {
            codes.add(m.group(1).trim());
        }
        return codes;
    }
}
