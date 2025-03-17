package org.bigorange.testai.handler.ai;

/**
 * ai 对话处理器
 */
public abstract class ChatAIHandler {
    final String prompt = "   请基于以下HTML结构生成Selenium测试脚本：\n" +
            "            - 语言要求：Java" +
            "            - 测试目标：验证主要功能按钮点击是否正常\n" +
            "            - HTML内容:            %s";
    final String prompt1 = "请帮我生成一个Java demo 代码,要求是能打印输出hello world的";

    public String generateTestScript(String html) {
        return getScriptCode(prompt1 + html);
    }

    public abstract String getScriptCode(String chat);

}
